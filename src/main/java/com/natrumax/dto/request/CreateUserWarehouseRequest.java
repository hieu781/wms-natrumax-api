package com.natrumax.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateUserWarehouseRequest {
    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Warehouse ID cannot be null")
    private Long warehouseId;

    @NotBlank(message = "Role cannot be blank")
    private String role;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
