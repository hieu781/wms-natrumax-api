package com.natrumax.dto.request;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

public class CreateWalletRequest {
    @NotNull(message = "Balance cannot be null")
    @Min(value = 0, message = "Balance must be greater than 0")
    private double balance;

    @NotBlank(message = "Currency cannot be blank")
    private String currency;

    @NotNull(message = "User ID cannot be null")
    @Positive(message = "User ID must be a positive number")
    private Long userId;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
