package com.natrumax.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailResponse {
    private int orderDetailId;
    private boolean isBonus;
    private int quantity;
    private double price;
    private int bonusQuantity;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private ProductResponse product;

    public OrderDetailResponse() {
    }

    public OrderDetailResponse(int orderDetailId, boolean isBonus, int quantity, double price, int bonusQuantity, LocalDateTime createDate, LocalDateTime modifyDate, ProductResponse product) {
        this.orderDetailId = orderDetailId;
        this.isBonus = isBonus;
        this.quantity = quantity;
        this.price = price;
        this.bonusQuantity = bonusQuantity;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.product = product;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public boolean isBonus() {
        return isBonus;
    }

    public void setBonus(boolean bonus) {
        isBonus = bonus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBonusQuantity() {
        return bonusQuantity;
    }

    public void setBonusQuantity(int bonusQuantity) {
        this.bonusQuantity = bonusQuantity;
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

    public ProductResponse getProduct() {
        return product;
    }

    public void setProduct(ProductResponse product) {
        this.product = product;
    }
}
