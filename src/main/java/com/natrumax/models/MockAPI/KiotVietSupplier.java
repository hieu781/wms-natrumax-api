package com.natrumax.models.MockAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KiotVietSupplier {

    private String address;

    @JsonProperty("locationName")
    private String locationName;

    @JsonProperty("wardName")
    private String wardName;

    @JsonProperty("organization")
    private String organization;

    @JsonProperty("taxCode")
    private String taxCode;

    @JsonProperty("comments")
    private String comments;

    @JsonProperty("groups")
    private String groups;

    @JsonProperty("isActive")
    private boolean isActive;

    @JsonProperty("modifiedDate")
    private String modifiedDate;

    @JsonProperty("createdDate")
    private String createdDate;

    @JsonProperty("retailerId")
    private int retailerId;

    @JsonProperty("branchId")
    private int branchId;

    @JsonProperty("createdBy")
    private String createdBy;

    @JsonProperty("debt")
    private long debt;

    @JsonProperty("totalInvoiced")
    private long totalInvoiced;

    @JsonProperty("totalInvoicedWithoutReturn")
    private long totalInvoicedWithoutReturn;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(int retailerId) {
        this.retailerId = retailerId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getDebt() {
        return debt;
    }

    public void setDebt(long debt) {
        this.debt = debt;
    }

    public long getTotalInvoiced() {
        return totalInvoiced;
    }

    public void setTotalInvoiced(long totalInvoiced) {
        this.totalInvoiced = totalInvoiced;
    }

    public long getTotalInvoicedWithoutReturn() {
        return totalInvoicedWithoutReturn;
    }

    public void setTotalInvoicedWithoutReturn(long totalInvoicedWithoutReturn) {
        this.totalInvoicedWithoutReturn = totalInvoicedWithoutReturn;
    }
}
