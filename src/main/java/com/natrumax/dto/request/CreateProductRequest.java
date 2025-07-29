package com.natrumax.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CreateProductRequest {
    @NotBlank(message = "Barcode is required")
    private String barcode;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Base price is required")
    @Min(value = 0, message = "Base price must be non-negative")
    private Double basePrice;

    private String image;
    private Float discount = 0f;

    private Integer quantityToGetPromotion;
    private Boolean status = true;

    @NotBlank(message = "Unit is required")
    private String unit;

    @NotBlank(message = "Misa Code is required")
    private String misaCode;

    @NotNull(message = "Category ID is required")
    private Long categoryId;
    private LocalDateTime createDate;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getQuantityToGetPromotion() {
        return quantityToGetPromotion;
    }

    public void setQuantityToGetPromotion(Integer quantityToGetPromotion) {
        this.quantityToGetPromotion = quantityToGetPromotion;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMisaCode() {
        return misaCode;
    }

    public void setMisaCode(String misaCode) {
        this.misaCode = misaCode;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
