package com.natrumax.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Lottery_codes")
public class LotteryCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "redemption_code_id")
    private int redemtionCodeId;

    @Column(name = "code", length = 255)
    private String code;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "modify_date")
    private LocalDate modifyDate;

    @Column(name = "price")
    private Double price;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "place")
    private String place;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "reward_id")
    @JsonIgnore
    private Reward reward;

    @ManyToOne
    @JoinColumn(name = "user_id") // FK to User
    private User user;
    // Getters and Setters


    public int getRedemtionCodeId() {
        return redemtionCodeId;
    }

    public void setRedemtionCodeId(int redemtionCodeId) {
        this.redemtionCodeId = redemtionCodeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getPrice() {
        return price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}

