package com.natrumax.dto.response;

import com.natrumax.models.CommissionPolicies;

import java.time.LocalDate;

public class CommissionPolicyShortResponse {
    private Long id;
    private double percentage;
    private LocalDate createDate;
    private LocalDate modifyDate;
    private int commissionId;
    private Long categoryId;

    public CommissionPolicyShortResponse(CommissionPolicies policy) {
        this.id = policy.getCommissionPoliciesId();
        this.percentage = policy.getPercentage();
        this.createDate = policy.getCreateDate();
        this.modifyDate = policy.getModifyDate();
        this.commissionId = policy.getCommission().getCommissionId();
        this.categoryId = policy.getCategory().getCategoryId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
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

    public int getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(int commissionId) {
        this.commissionId = commissionId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
