package com.natrumax.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Redemption_codes")
public class RedemptionCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code", length = 255)
    private String code;

    @Column(name = "expiry_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "reward_id", nullable = false)
    private Reward reward;
    // Getters and Setters

    public RedemptionCode() {
    }

    public RedemptionCode(int id, String code, Date expiryDate, Date updateDate, Customer customer, Product product, Reward reward) {
        this.id = id;
        this.code = code;
        this.expiryDate = expiryDate;
        this.updateDate = updateDate;
        this.customer = customer;
        this.product = product;
        this.reward = reward;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }
}

