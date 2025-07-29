package com.natrumax.dto.request;

import java.time.LocalDateTime;

public class ProductBatchExpireDTO {
    private Integer id;
    private Integer productId;
    private String batchName;
    private String fullNameVirgule;
    private LocalDateTime createdDate;
    private LocalDateTime expireDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getFullNameVirgule() {
        return fullNameVirgule;
    }

    public void setFullNameVirgule(String fullNameVirgule) {
        this.fullNameVirgule = fullNameVirgule;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }
}
