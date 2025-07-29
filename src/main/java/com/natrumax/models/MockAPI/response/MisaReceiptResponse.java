package com.natrumax.models.MockAPI.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MisaReceiptResponse {

    private String message;
    private Receipt receipt;

    public MisaReceiptResponse(String message, Receipt receipt) {
        this.message = message;
        this.receipt = receipt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    @Data
    public static class Receipt {
        private String Voucher_date;
        @JsonProperty("Voucher_no")
        private String Voucher_no;
        private String Posted_date;
        private String Debit_account;
        private String Cash_deposit;
        private String Reason;
        private String Item_name;
        private AmountWrapper Amount;
        private String Bank;
        private String _id;

        public Receipt(String voucher_date, String voucher_no, String posted_date, String debit_account, String cash_deposit, String reason, String item_name, AmountWrapper amount, String bank, String _id) {
            Voucher_date = voucher_date;
            Voucher_no = voucher_no;
            Posted_date = posted_date;
            Debit_account = debit_account;
            Cash_deposit = cash_deposit;
            Reason = reason;
            Item_name = item_name;
            Amount = amount;
            Bank = bank;
            this._id = _id;
        }

        @Data
        public static class AmountWrapper {
            private String $numberDecimal;
        }

        public String getVoucher_date() {
            return Voucher_date;
        }

        public void setVoucher_date(String voucher_date) {
            Voucher_date = voucher_date;
        }

        public String getVoucher_no() {
            return Voucher_no;
        }

        public void setVoucher_no(String voucher_no) {
            Voucher_no = voucher_no;
        }

        public String getPosted_date() {
            return Posted_date;
        }

        public void setPosted_date(String posted_date) {
            Posted_date = posted_date;
        }

        public String getDebit_account() {
            return Debit_account;
        }

        public void setDebit_account(String debit_account) {
            Debit_account = debit_account;
        }

        public String getCash_deposit() {
            return Cash_deposit;
        }

        public void setCash_deposit(String cash_deposit) {
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

        public AmountWrapper getAmount() {
            return Amount;
        }

        public void setAmount(AmountWrapper amount) {
            Amount = amount;
        }

        public String getBank() {
            return Bank;
        }

        public void setBank(String bank) {
            Bank = bank;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }
    }
}
