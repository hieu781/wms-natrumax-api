package com.natrumax.dto.response;

public class CommissionPolicyResponse {
    private Long commissionPolicyId;
    private String categoryName;
    private double percentage;

    public CommissionPolicyResponse(Long commissionPolicyId, String categoryName, double percentage) {
        this.commissionPolicyId = commissionPolicyId;
        this.categoryName = categoryName;
        this.percentage = percentage;
    }

    public Long getCommissionPolicyId() {
        return commissionPolicyId;
    }

    public void setCommissionPolicyId(Long commissionPolicyId) {
        this.commissionPolicyId = commissionPolicyId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
