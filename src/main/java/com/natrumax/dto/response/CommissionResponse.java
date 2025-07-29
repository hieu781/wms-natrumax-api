package com.natrumax.dto.response;

import java.util.List;

public class CommissionResponse {
    private UserShortResponse referrer;
    private List<CommissionDetailResponse> commissions;

    public CommissionResponse(UserShortResponse referrer, List<CommissionDetailResponse> commissions) {
        this.referrer = referrer;
        this.commissions = commissions;
    }

    public UserShortResponse getReferrer() {
        return referrer;
    }

    public void setReferrer(UserShortResponse referrer) {
        this.referrer = referrer;
    }

    public List<CommissionDetailResponse> getCommissions() {
        return commissions;
    }

    public void setCommissions(List<CommissionDetailResponse> commissions) {
        this.commissions = commissions;
    }
}
