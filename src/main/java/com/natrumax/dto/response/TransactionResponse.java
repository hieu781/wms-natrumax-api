package com.natrumax.dto.response;

import com.natrumax.models.Enum.ETransactionStatus;
import com.natrumax.models.Enum.ETransactionType;

import java.time.LocalDateTime;

public class TransactionResponse {
    private int transactionsId;
    private LocalDateTime paymentDate;
    private double totalAmount;
    private String receiptCode;
    private String transferImage;
    private String refundImage;
    private ETransactionStatus status;
    private ETransactionType transaction_type;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private WalletResponse wallet;
    private BankResponse bank;

    public TransactionResponse() {
    }

    public TransactionResponse(int transactionsId, LocalDateTime paymentDate, double totalAmount, String receiptCode, String transferImage, String refundImage, ETransactionStatus status, ETransactionType transaction_type, LocalDateTime createDate, LocalDateTime modifyDate, WalletResponse wallet, BankResponse bank) {
        this.transactionsId = transactionsId;
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
        this.receiptCode = receiptCode;
        this.transferImage = transferImage;
        this.refundImage = refundImage;
        this.status = status;
        this.transaction_type = transaction_type;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.wallet = wallet;
        this.bank = bank;
    }

    public int getTransactionsId() {
        return transactionsId;
    }

    public void setTransactionsId(int transactionsId) {
        this.transactionsId = transactionsId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

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

    public String getTransferImage() {
        return transferImage;
    }

    public void setTransferImage(String transferImage) {
        this.transferImage = transferImage;
    }

    public String getRefundImage() {
        return refundImage;
    }

    public void setRefundImage(String refundImage) {
        this.refundImage = refundImage;
    }

    public ETransactionStatus getStatus() {
        return status;
    }

    public void setStatus(ETransactionStatus status) {
        this.status = status;
    }

    public ETransactionType getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(ETransactionType transaction_type) {
        this.transaction_type = transaction_type;
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

    public WalletResponse getWallet() {
        return wallet;
    }

    public void setWallet(WalletResponse wallet) {
        this.wallet = wallet;
    }

    public BankResponse getBank() {
        return bank;
    }

    public void setBank(BankResponse bank) {
        this.bank = bank;
    }
}
