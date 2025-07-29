package com.natrumax.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.natrumax.models.Enum.ETransactionStatus;
import com.natrumax.models.Enum.ETransactionType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactions_id")
    private int transactionsId;

    @Column(name = "payment_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime paymentDate;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Column(name = "receipt_code", length = 255, nullable = false)
    private String receiptCode;

    @Column(name = "transfer_image", length = 500)
    private String transferImage;

    @JsonIgnore
    private String cloudinaryTransferImageId;

    @Column(name = "refund_image", length = 500)
    private String refundImage;

    @JsonIgnore
    private String cloudinaryRefundImageId;

    @Column(name = "status", nullable = false)
    private ETransactionStatus status;

    @Column(name = "transaction_type", nullable = false)
    private ETransactionType transaction_type;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifyDate;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;
    // Getters and Setters

    public Transactions() {
    }

    public Transactions(int transactionsId, LocalDateTime paymentDate, double totalAmount, ETransactionStatus status, ETransactionType transaction_type, Discount discount, Wallet wallet) {
        this.transactionsId = transactionsId;
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.transaction_type = transaction_type;
        this.discount = discount;
        this.wallet = wallet;
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

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
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

    public String getTransferImage() {
        return transferImage;
    }

    public void setTransferImage(String transferImage) {
        this.transferImage = transferImage;
    }

    public String getCloudinaryTransferImageId() {
        return cloudinaryTransferImageId;
    }

    public void setCloudinaryTransferImageId(String cloudinaryTransferImageId) {
        this.cloudinaryTransferImageId = cloudinaryTransferImageId;
    }

    public String getRefundImage() {
        return refundImage;
    }

    public void setRefundImage(String refundImage) {
        this.refundImage = refundImage;
    }

    public String getCloudinaryRefundImageId() {
        return cloudinaryRefundImageId;
    }

    public void setCloudinaryRefundImageId(String cloudinaryRefundImageId) {
        this.cloudinaryRefundImageId = cloudinaryRefundImageId;
    }
}

