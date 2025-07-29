package com.natrumax.dto.response;

public class ProductSoldResponse {
    private Long productId;
    private String productName;
    private int quantitySold;

    public ProductSoldResponse() {
    }

    public ProductSoldResponse(Long productId, String productName, int quantitySold) {
        this.productId = productId;
        this.productName = productName;
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

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
}
