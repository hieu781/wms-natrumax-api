package com.natrumax.dto.request;

import com.natrumax.models.Enum.EOrderStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateDiscountRequest {
    @NotNull(message = "Active date cannot be null")
    @FutureOrPresent(message = "Active date must be in the present or future")
    private LocalDateTime activeDate;

    @NotNull(message = "Expiry date cannot be null")
    @Future(message = "Expiry date must be in the future")
    private LocalDateTime expiryDate;

    @NotBlank(message = "Description cannot be empty")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    @Min(value = 0, message = "Discount percent must be at least 1%")
    @Max(value = 100, message = "Discount percent cannot exceed 100%")
    private double discountPercent;

    @Min(value = 0, message = "Minimum amount cannot be negative")
    private double minimumAmount;

    @NotNull(message = "Status cannot be null")
    private boolean status;

    public LocalDateTime getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(LocalDateTime activeDate) {
        this.activeDate = activeDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(double minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
