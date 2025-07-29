package com.natrumax.models.MockAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InventoryInItem {
    @JsonProperty("Code")
    private String code;

    @JsonProperty("Quantity")
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
