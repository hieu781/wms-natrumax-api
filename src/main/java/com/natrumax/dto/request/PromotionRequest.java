package com.natrumax.dto.request;

import jakarta.validation.constraints.*;

public class PromotionRequest {
    @NotNull(message = "User ID cannot be null")
    @Positive(message = "User ID must be a positive number")
    private Long userId;

    @NotBlank(message = "Product cannot be blank")
    private String product;

    @Min(value = 1, message = "Quantity bought must be at least 1")
    private int quantityBought;

    @Min(value = 0, message = "Bonus quantity cannot be negative")
    private int bonusQuantity;

    private boolean isSameProduct; // No validation needed for boolean

    @Min(value = 0, message = "Quantity to get promotion must be greater or equal 0")
    private int quantityToGetPromotion;
    public PromotionRequest() {
    }

    public PromotionRequest(Long userId, String product, int quantityBought) {
        this.userId = userId;
        this.product = product;
        this.quantityBought = quantityBought;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantityBought() {
        return quantityBought;
    }

    public void setQuantityBought(int quantityBought) {
        this.quantityBought = quantityBought;
    }

    public boolean isSameProduct() {
        return isSameProduct;
    }

    public void setSameProduct(boolean sameProduct) {
        isSameProduct = sameProduct;
    }

    public int getQuantityToGetPromotion() {
        return quantityToGetPromotion;
    }

    public void setQuantityToGetPromotion(int quantityToGetPromotion) {
        this.quantityToGetPromotion = quantityToGetPromotion;
    }

    public int getBonusQuantity() {
        return bonusQuantity;
    }

    public void setBonusQuantity(int bonusQuantity) {
        this.bonusQuantity = bonusQuantity;
    }
}
