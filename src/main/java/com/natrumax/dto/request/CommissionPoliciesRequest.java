package com.natrumax.dto.request;

import java.util.List;

public class CommissionPoliciesRequest {
    private Long referrerId;
    private Long referralId;
    private List<PolicyRequest> policies;

    public CommissionPoliciesRequest(Long referrerId, Long referralId, List<PolicyRequest> policies) {
        this.referrerId = referrerId;
        this.referralId = referralId;
        this.policies = policies;
    }

    public Long getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(Long referrerId) {
        this.referrerId = referrerId;
    }

    public Long getReferralId() {
        return referralId;
    }

    public void setReferralId(Long referralId) {
        this.referralId = referralId;
    }

    public List<PolicyRequest> getPolicies() {
        return policies;
    }

    public void setPolicies(List<PolicyRequest> policies) {
        this.policies = policies;
    }

    public static class PolicyRequest {
        private Long categoryId;
        private float percentage;

        public PolicyRequest(Long categoryId, float percentage) {
            this.categoryId = categoryId;
            this.percentage = percentage;
        }

        public Long getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
        }

        public float getPercentage() {
            return percentage;
        }

        public void setPercentage(float percentage) {
            this.percentage = percentage;
        }
    }
}
