package com.natrumax.dto.request;

public class CommissionDetailRequest {
    private Long commissionPolicyId;
    private double percentage;

    public Long getCommissionPolicyId() {
        return commissionPolicyId;
    }

    public void setCommissionPolicyId(Long commissionPolicyId) {
        this.commissionPolicyId = commissionPolicyId;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
