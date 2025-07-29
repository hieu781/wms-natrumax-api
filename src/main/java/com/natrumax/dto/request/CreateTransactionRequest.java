package com.natrumax.dto.request;

import com.natrumax.models.Enum.ETransactionStatus;
import com.natrumax.models.Enum.ETransactionType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class CreateTransactionRequest {
    @NotNull(message = "Total amount cannot be null")
    @Min(value = 0, message = "Total amount must be greater than 0")
    private double totalAmount;

    private String receiptCode;

    @NotNull(message = "Transaction status cannot be null")
    private ETransactionStatus status;

    @NotNull(message = "Transaction type cannot be null")
    private ETransactionType transactionType;

    @NotNull(message = "Wallet ID cannot be null")
    @Positive(message = "Wallet ID must be a positive number")
    private Integer walletId;

    @NotNull(message = "Discount ID cannot be null")
    @Positive(message = "Discount ID must be a positive number")
    private Integer discountId;

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public ETransactionStatus getStatus() {
        return status;
    }

    public void setStatus(ETransactionStatus status) {
        this.status = status;
    }

    public ETransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(ETransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }
}
