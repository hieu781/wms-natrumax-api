package com.natrumax.models.MockAPI;

public class SaleResponse {
    private String message;
    private SaleData sale;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SaleData getSale() {
        return sale;
    }

    public void setSale(SaleData sale) {
        this.sale = sale;
    }
}
