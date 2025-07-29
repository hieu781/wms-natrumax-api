package com.natrumax.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.*;


public class CreateCommissionHistoryRequest {
    @Positive(message = "Amount must be greater than 0")
    private double amount;

    private LocalDate createDate;

    @NotNull(message = "Commission policy ID cannot be null")
    @Positive(message = "Commission policy ID must be a positive number")
    private Long commissionPolicyId;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Long getCommissionPolicyId() {
        return commissionPolicyId;
    }

    public void setCommissionPolicyId(Long commissionPolicyId) {
        this.commissionPolicyId = commissionPolicyId;
    }
}
