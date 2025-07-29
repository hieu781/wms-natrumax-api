package com.natrumax.services.APIService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.natrumax.config.MisaApiClient;
import com.natrumax.dto.request.SaleRequest;
import com.natrumax.dto.response.WarehouseResponse;
import com.natrumax.models.*;
import com.natrumax.models.MockAPI.*;
import com.natrumax.models.MockAPI.request.MisaInventoryInRequest;
import com.natrumax.models.MockAPI.request.MisaReceiptRequest;
import com.natrumax.models.MockAPI.response.*;
import com.natrumax.repository.OrderRepository;
import com.natrumax.repository.ProductRepository;
import com.natrumax.repository.UserWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MisaService {
    @Autowired
    private ProductRepository productRepository;

    private final MisaApiClient misaApiClient;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserWarehouseRepository userWarehouseRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public MisaService(MisaApiClient misaApiClient) {
        this.misaApiClient = misaApiClient;
    }

    public List<MisaGood> getAllMisaGoods() {
        return misaApiClient.getMisaGoods().getData();
    }

//    public Optional<MisaGood> getMisaGoodById(String id) {
//        try {
//            // Get goods by ID
//            List<MisaGood> goods = getAllMisaGoods();
//            return goods.stream()
//                    .filter(good -> good.getId().equals(id))
//                    .findFirst();
//        } catch (Exception e) {
//            return Optional.empty();
//        }
//    }

    public Optional<MisaGood> getMisaGoodByCode(String code) {
        try {
            List<MisaGood> goods = getAllMisaGoods();
            return goods.stream()
                    .filter(good -> good.getCode().equalsIgnoreCase(code))
                    .findFirst();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public MisaGood getGoodByCode(String code) {
        ResponseEntity<MisaGoodResponse> response = misaApiClient.getMisaGoodsByCode(code);
        List<MisaGood> goods = response.getBody().getData();
        return goods != null && !goods.isEmpty() ? goods.get(0) : null;
    }

    public MisaCustomer getMisaCustomerByCode(String misaCode) {
        MisaCustomerResponse response = misaApiClient.getMisaCustomerByCustomerId(misaCode);
        if (response != null && response.getData() != null && !response.getData().isEmpty()) {
            return response.getData().get(0);
        }
        return null;
    }

//    public void createInventoryIn(MisaInventoryInRequest request, String clientSecret) {
//        ResponseEntity<Void> response = misaApiClient.createMisaInventoryIn(request, clientSecret);
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            throw new RuntimeException("Failed to create inventory in. Status: " + response.getStatusCode());
//        }
//    }

//    public void createReceipt(MisaReceiptRequest request, String clientSecret) {
//        ResponseEntity<Void> response = misaApiClient.createMisaReceipt(request, clientSecret);
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            throw new RuntimeException("Failed to create receipt. Status: " + response.getStatusCode());
//        }
//    }

    public String createReceipt(User member, User owner, double totalAmount) {
        String accountNo = owner.getBank().getAccountNo();
        String accountName = owner.getAccountName();

        // Lấy danh sách MISA bank theo accountNo
        List<MisaBank> misaBanks = misaApiClient.getBanksByNumber(accountNo).getData();
        if (misaBanks.isEmpty()) {
            throw new RuntimeException("Không tìm thấy MISA bank với số tài khoản: " + accountNo);
        }
        MisaBank selectedBank = misaBanks.get(0);

        // Lấy misa_code từ detail của member
        String misaCode;
        try {
            JsonNode detailJson = objectMapper.readTree(member.getDetail());
            misaCode = detailJson.path("misa_code").asText();

            if (misaCode == null || misaCode.isEmpty()) {
                throw new RuntimeException("Missing misa_code in user detail");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse user detail for misa_code", e);
        }

        // Tạo request gửi đến MISA
        MisaReceiptRequest misaRequest = new MisaReceiptRequest();
        misaRequest.setCustomer(misaCode);
        misaRequest.setVoucher_date(Instant.now());
        misaRequest.setPosted_date(Instant.now());
        misaRequest.setDebit_account("1121");
        misaRequest.setCash_deposit(totalAmount);
        misaRequest.setReason("Thu tiền của " + accountName);
        misaRequest.setItem_name("Thu tiền của " + accountName);
        misaRequest.setAmount(totalAmount);
        misaRequest.setBank(selectedBank.get_id());

        // Gửi đến MISA
        ResponseEntity<MisaReceiptResponse> response = misaApiClient.createMisaReceipt(misaRequest, "secretNppHD");

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getReceipt().getVoucher_no();
        } else {
            throw new RuntimeException("Failed to create receipt in MISA");
        }
    }

    public String extractMisaCodeFromUserDetail(Order order) {
        String detailJson = order.getUser().getDetail();
        String userMisaCode;
        try {
            JsonNode root = new ObjectMapper().readTree(detailJson);
            userMisaCode = root.path("misa_code").asText();
            if (userMisaCode.isEmpty()) {
                throw new RuntimeException("Không tìm thấy misa_code trong user.detail");
            }
        } catch (IOException e) {
            throw new RuntimeException("Lỗi phân tích JSON user.detail", e);
        }
        return userMisaCode;
    }

    public void createMisaSale(String clientSecret, Order order) {

        //Find Customer
        String misa_code = extractMisaCodeFromUserDetail(order);
        MisaCustomerResponse page = misaApiClient.getCustomersByCustomerId(misa_code);
        if (page.getData().isEmpty()) {
            throw new RuntimeException("Không tìm thấy MISA customer cho mã: " + misa_code);
        }
        String customerId = page.getData().get(0).get_id();
        //tao sale item
        List<MisaSaleItem> saleItems = order.getOrderDetails().stream()
                .map(detail -> {
                    // load product (if you need more info than just misaCode)
                    Product product = productRepository.findById(detail.getProduct().getProductId())
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm ID=" + detail.getProduct().getProductId()));

                    String code = detail.isBonus()
                            ? "KM." + product.getMisaCode()
                            : product.getMisaCode();

                    MisaSaleItem item = new MisaSaleItem();
                    item.setCode(code);
                    item.setQuantity(detail.getQuantity());
                    return item;
                })
                .collect(Collectors.toList());
        // Tạo misa sale  để gửi sang MISA
        SaleRequest saleRequest = new SaleRequest();
        saleRequest.setPosted_date(LocalDateTime.now());
        saleRequest.setVoucher_date(LocalDateTime.now());
        saleRequest.setCustomer(customerId);
        saleRequest.setSaleItems(saleItems);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(saleRequest);
            System.out.println("➡ JSON gửi MISA: " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        SaleResponse saleResponse = misaApiClient.createMisaSale(saleRequest, clientSecret);
        order.setSaleCode(saleResponse.getSale().getVoucherNo());
    }

    public String getClientSecretByUser(User user) {
        String clientSecret = "";
        List<WarehouseResponse> listWarehouseResponse = userWarehouseRepository.findWarehouseDTOsByUserId(user.getId());
        for (WarehouseResponse warehouse : listWarehouseResponse) {
            if (warehouse.getRoleInWarehouse().equalsIgnoreCase("Member")) {
                clientSecret = warehouse.getAccessCode();
            }
        }
        System.out.println(clientSecret);
        return clientSecret;
    }


    public String getSaleIdByOrder(Order order) {
        String voucherNo = order.getSaleCode();
        MisaSaleResponse page = misaApiClient.getSalesByVoucher(voucherNo);
        if (page == null || page.getData().isEmpty()) {
            throw new RuntimeException("No sale found for voucher: " + voucherNo);
        }
        return page.getData().get(0).getId();
    }

    public boolean isInventoryFullyExported(String clientSecret, Order order) {
        String saleId = getSaleIdByOrder(order);

        MisaInventoryOutResponse resp = misaApiClient.getInventoryOut(saleId, clientSecret);
        if (resp == null
                || resp.getRecords() == null
                || resp.getRecords().isEmpty()) {
            return false;
        }

        MisaInventoryOut inv = resp.getRecords().get(0);
        if (inv.getItems() == null || inv.getItems().isEmpty()) {
            return false;
        }

        if (inv.getItems().stream()
                .allMatch(item -> "Xuất đủ".equalsIgnoreCase(item.getStatus()))) {
            order.setInventoryOutCode(inv.getVoucherNo());
            orderRepository.save(order);
        }

        return inv.getItems().stream()
                .allMatch(item -> "Xuất đủ".equalsIgnoreCase(item.getStatus()));
    }

    public boolean isInventoryOutMissing(String clientSecret, Order order) {
        String saleId = getSaleIdByOrder(order);

        MisaInventoryOutResponse resp = misaApiClient.getInventoryOut(saleId, clientSecret);

        if (resp == null
                || resp.getRecords() == null
                || resp.getRecords().isEmpty()) {
            return true;
        }

        return false;
    }

    public boolean createMisaInventoryIn(Order order) {
        // 1. Extract clientSecret as you already do
        String clientSecret = getClientSecretByUser(order.getUser());

        // 2. Build list of inventory‐in items (one per order detail)
        List<InventoryInItem> items = order.getOrderDetails().stream()
                .map(detail -> {
                    InventoryInItem item = new InventoryInItem();
                    // Use the product's MISA code (or productId if that's what your API expects)
                    item.setCode(detail.getProduct().getMisaCode());
                    item.setQuantity(detail.getQuantity());
                    return item;
                })
                .collect(Collectors.toList());

        // 3. Prepare the request payload with current UTC timestamps

        MisaInventoryInRequest req = new MisaInventoryInRequest();
        req.setVoucher_date(LocalDateTime.now());
        req.setPosted_date(LocalDateTime.now());
        req.setInventory_in_items(items);

        // 4. Call the MISA API
        MisaInventoryInResponse resp = misaApiClient.createInventoryIn(req, clientSecret);

        return resp.getMessage() != null && resp.getMessage().equalsIgnoreCase("Tạo phiếu xuất kho thành công!");
    }
}

