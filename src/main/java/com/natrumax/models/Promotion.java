package com.natrumax.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private int promotionId;

    @Column(name = "is_same_product", nullable = false)
    private boolean isSameProduct;

    @Column(name = "quantity_to_get_promotion", nullable = false)
    private int quantityToGetPromotion;

    @Column(name = "bonus_quantity", nullable = false)
    private int bonusQuantity;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    @JsonIgnore
    private User user;

    // Getters and Setters

    public Promotion() {
    }

    public Promotion(int promotionId, boolean isSameProduct, int quantityToGetPromotion, int bonusQuantity, User user) {
        this.promotionId = promotionId;
        this.isSameProduct = isSameProduct;
        this.quantityToGetPromotion = quantityToGetPromotion;
        this.user = user;
        this.bonusQuantity = bonusQuantity;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
