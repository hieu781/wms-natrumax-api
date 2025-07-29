package com.natrumax.models;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

=======
import jakarta.persistence.*;

>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
@Entity
@Table(name = "Order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    @Column(name = "order_details_id")
    private int orderDetailId;

    @Column(name = "is_bonus", nullable = false)
    private boolean isBonus;
=======
    private int id;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6

    @Column(name = "quantity", nullable = false)
    private int quantity;

<<<<<<< HEAD
    @Column(name = "price", nullable = false)
    private double price;
    @Transient
    private int bonusQuantity;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifyDate;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
=======
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id", unique = true, nullable = false)
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    private Product product;
    // Getters and Setters


    public OrderDetail() {
    }

<<<<<<< HEAD

    public OrderDetail(int orderDetailId, boolean isBonus, int quantity, LocalDateTime createDate, LocalDateTime modifyDate, Order order, Product product) {
        this.orderDetailId = orderDetailId;
        this.isBonus = isBonus;
        this.quantity = quantity;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
=======
    public OrderDetail(int id, int quantity, Order order, Product product) {
        this.id = id;
        this.quantity = quantity;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
        this.order = order;
        this.product = product;
    }

<<<<<<< HEAD
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
=======
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

<<<<<<< HEAD
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

=======
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
<<<<<<< HEAD

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
=======
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
}

