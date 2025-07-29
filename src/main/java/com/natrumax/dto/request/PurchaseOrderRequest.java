package com.natrumax.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseOrderRequest {
    private String code; // e.g., "PO-20250328-001"
    private Integer branchId; // e.g., 5
    private String branchName; // e.g., "Chi nhánh Hà Nội"
    private LocalDateTime purchaseDate; // e.g., ZonedDateTime.now() in ISO 8601 format
    private double discountRatio; // e.g., 5
    private Double total; // e.g., total amount of purchase order
    private Integer supplierId; // e.g., 101
    private String supplierName; // e.g., "Công Ty TNHH Thương Mại ABC"
    private String supplierCode; // e.g., "SUP-001"
    private String partnerType; // e.g., "Nhà cung cấp"
    private Integer purchaseById; // e.g., 2001
    private String purchaseName; // e.g., "Nguyễn Văn A"
    private List<PurchaseOrderDetailRequest> purchaseOrderDetails;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(double discountRatio) {
        this.discountRatio = discountRatio;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }

    public Integer getPurchaseById() {
        return purchaseById;
    }

    public void setPurchaseById(Integer purchaseById) {
        this.purchaseById = purchaseById;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public List<PurchaseOrderDetailRequest> getPurchaseOrderDetails() {
        return purchaseOrderDetails;
    }

    public void setPurchaseOrderDetails(List<PurchaseOrderDetailRequest> purchaseOrderDetails) {
        this.purchaseOrderDetails = purchaseOrderDetails;
    }
}
