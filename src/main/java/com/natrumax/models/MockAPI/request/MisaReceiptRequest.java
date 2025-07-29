package com.natrumax.models.MockAPI.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class MisaReceiptRequest {
    @JsonProperty("Customer")
    private String Customer;
    @JsonProperty("Voucher_date")
    private Instant Voucher_date;
    @JsonProperty("Posted_date")
    private Instant Posted_date;
    @JsonProperty("Debit_account")
    private String Debit_account;
    @JsonProperty("Cash_deposit")
    private Double Cash_deposit;
    @JsonProperty("Reason")
    private String Reason;
    @JsonProperty("Item_name")
    private String Item_name;
    @JsonProperty("Amount")
    private Double Amount;
    @JsonProperty("Bank")
    private String Bank;

    public MisaReceiptRequest() {
    }

    public MisaReceiptRequest(String customer, Instant voucher_date, Instant posted_date, String debit_account, Double cash_deposit, String reason, String item_name, Double amount, String bank) {
        Customer = customer;
        Voucher_date = voucher_date;
        Posted_date = posted_date;
        Debit_account = debit_account;
        Cash_deposit = cash_deposit;
        Reason = reason;
        Item_name = item_name;
        Amount = amount;
        Bank = bank;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public Instant getVoucher_date() {
        return Voucher_date;
    }

    public void setVoucher_date(Instant voucher_date) {
        Voucher_date = voucher_date;
    }

    public Instant getPosted_date() {
        return Posted_date;
    }

    public void setPosted_date(Instant posted_date) {
        Posted_date = posted_date;
    }

    public String getDebit_account() {
        return Debit_account;
    }

    public void setDebit_account(String debit_account) {
        Debit_account = debit_account;
    }

    public Double getCash_deposit() {
        return Cash_deposit;
    }

    public void setCash_deposit(Double cash_deposit) {
        Cash_deposit = cash_deposit;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String item_name) {
        Item_name = item_name;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }
}
