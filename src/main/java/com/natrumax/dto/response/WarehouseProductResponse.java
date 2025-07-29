package com.natrumax.dto.response;

import com.natrumax.models.Category;

public class WarehouseProductResponse {
    private Long productId;
    private String barcode;
    private String misaCode;
    private String name;
    private String image;
    private Category category;
    private double basePrice;
    private double discount;
    private int quantity;
    private String unit;
    private int quantityToGetPromotion;
    private String description;
    private boolean status;

    public WarehouseProductResponse(Long productId, String barcode, String name, double basePrice, double discount, int quantity, int quantityToGetPromotion, boolean status) {
        this.productId = productId;
        this.barcode = barcode;
        this.name = name;
        this.basePrice = basePrice;
        this.discount = discount;
        this.quantity = quantity;
        this.quantityToGetPromotion = quantityToGetPromotion;
        this.status = status;
    }

    public WarehouseProductResponse(Long productId, String barcode, String misaCode, String name, String image, Category category, double basePrice, double discount, int quantity, String unit, String description, int quantityToGetPromotion, boolean status) {
        this.productId = productId;
        this.barcode = barcode;
        this.misaCode = misaCode;
        this.name = name;
        this.image = image;
        this.category = category;
        this.basePrice = basePrice;
        this.discount = discount;
        this.quantity = quantity;
        this.unit = unit;
        this.quantityToGetPromotion = quantityToGetPromotion;
        this.description = description;
        this.status = status;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMisaCode() {
        return misaCode;
    }

    public void setMisaCode(String misaCode) {
        this.misaCode = misaCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityToGetPromotion() {
        return quantityToGetPromotion;
    }

    public void setQuantityToGetPromotion(int quantityToGetPromotion) {
        this.quantityToGetPromotion = quantityToGetPromotion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}