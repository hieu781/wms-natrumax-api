package com.natrumax.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.*;

public class CreateCommissionRequest {

    private LocalDate createDate;

    @NotNull(message = "Referral ID cannot be null")
    @Positive(message = "Referral ID must be a positive number")
    private Long referralId;

    @NotNull(message = "Referrer ID cannot be null")
    @Positive(message = "Referrer ID must be a positive number")
    private Long referrerId;

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
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
