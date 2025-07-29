package com.natrumax.models.MockAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KiotVietBranch {
    @JsonProperty("_id")
    private String id;

    @JsonProperty("branchName")
    private String branchName;

    @JsonProperty("address")
    private String address;

    @JsonProperty("locationName")
    private String locationName;

    @JsonProperty("contactNumber")
    private String contactNumber;

    @JsonProperty("retailerId")
    private String retailerId;

    @JsonProperty("modifiedDate")
    private String modifiedDate;

    @JsonProperty("createdDate")
    private String createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
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
}
