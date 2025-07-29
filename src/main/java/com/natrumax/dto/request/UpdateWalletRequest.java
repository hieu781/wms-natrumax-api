package com.natrumax.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class UpdateWalletRequest {
    @NotBlank(message = "Balance cannot be empty")
    private Double balance;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
