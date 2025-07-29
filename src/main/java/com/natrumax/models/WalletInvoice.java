package com.natrumax.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Wallet_invoices")
public class WalletInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "payment_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;
    // Getters and Setters

    public WalletInvoice() {
    }

    public WalletInvoice(int id, Date paymentDate, double totalAmount, Discount discount, Wallet wallet) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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
}

