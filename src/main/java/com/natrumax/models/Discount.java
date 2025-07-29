package com.natrumax.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "active_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date activeDate;

    @Column(name = "expiry_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "discount_percent", nullable = false)
    private int discountPercent;

    @Column(name = "minimun_amount", nullable = false)
    private double minimumAmount;

    @OneToMany(mappedBy = "discount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderInvoice> orderInvoices;

    @OneToMany(mappedBy = "discount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalletInvoice> walletInvoices;
    // Getters and Setters


    public Discount() {
    }

    public Discount(int id, Date activeDate, Date expiryDate, String description, int discountPercent, double minimumAmount) {
        this.id = id;
        this.activeDate = activeDate;
        this.expiryDate = expiryDate;
        this.description = description;
        this.discountPercent = discountPercent;
        this.minimumAmount = minimumAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(double minimumAmount) {
        this.minimumAmount = minimumAmount;
    }
}

