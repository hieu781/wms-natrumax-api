package com.natrumax.dto.request;

public class PurchaseOrderDetailRequest {
    private Integer productId;
    private String productCode;
    private String productName;
    private Integer quantity;
    private Double price;
    private double discount;       // e.g., "10%"
    private String serialNumbers;    // e.g., "SN001, SN002, SN003"
    // Assuming you may want to include batch information.
    private ProductBatchExpireDTO productBatchExpire;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getSerialNumbers() {
        return serialNumbers;
    }

    public void setSerialNumbers(String serialNumbers) {
        this.serialNumbers = serialNumbers;
    }

    public ProductBatchExpireDTO getProductBatchExpire() {
        return productBatchExpire;
    }

    public void setProductBatchExpire(ProductBatchExpireDTO productBatchExpire) {
        this.productBatchExpire = productBatchExpire;
    }
}
