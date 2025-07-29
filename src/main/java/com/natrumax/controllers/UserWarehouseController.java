package com.natrumax.controllers;

import com.natrumax.dto.request.CreateUserWarehouseRequest;
import com.natrumax.dto.request.UpdateUserWarehouseRequest;
import com.natrumax.dto.response.WarehouseResponse;
import com.natrumax.models.UserWarehouse;
import com.natrumax.services.UserWarehouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-warehouses")
public class UserWarehouseController {

    @Autowired
    private UserWarehouseService userWarehouseService;

    // Get all UserWarehouse records
    @GetMapping
    public ResponseEntity<List<UserWarehouse>> getAllUserWarehouses() {
        return ResponseEntity.ok(userWarehouseService.getAllUserWarehouses());
    }

    // Get UserWarehouse by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserWarehouse> getUserWarehouseById(@PathVariable Long id) {
        return ResponseEntity.ok(userWarehouseService.getUserWarehouseById(id));
    }

    @PostMapping
    public ResponseEntity<UserWarehouse> createUserWarehouse(@Valid @RequestBody CreateUserWarehouseRequest request) {
        UserWarehouse createdUserWarehouse = userWarehouseService.createUserWarehouse(request);
        return ResponseEntity.status(201).body(createdUserWarehouse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserWarehouse> updateUserWarehouse(@PathVariable Long id,
                                                             @Valid @RequestBody UpdateUserWarehouseRequest request) {
        UserWarehouse updatedUserWarehouse = userWarehouseService.updateUserWarehouse(id, request);
        return ResponseEntity.ok(updatedUserWarehouse);
    }

    @GetMapping("/{userId}/warehouses")
    public List<WarehouseResponse> getWarehousesByUser(@PathVariable Long userId) {
        return userWarehouseService.getWarehousesByUserId(userId);
    }
}
