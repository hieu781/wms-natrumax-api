package com.natrumax.models.MockAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MisaSaleItem {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("quantity")
    private int quantity;

    public MisaSaleItem() {
    }

    public MisaSaleItem(String code, int quantity) {
        this.code = code;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
