package com.natrumax.config;


import com.natrumax.dto.request.SaleRequest;
import com.natrumax.models.MockAPI.*;
import com.natrumax.models.MockAPI.request.MisaInventoryInRequest;
import com.natrumax.models.MockAPI.request.MisaReceiptRequest;
import com.natrumax.models.MockAPI.response.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import com.natrumax.models.MockAPI.response.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "misa-api", url = "https://mock-api-bice.vercel.app")
public interface MisaApiClient {


    @PostMapping("/misa-sales")
    SaleResponse createMisaSale(@RequestBody SaleRequest saleRequest,
                                @RequestHeader("clientSecret") String clientSecret);
    @GetMapping("/misa-goods?pageSize=71&currentItem=0")
    MisaGoodResponse getMisaGoods();

    @GetMapping("misa-warehouses")
    MisaWarehouseResponse getMisaWareHouseByClientSecret(@RequestParam("clientSecret") String clientSecret);

    @GetMapping("/misa-warehouse-goods")
    List<MisaWarehouseGood> getMisaWareHouseGoodByWareHouseId(@RequestParam("warehosue") String warehosue
                                                                    , @RequestHeader("clientSecret") String clientSecret);
    @GetMapping("/misa-goods")
    ResponseEntity<MisaGoodResponse> getMisaGoodsByCode(@RequestParam("code") String code);

    @GetMapping("/misa-warehouse-goods")
    List<MisaWarehouseGood> getAllMisaWarehouseGoods();

    @GetMapping("/misa-warehouses")
    MisaWarehouseResponse getAllMisaWarehouses();


    @GetMapping("/misa-customers")
    MisaCustomerResponse getMisaCustomerByCustomerId(@RequestParam("Customer_id") String customerId);


    @PostMapping("/misa-receipts")
    ResponseEntity<MisaReceiptResponse> createMisaReceipt(
            @RequestBody MisaReceiptRequest receiptRequest,
            @RequestHeader("clientSecret") String clientSecret
    );

    @GetMapping("/misa-banks")
    MisaBankResponse getBanksByNumber(@RequestParam("Number") String number);

    @GetMapping("/misa-inventory-outs")
    MisaInventoryOutResponse getMisaInventoryOut();

    @GetMapping("/misa-customers")
    MisaCustomerResponse getCustomersByCustomerId(@RequestParam("Customer_id") String customerId);

    @GetMapping("/misa-inventory-outs")
    MisaInventoryOutResponse getInventoryOut(
            @RequestParam("sale_id") String saleId,
            @RequestHeader("clientSecret") String clientSecret
    );
    @GetMapping("/misa-sales")
    MisaSaleResponse getSalesByVoucher(@RequestParam("Voucher_no") String voucherNo);
    @PostMapping("/misa-inventory-ins")
    MisaInventoryInResponse createInventoryIn(
            @RequestBody MisaInventoryInRequest request,
            @RequestHeader("clientSecret") String clientSecret
    );
}
