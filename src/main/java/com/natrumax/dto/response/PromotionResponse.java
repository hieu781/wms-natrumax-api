package com.natrumax.dto.response;

public class PromotionResponse {
    private int quantityBought;
    private int quantityToGetPromotion;
    private boolean isSameProduct;
    private int bonusQuantity;

    public PromotionResponse(int quantityBought, int quantityToGetPromotion, boolean isSameProduct, int bonusQuantity) {
        this.quantityBought = quantityBought;
        this.quantityToGetPromotion = quantityToGetPromotion;
        this.isSameProduct = isSameProduct;
        this.bonusQuantity = bonusQuantity;
    }


    // Getters và Setters

    public int getQuantityBought() {
        return quantityBought;
    }

    public void setQuantityBought(int quantityBought) {
        this.quantityBought = quantityBought;
    }

    public int getQuantityToGetPromotion() {
        return quantityToGetPromotion;
    }

    public void setQuantityToGetPromotion(int quantityToGetPromotion) {
        this.quantityToGetPromotion = quantityToGetPromotion;
    }

    public boolean isSameProduct() {
        return isSameProduct;
    }

    public void setSameProduct(boolean sameProduct) {
        isSameProduct = sameProduct;
    }

    public int getBonusQuantity() {
        return bonusQuantity;
    }

    public void setBonusQuantity(int bonusQuantity) {
        this.bonusQuantity = bonusQuantity;
    }
}
