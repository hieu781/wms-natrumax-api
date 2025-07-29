package com.natrumax.dto.response;

public class BankResponse {
    private Long bankId;
    private String bankName;
    private String accountName;
    private String accountNo;

    public BankResponse() {
    }

    public BankResponse(String bankName, String accountName, String accountNo) {
        this.bankName = bankName;
        this.accountName = accountName;
        this.accountNo = accountNo;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
