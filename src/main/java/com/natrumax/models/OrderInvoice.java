package com.natrumax.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Order_invoices")
public class OrderInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "payment_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @Column(name = "payment_method", nullable = false, length = 255)
    private String paymentMethod;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @OneToOne(mappedBy = "orderInvoice", cascade = CascadeType.ALL)
    private Order order;

    // Getters and Setters


    public OrderInvoice() {
    }

    public OrderInvoice(int id, Date paymentDate, String paymentMethod, double totalAmount, Discount discount, Order order) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
