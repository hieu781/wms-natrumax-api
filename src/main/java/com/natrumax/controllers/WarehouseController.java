package com.natrumax.controllers;

import com.natrumax.dto.request.CreateWarehouseRequest;
import com.natrumax.dto.request.UpdateWarehouseRequest;
import com.natrumax.dto.response.MessageResponse;
import com.natrumax.dto.response.ProductResponse;
import com.natrumax.dto.response.WarehouseProductResponse;
import com.natrumax.models.Enum.ERole;
import com.natrumax.models.Product;
import com.natrumax.models.User;
import com.natrumax.models.Warehouse;
import com.natrumax.services.ProductService;
import com.natrumax.services.WarehouseProductService;
import com.natrumax.services.interfaces.IWarehouseService;
import com.natrumax.utils.AccessCodeValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private WarehouseProductService warehouseProductService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AccessCodeValidatorService accessCodeValidatorService;

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Warehouse not found"));
    }

    @PostMapping
    public ResponseEntity<?> createWarehouse(@RequestBody CreateWarehouseRequest request) {
        try {
            Warehouse warehouse = warehouseService.createWarehouse(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Warehouse created with ID: " + warehouse.getWarehouseId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWarehouse(@PathVariable Long id, @RequestBody UpdateWarehouseRequest request) {
        Warehouse updatedWarehouse = warehouseService.updateWarehouse(id, request);
        if (updatedWarehouse != null) {
            return ResponseEntity.ok(updatedWarehouse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warehouse not found");
        }
    }

//    @GetMapping("/products")
//    public ResponseEntity<?> getProductsByRoleAndProvince(@RequestParam(required = false) Integer roleId,
//                                                          @RequestParam(required = false) Long provinceId) {
//        if (roleId == null || provinceId == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new MessageResponse("Fail"));
//        }
//
//        try {
//            ERole role = mapRoleIdToEnum(roleId);
//            List<Product> products = warehouseService.getProductsByUserRoleAndProvince(role, provinceId);
//
//            if (products == null || products.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(new MessageResponse("No warehouse found for the given role and province"));
//            }
//
//            return ResponseEntity.ok(products);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new MessageResponse(e.getMessage()));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new MessageResponse("An unexpected error occurred: " + e.getMessage()));
//        }
//    }

    @GetMapping("/{warehouseId}/products")
    public ResponseEntity<List<WarehouseProductResponse>> getProductsByWarehouse(@PathVariable Long warehouseId) {
        List<WarehouseProductResponse> products = warehouseService.getProductsByWarehouse(warehouseId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{warehouseId}/category/{categoryId}")
    public ResponseEntity<?> getProductsByCategoryAndWarehouse(
            @PathVariable Long warehouseId,
            @PathVariable Long categoryId) {

        List<Product> products = warehouseProductService.getProductsByCategoryAndWarehouse(warehouseId, categoryId);

        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No product found");
        }

        return ResponseEntity.ok(products);
    }

//    private ERole mapRoleIdToEnum(int roleId) {
//        return switch (roleId) {
//            case 1 -> ERole.ROLE_CUSTOMER;
//            case 2 -> ERole.ROLE_ADMIN;
//            case 3 -> ERole.ROLE_DISTRIBUTOR;
//            case 4 -> ERole.ROLE_BRANCH_OWNER;
//            case 5 -> ERole.ROLE_ACCOUNTANT;
//            default -> throw new IllegalArgumentException("Invalid roleId: " + roleId);
//        };
//    }

    @GetMapping("/{warehouseId}/products/{productId}")
    public ResponseEntity<?> getProductDetailByWarehouse(
            @PathVariable Long warehouseId,
            @PathVariable Long productId) {

        ProductResponse response = productService.getProductByProductIdAndWarehouseId(warehouseId, productId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/owner-by-member/{memberId}")
    public ResponseEntity<User> getWarehouseOwnerByMember(@PathVariable Long memberId) {
        User owner = warehouseService.getWarehouseOwnerByMemberId(memberId);
        return ResponseEntity.ok(owner);
    }
}
