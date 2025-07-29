package com.natrumax.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_same_product", nullable = false)
    private boolean isSameProduct;

    @Column(name = "quantity_to_get_promotion", nullable = false, length = 255)
    private String quantityToGetPromotion;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    // Getters and Setters

    public Promotion() {
    }

    public Promotion(int id, boolean isSameProduct, String quantityToGetPromotion, User user) {
        this.id = id;
        this.isSameProduct = isSameProduct;
        this.quantityToGetPromotion = quantityToGetPromotion;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSameProduct() {
        return isSameProduct;
    }

    public void setSameProduct(boolean sameProduct) {
        isSameProduct = sameProduct;
    }

    public String getQuantityToGetPromotion() {
        return quantityToGetPromotion;
    }

    public void setQuantityToGetPromotion(String quantityToGetPromotion) {
        this.quantityToGetPromotion = quantityToGetPromotion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
