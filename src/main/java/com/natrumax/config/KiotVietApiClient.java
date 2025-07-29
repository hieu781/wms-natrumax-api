package com.natrumax.config;

import com.natrumax.dto.request.PurchaseOrderRequest;
import com.natrumax.models.MockAPI.response.*;
import com.natrumax.models.Order;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kiotviet-api", url = "https://mock-api-bice.vercel.app")
public interface KiotVietApiClient {



    @GetMapping("/kiotviet-products")
    ResponseEntity<KiotVietProductResponse> getKiotVietProductByBarCode(@RequestParam("code") String barcode);

    @PostMapping("/kiotViet-purchase-order")
    KiotVietPurchaseOrderResponse createPurchaseOrder(
            @RequestBody PurchaseOrderRequest request,
            @RequestHeader("retailerId") String retailerId
    );
    @GetMapping("/kiotviet-warehouse")
    KiotVietWareHouseResponse getWarehouse(
            @RequestParam("clientSecret") String clientSecret
    );

}
