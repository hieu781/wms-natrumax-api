package com.natrumax.models;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.natrumax.models.Enum.EOrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
=======
import jakarta.persistence.*;
import java.util.Date;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
import java.util.List;

@Entity
@Table(name = "Discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    @Column(name = "discount_id")
    private int discountId;

    @Column(name = "active_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime activeDate;

    @Column(name = "expiry_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime expiryDate;
=======
    private int id;

    @Column(name = "active_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date activeDate;

    @Column(name = "expiry_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "discount_percent", nullable = false)
<<<<<<< HEAD
    private double discountPercent;
=======
    private int discountPercent;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6

    @Column(name = "minimun_amount", nullable = false)
    private double minimumAmount;

<<<<<<< HEAD
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
=======
    @OneToMany(mappedBy = "discount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderInvoice> orderInvoices;

    @OneToMany(mappedBy = "discount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalletInvoice> walletInvoices;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    // Getters and Setters


    public Discount() {
    }

<<<<<<< HEAD
    public Discount(int discountId, LocalDateTime activeDate, LocalDateTime expiryDate, String description, double discountPercent, double minimumAmount, boolean status, LocalDateTime createDate, LocalDateTime modifyDate, List<Invoices> invoices, List<Transactions> transactions) {
        this.discountId = discountId;
=======
    public Discount(int id, Date activeDate, Date expiryDate, String description, int discountPercent, double minimumAmount) {
        this.id = id;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
        this.activeDate = activeDate;
        this.expiryDate = expiryDate;
        this.description = description;
        this.discountPercent = discountPercent;
        this.minimumAmount = minimumAmount;
<<<<<<< HEAD
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
=======
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
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
        this.expiryDate = expiryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

<<<<<<< HEAD
    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
=======
    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
        this.discountPercent = discountPercent;
    }

    public double getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(double minimumAmount) {
        this.minimumAmount = minimumAmount;
    }
<<<<<<< HEAD

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
=======
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
}

