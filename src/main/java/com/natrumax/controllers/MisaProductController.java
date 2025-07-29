package com.natrumax.controllers;

import com.natrumax.dto.response.ProductResponse;
import com.natrumax.models.MockAPI.MisaGood;
import com.natrumax.models.Product;
import com.natrumax.repository.WarehouseProductRepository;
import com.natrumax.services.APIService.MisaService;
import com.natrumax.services.ProductService;
import com.natrumax.utils.AccessCodeValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/misa-products")
public class MisaProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private final MisaService misaService;

    @Autowired
    private WarehouseProductRepository warehouseProductRepository;

    @Autowired
    private AccessCodeValidatorService accessCodeValidatorService;

    @Autowired
    public MisaProductController(ProductService productService, MisaService misaService) {
        this.productService = productService;
        this.misaService = misaService;
    }

    @GetMapping("/misa")
    public ResponseEntity<List<MisaGood>> getAllMisaGoods() {
        return ResponseEntity.ok(misaService.getAllMisaGoods());
    }

    @GetMapping("/misa/{code}")
    public ResponseEntity<?> getMisaGoodByCode(@PathVariable String code) {
        Optional<MisaGood> misaGood = misaService.getMisaGoodByCode(code);
        if (misaGood.isPresent()) {
            return ResponseEntity.ok(misaGood.get());
        } else {
            // Return 404 with custom message in the body
            Map<String, String> response = new HashMap<>();
            response.put("Message", "Not found product in MISA");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/import-from-misa")
    public ResponseEntity<Product> importFromMisa(
            @RequestParam String misaCode,
            @RequestParam Long categoryId,
            @RequestParam String barcode) {
        Product product = productService.importProductFromMisa(misaCode, categoryId, barcode);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/warehouse/{warehouseId}/sync")
    public ResponseEntity<?> syncWarehouseProductsFromMisa(@PathVariable Long warehouseId) {
        productService.updateWarehouseProductsFromMisa(warehouseId);
        return ResponseEntity.ok("Warehouse products updated from MISA successfully");
    }

//    @GetMapping("/warehouse/{warehouseId}")
//    public ResponseEntity<?> getProductListByWarehouseId(
//            @PathVariable Long warehouseId) {
//
//        List<ProductResponse> products = productService.getProductListByWarehouseId(warehouseId);
//        return ResponseEntity.ok(products);
//    }
}
