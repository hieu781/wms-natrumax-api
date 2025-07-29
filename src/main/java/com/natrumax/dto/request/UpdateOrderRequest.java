package com.natrumax.dto.request;

public class UpdateOrderRequest {
    private String inventoryOutCode;
    private String saleCode;
    private String title;

    public String getInventoryOutCode() {
        return inventoryOutCode;
    }

    public void setInventoryOutCode(String inventoryOutCode) {
        this.inventoryOutCode = inventoryOutCode;
    }

    public String getSaleCode() {
        return saleCode;
    }

    public void setSaleCode(String saleCode) {
        this.saleCode = saleCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
