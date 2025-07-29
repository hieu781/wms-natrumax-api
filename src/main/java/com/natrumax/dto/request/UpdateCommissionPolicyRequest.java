package com.natrumax.dto.request;

import java.time.LocalDate;

public class UpdateCommissionPolicyRequest {
    private double percentage;
    private LocalDate modifyDate;

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public LocalDate getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
    }
}
