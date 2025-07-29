package com.natrumax.dto.response;

public class WarehouseResponse {
    private Long id;
    private String name;
    private String description;
    private String roleInWarehouse;
    private String accessCode;

    public WarehouseResponse() {
    }

    public WarehouseResponse(Long id, String name, String description, String roleInWarehouse, String accessCode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.roleInWarehouse = roleInWarehouse;
        this.accessCode = accessCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleInWarehouse() {
        return roleInWarehouse;
    }

    public void setRoleInWarehouse(String roleInWarehouse) {
        this.roleInWarehouse = roleInWarehouse;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
}
