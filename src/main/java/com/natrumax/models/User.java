package com.natrumax.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(name = "username", length = 255)
    private String username;

    @Column(name = "phone_number", length = 255)
    private String phoneNumber;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "client_id", nullable = false, length = 255)
    private String clientId;

    @Column(name = "client_secret", nullable = false, length = 255)
    private String clientSecret;

    @Column(name = "retailer", length = 255)
    private String retailer;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "last_modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifyDate;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    private int otpVerification;

    private LocalDateTime otpExpirationTime;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Wallet wallet;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Promotion promotion;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Customer> customers;

    @OneToMany(mappedBy = "distributor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commission> distributorCommissions;

    @OneToMany(mappedBy = "referer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commission> refererCommissions;
    // Getters and Setters

    public User() {
    }

    public User(int id, String fullName, String username, String phoneNumber, String address, String clientId, String clientSecret, String retailer, Date createDate, Date lastModifyDate, boolean status, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.retailer = retailer;
        this.createDate = createDate;
        this.lastModifyDate = lastModifyDate;
        this.status = status;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public int getOtpVerification() {
        return otpVerification;
    }

    public void setOtpVerification(int otpVerification) {
        this.otpVerification = otpVerification;
    }

    public LocalDateTime getOtpExpirationTime() {
        return otpExpirationTime;
    }

    public void setOtpExpirationTime(LocalDateTime otpExpirationTime) {
        this.otpExpirationTime = otpExpirationTime;
    }
}
