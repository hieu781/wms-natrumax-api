package com.natrumax.dto.request;

import jakarta.validation.constraints.*;

public class UpdateProductRequest {
    @NotBlank(message = "Barcode cannot be empty")
    @Size(max = 50, message = "Barcode must not exceed 50 characters")
    private String barcode;

    @NotBlank(message = "Product name cannot be empty")
    @Size(max = 100, message = "Product name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @Size(max = 255, message = "Image URL must not exceed 255 characters")
    private String image;

    @Min(value = 0, message = "Discount cannot be negative")
    @Max(value = 100, message = "Discount cannot exceed 100")
    private Double discount;

    @Min(value = 0, message = "Quantity required for promotion cannot be negative")
    private Integer quantityToGetPromotion;

    @NotNull(message = "Status cannot be null")
    private Boolean status;

    @NotBlank(message = "Unit cannot be null")
    private String unit;

    @NotNull(message = "Category ID cannot be null")
    @Positive(message = "Category ID must be a positive number")
    private Long categoryId;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
