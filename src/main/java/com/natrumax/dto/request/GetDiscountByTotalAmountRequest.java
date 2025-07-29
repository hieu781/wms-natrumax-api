package com.natrumax.dto.request;

public class GetDiscountByTotalAmountRequest {
    private double totalAmount;

    public GetDiscountByTotalAmountRequest() {
    }

    public GetDiscountByTotalAmountRequest(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
