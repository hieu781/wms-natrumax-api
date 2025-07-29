package com.natrumax.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CommissionHistoriesDetailCreateRequest {

    @Min(value = 0, message = "Percentage must be at least 0")
    @Max(value = 100, message = "Percentage cannot exceed 100")
    private double percentage;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 0, message = "Amount must be greater than 0")
    private double amount;

    private LocalDateTime createDate;

    @NotNull(message = "Commission history ID cannot be null")
    @Positive(message = "Commission history ID must be a positive number")
    private Long commissionHistoryId;

    @NotNull(message = "Commission policy ID cannot be null")
    @Positive(message = "Commission policy ID must be a positive number")
    private Long commissionPolicyId;

    @NotNull(message = "Category ID cannot be null")
    @Positive(message = "Category ID must be a positive number")
    private Long categoryId;

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCommissionHistoryId() {
        return commissionHistoryId;
    }

    public void setCommissionHistoryId(Long commissionHistoryId) {
        this.commissionHistoryId = commissionHistoryId;
    }

    public Long getCommissionPolicyId() {
        return commissionPolicyId;
    }

    public void setCommissionPolicyId(Long commissionPolicyId) {
        this.commissionPolicyId = commissionPolicyId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
