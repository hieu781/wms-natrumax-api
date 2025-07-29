package com.natrumax.dto.response;

public class UserShortResponse {
    private Long userId;
    private String accountName;
    private String role;
    private String province;

    public UserShortResponse(Long userId, String accountName, String role, String province) {
        this.userId = userId;
        this.accountName = accountName;
        this.role = role;
        this.province = province;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
