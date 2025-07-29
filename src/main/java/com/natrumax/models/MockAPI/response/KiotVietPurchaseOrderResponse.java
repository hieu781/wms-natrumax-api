package com.natrumax.models.MockAPI.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.natrumax.models.MockAPI.purchaseOrderData;

public class KiotVietPurchaseOrderResponse {
    private String message;

    @JsonProperty("data")
    private purchaseOrderData data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public purchaseOrderData getData() {
        return data;
    }

    public void setData(purchaseOrderData data) {
        this.data = data;
    }
}
