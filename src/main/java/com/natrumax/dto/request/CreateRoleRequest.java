package com.natrumax.dto.request;

import com.natrumax.models.Enum.ERole;

public class CreateRoleRequest {
    private ERole name;

    private String description;

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
