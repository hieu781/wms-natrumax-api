package com.natrumax.models.MockAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MisaCustomer {
    @JsonProperty("_id")
    private String _id;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("Liabilities")
    private String liability;

    @JsonProperty("Customer_id")
    private String customerId;

    @JsonProperty("Accounts_receivable")
    private String accountsReceivable;

    @JsonProperty("Customer_name")
    private String customerName;

    @JsonProperty("Phone_number")
    private String phoneNumber;

    @JsonProperty("Customer_group")
    private String customerGroup;

    @JsonProperty("Role")
    private String Role;

    @JsonProperty("warehouse_id")
    private String warehouseId;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLiability() {
        return liability;
    }

    public void setLiability(String liability) {
        this.liability = liability;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountsReceivable() {
        return accountsReceivable;
    }

    public void setAccountsReceivable(String accountsReceivable) {
        this.accountsReceivable = accountsReceivable;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }
}
