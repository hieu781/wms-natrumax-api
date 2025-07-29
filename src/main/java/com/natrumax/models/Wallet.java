package com.natrumax.models;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
=======
import jakarta.persistence.*;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
import java.util.List;

@Entity
@Table(name = "Wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    @Column(name = "wallet_id")
    private int walletId;
=======
    private int id;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6

    @Column(name = "balance", nullable = false)
    private double balance;

<<<<<<< HEAD
    @Column(name = "currency", nullable = false, length = 3)
    private String currency;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Transactions> transactions;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifyDate;
=======
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalletInvoice> walletInvoices;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6

    // Getters and Setters

    public Wallet() {
    }

<<<<<<< HEAD
    public Wallet(int walletId, double balance, String currency, User user, List<Transactions> transactions, LocalDateTime createDate, LocalDateTime modifyDate) {
        this.walletId = walletId;
        this.balance = balance;
        this.currency = currency;
        this.user = user;
        this.transactions = transactions;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
=======
    public Wallet(int id, double balance, User user) {
        this.id = id;
        this.balance = balance;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

<<<<<<< HEAD
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

=======
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
<<<<<<< HEAD

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
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
=======
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
}

