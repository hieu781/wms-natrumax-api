package com.natrumax.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "barcode", nullable = false, length = 255)
    private String barcode;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "discount", nullable = false)
    private float discount;

    @Column(name = "last_modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifyDate;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "quantity_to_get_promotion")
    private Integer quantityToGetPromotion;

    @Column(name = "status", nullable = false)
    private boolean status;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private OrderDetail orderDetail;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommissionProduct> commissionProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RedemptionCode> redemptionCodes;
    // Getters and Setters


    public Product() {
    }

    public Product(int id, String barcode, Date createDate, String description, float discount, Date lastModifyDate, String name, int quantity, Integer quantityToGetPromotion, boolean status, OrderDetail orderDetail) {
        this.id = id;
        this.barcode = barcode;
        this.createDate = createDate;
        this.description = description;
        this.discount = discount;
        this.lastModifyDate = lastModifyDate;
        this.name = name;
        this.quantity = quantity;
        this.quantityToGetPromotion = quantityToGetPromotion;
        this.status = status;
        this.orderDetail = orderDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantityToGetPromotion() {
        return quantityToGetPromotion;
    }

    public void setQuantityToGetPromotion(Integer quantityToGetPromotion) {
        this.quantityToGetPromotion = quantityToGetPromotion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
