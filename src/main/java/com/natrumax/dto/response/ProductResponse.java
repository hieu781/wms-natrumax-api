package com.natrumax.dto.response;

import java.time.LocalDateTime;

public class ProductResponse {
    private Long productId;
    private String barcode;
    private String misaCode;
    private String name;
    private String image;
    private String category;
    private double basePrice;
    private double discount;
    private int quantity;
    private String unit;
    private Integer quantityToGetPromotion;
    private String description;
    private boolean status;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public ProductResponse() {
    }

    public ProductResponse(Long productId, String barcode, String misaCode, String name, String image, String category, double basePrice, double discount, int quantity, String unit, Integer quantityToGetPromotion, String description, boolean status) {
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

    public ProductResponse(Long productId, String barcode, String misaCode, String name, String image, double basePrice, float discount, int quantity, String unit, Integer quantityToGetPromotion, String description, boolean status, LocalDateTime createDate, LocalDateTime modifyDate) {
        this.productId = productId;
        this.barcode = barcode;
        this.misaCode = misaCode;
        this.name = name;
        this.image = image;
        this.basePrice = basePrice;
        this.discount = discount;
        this.quantity = quantity;
        this.unit = unit;
        this.quantityToGetPromotion = quantityToGetPromotion;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getQuantityToGetPromotion() {
        return quantityToGetPromotion;
    }

    public void setQuantityToGetPromotion(Integer quantityToGetPromotion) {
        this.quantityToGetPromotion = quantityToGetPromotion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
}
