package com.natrumax.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "balance", nullable = false)
    private double balance;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WalletInvoice> walletInvoices;

    // Getters and Setters

    public Wallet() {
    }

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
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

