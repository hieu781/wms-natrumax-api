package com.natrumax.dto.response;

import org.springframework.http.ResponseCookie;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class LoginResponse {
    private String token;
    private Long id;
    private String accountName;
    private String phoneNumber;
    private String email;
    private String password;
    private String address;
    private String detail;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private boolean status;
    private List<String> roles;

    public LoginResponse(String token, Long id, String accountName, String phoneNumber, String email,
                         String address, String detail, LocalDateTime createDate,
                         LocalDateTime modifyDate, boolean status, List<String> roles) {
        this.token = token;
        this.id = id;
        this.accountName = accountName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.detail = detail;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.status = status;
        this.roles = roles;
    }

    // Getters and Setters


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
