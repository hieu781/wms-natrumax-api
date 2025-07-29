package com.natrumax.dto.response;

import com.natrumax.models.Commission;

import java.time.LocalDate;

public class CommissionShortResponse {
    private int commissionId;
    private LocalDate createDate;
    private LocalDate modifyDate;
    private Long referralId;
    private Long referrerId;

    public CommissionShortResponse(Commission commission) {
        this.commissionId = commission.getCommissionId();
        this.createDate = commission.getCreateDate();
        this.modifyDate = commission.getModifyDate();
        this.referralId = commission.getReferral().getId();
        this.referrerId = commission.getReferrer().getId();
    }

    public int getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(int commissionId) {
        this.commissionId = commissionId;
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

    public Long getReferralId() {
        return referralId;
    }

    public void setReferralId(Long referralId) {
        this.referralId = referralId;
    }

    public Long getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(Long referrerId) {
        this.referrerId = referrerId;
    }
}
