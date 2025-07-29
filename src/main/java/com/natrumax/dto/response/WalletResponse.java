package com.natrumax.dto.response;

import java.time.LocalDateTime;

public class WalletResponse {
    private int walletId;
    private double balance;
    private String currency;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public WalletResponse() {
    }

    public WalletResponse(int walletId, double balance, String currency, LocalDateTime createDate, LocalDateTime modifyDate) {
        this.walletId = walletId;
        this.balance = balance;
        this.currency = currency;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

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
