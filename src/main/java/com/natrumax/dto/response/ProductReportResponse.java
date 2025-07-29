package com.natrumax.dto.response;

public class ProductReportResponse {
    private Long productId;
    private String productName;
    private int quantityImported;
    private int quantitySold;

    public ProductReportResponse() {
    }

    public ProductReportResponse(Long productId, String productName, int quantityImported, int quantitySold) {
        this.productId = productId;
        this.productName = productName;
        this.quantityImported = quantityImported;
        this.quantitySold = quantitySold;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantityImported() {
        return quantityImported;
    }

    public void setQuantityImported(int quantityImported) {
        this.quantityImported = quantityImported;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
}
