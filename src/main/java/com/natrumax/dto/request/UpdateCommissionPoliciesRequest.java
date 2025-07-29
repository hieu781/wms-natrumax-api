package com.natrumax.dto.request;

import java.util.List;

public class UpdateCommissionPoliciesRequest {
    private List<CommissionDetailRequest> commissionDetails;

    public List<CommissionDetailRequest> getCommissionDetails() {
        return commissionDetails;
    }

    public void setCommissionDetails(List<CommissionDetailRequest> commissionDetails) {
        this.commissionDetails = commissionDetails;
    }
}
