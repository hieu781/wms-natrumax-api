package com.natrumax.dto.request;

import com.natrumax.models.Enum.ERole;

public class UpdateRoleRequest {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
