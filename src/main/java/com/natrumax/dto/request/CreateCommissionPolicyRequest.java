package com.natrumax.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.*;

public class CreateCommissionPolicyRequest {
    @Min(value = 0, message = "Percentage must be at least 0")
    @Max(value = 100, message = "Percentage cannot exceed 100")
    private double percentage;

    private LocalDate createDate;

    @Positive(message = "Commission ID must be a positive number")
    private int commissionId;

    @NotNull(message = "Category ID cannot be null")
    @Positive(message = "Category ID must be a positive number")
    private Long categoryId;

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
