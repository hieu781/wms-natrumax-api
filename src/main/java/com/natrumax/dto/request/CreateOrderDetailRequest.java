package com.natrumax.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public class CreateOrderDetailRequest {
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
    @JsonProperty("isBonus")
    private boolean isBonus;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price must be greater than 0")
    private double price;

    @NotNull(message = "Product ID cannot be null")
    @Positive(message = "Product ID must be a positive number")
    private Long productId;

    public CreateOrderDetailRequest() {
    }

    public CreateOrderDetailRequest(int quantity, boolean isBonus, double price, Long productId) {
        this.quantity = quantity;
        this.isBonus = isBonus;
        this.price = price;
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isBonus() {
        return isBonus;
    }

    public void setBonus(boolean isBonus) {
        this.isBonus = isBonus;

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
