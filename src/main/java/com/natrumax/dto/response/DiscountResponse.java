package com.natrumax.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DiscountResponse {

    private Long discountId;
    private double discountPercentage;
    private BigDecimal minimumAmount;
    private LocalDateTime activeDate;
    private LocalDateTime expiryDate;
    private String description;
    private boolean status;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;


    public DiscountResponse(Long discountId, double discountPercentage, BigDecimal minimumAmount, LocalDateTime activeDate, LocalDateTime expiryDate, String description, boolean status, LocalDateTime createDate, LocalDateTime modifyDate) {
        this.discountId = discountId;
        this.discountPercentage = discountPercentage;
        this.minimumAmount = minimumAmount;
        this.activeDate = activeDate;
        this.expiryDate = expiryDate;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public DiscountResponse() {
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(BigDecimal minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
}
