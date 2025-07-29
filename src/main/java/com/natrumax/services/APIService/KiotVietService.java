package com.natrumax.services.APIService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.natrumax.config.KiotVietApiClient;
import com.natrumax.dto.request.PurchaseOrderDetailRequest;
import com.natrumax.dto.request.PurchaseOrderRequest;
import com.natrumax.models.*;
import com.natrumax.models.MockAPI.response.KiotVietPurchaseOrderResponse;
import com.natrumax.models.MockAPI.response.KiotVietWareHouseResponse;
import com.natrumax.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KiotVietService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private KiotVietApiClient kiotVietApiClient;
    @Autowired
    private ProductRepository productRepository;
    public KiotVietPurchaseOrderResponse createKiotVietPurchaseOrder(Order order) {
        String clientSecret = getClientSecretByUser(
                order.getUser()
        );
//        String clientSecret = "secret007";
        // 2. Lookup warehouse info to get retailerId
        KiotVietWareHouseResponse whResp =
                kiotVietApiClient.getWarehouse(clientSecret);
        if (whResp == null || whResp.getData().isEmpty()) {
            throw new RuntimeException("No warehouse found for clientSecret: " + clientSecret);
        }
        String retailerId = whResp.getData().get(0).getRetailerId();

        // 3. Build PurchaseOrderRequest
        PurchaseOrderRequest req = new PurchaseOrderRequest();
        req.setPurchaseDate(LocalDateTime.now());

        // Invoice discount percent (assumes Discount has getDiscountPercent())
        double discountRatio = order.getInvoices() != null
                && order.getInvoices().getDiscount() != null
                ? order.getInvoices().getDiscount().getDiscountPercent()
                : 0;
        req.setDiscountRatio(discountRatio);

        // Line items from order details
        List<PurchaseOrderDetailRequest> lines = order.getOrderDetails().stream()
                .map(detail -> {
                    PurchaseOrderDetailRequest line = new PurchaseOrderDetailRequest();

                    // Load product to get its code & name
                    Product p = productRepository.findById(detail.getProduct().getProductId())
                            .orElseThrow(() -> new RuntimeException(
                                    "Product not found: " + detail.getProduct().getProductId()));

                    line.setProductCode(p.getBarcode());      // or p.getMisaCode()
                    line.setProductName(p.getName());
                    line.setQuantity(detail.getQuantity());
                    line.setPrice(detail.getPrice());
                    line.setDiscount(discountRatio);
                    return line;
                })
                .collect(Collectors.toList());
        req.setPurchaseOrderDetails(lines);

        // 4. Call KiotViet API
        KiotVietPurchaseOrderResponse response =  kiotVietApiClient.createPurchaseOrder(req, retailerId);
        return response;
    }

    // Reuse your existing method
    private String getClientSecretByUser(User user) {
        String detailJson = user.getDetail();
        try {
            JsonNode root = objectMapper.readTree(detailJson);
            JsonNode secretNode = root.path("client_secret");
            if (secretNode.isTextual() && !secretNode.asText().isEmpty()) {
                return secretNode.asText();
            } else {
                throw new RuntimeException(
                        "No client_secret field in user.detail for user id=" + user.getId()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to parse user.detail JSON for user id=" + user.getId(), e
            );
        }
    }

}
