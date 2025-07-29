package com.natrumax.controllers;

import com.natrumax.dto.request.CreateWarehouseProductRequest;
import com.natrumax.models.Product;
import com.natrumax.models.WarehouseProduct;
import com.natrumax.services.WarehouseProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouse-products")
public class WarehouseProductController {

    @Autowired
    private WarehouseProductService warehouseProductService;

    @PostMapping
    public ResponseEntity<WarehouseProduct> createWarehouseProduct(@Valid @RequestBody CreateWarehouseProductRequest request) {
        WarehouseProduct warehouseProduct = warehouseProductService.createWarehouseProduct(request);
        return ResponseEntity.ok(warehouseProduct);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Product>> getProductsByUser(@PathVariable Long userId) {
        List<Product> products = warehouseProductService.getProductsByUser(userId);
        return ResponseEntity.ok(products);
    }
}

