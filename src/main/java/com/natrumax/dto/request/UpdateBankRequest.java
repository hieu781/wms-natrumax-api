package com.natrumax.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateBankRequest {
    @NotBlank(message = "Bank name must not be blank")
    @Size(max = 255, message = "Bank name must not exceed 255 characters")
    private String bankName;

    @NotBlank(message = "Account name must not be blank")
    @Size(max = 255, message = "Account name must not exceed 255 characters")
    private String accountName;

    @NotBlank(message = "Account number must not be blank")
    @Size(max = 20, message = "Account number must not exceed 20 characters")
    private String accountNo;

    @NotBlank(message = "Bank code must not be blank")
    @Size(max = 20, message = "Account number must not exceed 20 characters")
    private String bankCode;

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

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
