package com.natrumax.models;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
=======
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Long id;

    @Column(name = "account_name", nullable = false, length = 255)
    private String accountName;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "password", length = 255)
    private String password;
=======
    private int id;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(name = "username", length = 255)
    private String username;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6

    @Column(name = "phone_number", length = 255)
    private String phoneNumber;

    @Column(name = "address", length = 255)
    private String address;

<<<<<<< HEAD
    @Column(name = "detail", columnDefinition = "JSON")
    private String detail;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifyDate;
=======
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
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

<<<<<<< HEAD
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Otp otp;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
=======
    private int otpVerification;

    private LocalDateTime otpExpirationTime;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    private List<Order> orders;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Wallet wallet;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Promotion promotion;

<<<<<<< HEAD

    @OneToMany(mappedBy = "referral", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Commission> referralCommissions;

    @OneToMany(mappedBy = "referrer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Commission> referrerCommissions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserWarehouse> userWarehouses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LotteryCode> lotteryCodes;

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserProduct> userProducts;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Banks bank;

=======
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Customer> customers;

    @OneToMany(mappedBy = "distributor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commission> distributorCommissions;

    @OneToMany(mappedBy = "referer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commission> refererCommissions;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    // Getters and Setters

    public User() {
    }

<<<<<<< HEAD
    public User(Long id, String accountName, String email, String password, String phoneNumber, String address, String detail, LocalDateTime createDate, LocalDateTime modifyDate, boolean status, Role role) {
        this.id = id;
        this.accountName = accountName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.detail = detail;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
=======
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
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
        this.status = status;
        this.role = role;
    }

<<<<<<< HEAD
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
=======
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
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
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

<<<<<<< HEAD

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
=======
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
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
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

<<<<<<< HEAD
    public Otp getOtp() {
        return otp;
    }

    public void setOtp(Otp otp) {
        this.otp = otp;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Commission> getReferralCommissions() {
        return referralCommissions;
    }

    public void setReferralCommissions(List<Commission> referralCommissions) {
        this.referralCommissions = referralCommissions;
    }

    public List<Commission> getReferrerCommissions() {
        return referrerCommissions;
    }

    public void setReferrerCommissions(List<Commission> referrerCommissions) {
        this.referrerCommissions = referrerCommissions;
    }

    public List<UserWarehouse> getUserWarehouses() {
        return userWarehouses;
    }

    public void setUserWarehouses(List<UserWarehouse> userWarehouses) {
        this.userWarehouses = userWarehouses;
    }

    public List<LotteryCode> getLotteryCodes() {
        return lotteryCodes;
    }

    public void setLotteryCodes(List<LotteryCode> lotteryCodes) {
        this.lotteryCodes = lotteryCodes;
    }

    public List<UserProduct> getUserProducts() {
        return userProducts;
    }

    public void setUserProducts(List<UserProduct> userProducts) {
        this.userProducts = userProducts;
    }

    public Banks getBank() {
        return bank;
    }

    public void setBank(Banks bank) {
        this.bank = bank;
=======
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
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    }
}
