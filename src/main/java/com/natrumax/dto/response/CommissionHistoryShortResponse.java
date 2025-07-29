package com.natrumax.dto.response;

import com.natrumax.models.CommissionHistories;

import java.time.LocalDate;

public class CommissionHistoryShortResponse {
    private Long id;
    private double amount;
    private LocalDate createDate;
    private LocalDate modifyDate;
    private Long commissionPolicyId;

    public CommissionHistoryShortResponse(CommissionHistories history) {
        this.id = history.getCommissionHistoriesId();
        this.amount = history.getTotalAmount();
        this.createDate = history.getCreateDate();
        this.modifyDate = history.getModifyDate();
//        this.commissionPolicyId = history.getCommissionPolicy().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getCommissionPolicyId() {
        return commissionPolicyId;
    }

    public void setCommissionPolicyId(Long commissionPolicyId) {
        this.commissionPolicyId = commissionPolicyId;
    }
}
