package com.natrumax.dto.response;

import java.util.List;

public class CommissionDetailResponse {
    private int commissionId;
    private UserShortResponse referral;
    private List<CommissionPolicyResponse> commissionPolicies;

    public CommissionDetailResponse(int commissionId, UserShortResponse referral, List<CommissionPolicyResponse> commissionPolicies) {
        this.commissionId = commissionId;
        this.referral = referral;
        this.commissionPolicies = commissionPolicies;
    }

    public int getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(int commissionId) {
        this.commissionId = commissionId;
    }

    public UserShortResponse getReferral() {
        return referral;
    }

    public void setReferral(UserShortResponse referral) {
        this.referral = referral;
    }

    public List<CommissionPolicyResponse> getCommissionPolicies() {
        return commissionPolicies;
    }

    public void setCommissionPolicies(List<CommissionPolicyResponse> commissionPolicies) {
        this.commissionPolicies = commissionPolicies;
    }
}
