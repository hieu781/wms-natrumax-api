package com.natrumax.models.MockAPI.request;

import java.util.List;

public class KVPurchaseOrderRequest {
    private String purchaseDate;
    private int    discountRatio;
    private List<KVPurchaseOrderRequest> purchaseOrderDetails;

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(int discountRatio) {
        this.discountRatio = discountRatio;
    }

    public List<KVPurchaseOrderRequest> getPurchaseOrderDetails() {
        return purchaseOrderDetails;
    }

    public void setPurchaseOrderDetails(List<KVPurchaseOrderRequest> purchaseOrderDetails) {
        this.purchaseOrderDetails = purchaseOrderDetails;
    }
}
