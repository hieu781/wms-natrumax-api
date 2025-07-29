package com.natrumax.models.MockAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Inventory {
    @JsonProperty("branchId")
    private String branchId;

    @JsonProperty("branchName")
    private String branchName;

    @JsonProperty("cost")
    private int cost;

    @JsonProperty("onHand")
    private int onHand;

    @JsonProperty("reserved")
    private int reserved;

    @JsonProperty("actualReserved")
    private int actualReserved;

    @JsonProperty("minQuantity")
    private int minQuantity;

    @JsonProperty("maxQuantity")
    private int maxQuantity;

    @JsonProperty("isActive")
    private boolean isActive;

    @JsonProperty("onOrder")
    private int onOrder;

    @JsonProperty("_id")
    private String _id;

    // Getters and setters

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getOnHand() {
        return onHand;
    }

    public void setOnHand(int onHand) {
        this.onHand = onHand;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public int getActualReserved() {
        return actualReserved;
    }

    public void setActualReserved(int actualReserved) {
        this.actualReserved = actualReserved;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getOnOrder() {
        return onOrder;
    }

    public void setOnOrder(int onOrder) {
        this.onOrder = onOrder;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}

