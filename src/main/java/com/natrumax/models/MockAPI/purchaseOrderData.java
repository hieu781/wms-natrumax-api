package com.natrumax.models.MockAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class purchaseOrderData {
    private String branchId;
    private String branchName;
    private LocalDateTime purchaseDate;
    private int    discountRatio;
    private double total;
    private String purchaseById;
    private String purchaseName;

    @JsonProperty("purchaseOrderDetails")
    private List<PurchaseOrderDetails> details;

    @JsonProperty("_id")
    private String id;

    private String code;
    @JsonProperty("__v")
    private int v;

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

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(int discountRatio) {
        this.discountRatio = discountRatio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPurchaseById() {
        return purchaseById;
    }

    public void setPurchaseById(String purchaseById) {
        this.purchaseById = purchaseById;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public List<PurchaseOrderDetails> getDetails() {
        return details;
    }

    public void setDetails(List<PurchaseOrderDetails> details) {
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }
}
