package com.natrumax.dto.request;

import jakarta.validation.constraints.NotNull;

public class WarehouseRequest {
    @NotNull(message = "RoleId required")
    private int roleId;
    @NotNull(message = "ProvinceId required")
    private Long provinceId;

    public WarehouseRequest(int roleId, Long provinceId) {
        this.roleId = roleId;
        this.provinceId = provinceId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
}
