package com.natrumax.models.MockAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InventoryOutItem {
    @JsonProperty("Good_id")
    private String goodId;
    @JsonProperty("Quantity")
    private String quantity;
    @JsonProperty("Status")
    private String status;

    public InventoryOutItem() {
    }

    public InventoryOutItem(String goodId, String quantity, String status) {
        this.goodId = goodId;
        this.quantity = quantity;
        this.status = status;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
