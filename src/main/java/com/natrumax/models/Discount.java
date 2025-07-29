package com.natrumax.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.natrumax.models.Enum.EOrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private int discountId;

    @Column(name = "active_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime activeDate;

    @Column(name = "expiry_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime expiryDate;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "discount_percent", nullable = false)
    private double discountPercent;

    @Column(name = "minimun_amount", nullable = false)
    private double minimumAmount;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "discount", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Invoices> invoices;

    @OneToMany(mappedBy = "discount", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Transactions> transactions;
    // Getters and Setters


    public Discount() {
    }

    public Discount(int discountId, LocalDateTime activeDate, LocalDateTime expiryDate, String description, double discountPercent, double minimumAmount, boolean status, LocalDateTime createDate, LocalDateTime modifyDate, List<Invoices> invoices, List<Transactions> transactions) {
        this.discountId = discountId;
        this.activeDate = activeDate;
        this.expiryDate = expiryDate;
        this.description = description;
        this.discountPercent = discountPercent;
        this.minimumAmount = minimumAmount;
        this.status = status;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.invoices = invoices;
        this.transactions = transactions;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public LocalDateTime getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(LocalDateTime activeDate) {
        this.activeDate = activeDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(double minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public List<Invoices> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoices> invoices) {
        this.invoices = invoices;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }
}

