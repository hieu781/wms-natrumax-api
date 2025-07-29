package com.natrumax.models;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
=======
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
import jakarta.persistence.*;

@Entity
@Table(name = "Promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    @Column(name = "promotion_id")
    private int promotionId;
=======
    private int id;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6

    @Column(name = "is_same_product", nullable = false)
    private boolean isSameProduct;

<<<<<<< HEAD
    @Column(name = "quantity_to_get_promotion", nullable = false)
    private int quantityToGetPromotion;

    @Column(name = "bonus_quantity", nullable = false)
    private int bonusQuantity;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    @JsonIgnore
=======
    @Column(name = "quantity_to_get_promotion", nullable = false, length = 255)
    private String quantityToGetPromotion;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    private User user;

    // Getters and Setters

    public Promotion() {
    }

<<<<<<< HEAD
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
=======
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
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    }

    public boolean isSameProduct() {
        return isSameProduct;
    }

    public void setSameProduct(boolean sameProduct) {
        isSameProduct = sameProduct;
    }

<<<<<<< HEAD
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

=======
    public String getQuantityToGetPromotion() {
        return quantityToGetPromotion;
    }

    public void setQuantityToGetPromotion(String quantityToGetPromotion) {
        this.quantityToGetPromotion = quantityToGetPromotion;
    }

>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
