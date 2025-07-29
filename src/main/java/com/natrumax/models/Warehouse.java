package com.natrumax.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "access_code", length = 255)
    private String accessCode;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "warehouse_name", length = 255)
    private String warehouseName;

//    @Column(name = "isMainWarehouse", nullable = false)
//    private boolean isMainWarehouse;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserWarehouse> userWarehouses;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "province_id") // Liên kết với Province
    private Province province;
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<WarehouseProduct> warehouseProducts;

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public List<UserWarehouse> getUserWarehouses() {
        return userWarehouses;
    }

    public void setUserWarehouses(List<UserWarehouse> userWarehouses) {
        this.userWarehouses = userWarehouses;
    }

    public List<WarehouseProduct> getWarehouseProducts() {
        return warehouseProducts;
    }

    public void setWarehouseProducts(List<WarehouseProduct> warehouseProducts) {
        this.warehouseProducts = warehouseProducts;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

//    public boolean isMainWarehouse() {
//        return isMainWarehouse;
//    }
//
//    public void setMainWarehouse(boolean mainWarehouse) {
//        isMainWarehouse = mainWarehouse;
//    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
}
