package com.natrumax.dto.request;

import jakarta.validation.constraints.NotBlank;

public class UpdateUserWarehouseRequest {
    @NotBlank(message = "Role cannot be blank")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
