package com.natrumax.dto.request;

import java.time.LocalDate;

public class UpdateCommissionHistoryRequest {
    private double amount;
    private LocalDate modifyDate;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
    }
}
