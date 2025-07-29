package com.natrumax.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Commissions_products")
public class CommissionProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "percentage", nullable = false)
    private float percentage;

    @ManyToOne
    @JoinColumn(name = "commission_id", nullable = false)
    private Commission commission;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Getters and Setters

    public CommissionProduct() {
    }

    public CommissionProduct(int id, float percentage, Commission commission, Product product) {
        this.id = id;
        this.percentage = percentage;
        this.commission = commission;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
