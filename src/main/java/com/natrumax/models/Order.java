package com.natrumax.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "invoice_id", nullable = false, insertable = false, updatable = false)
    private int invoiceId;

    @Column(name = "order_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column(name = "status", nullable = false, length = 255)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

    @OneToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    private OrderInvoice orderInvoice;

    // Getters and Setters

    public Order() {
    }

    public Order(int id, int invoiceId, Date orderDate, String status, User user, OrderInvoice orderInvoice) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.orderDate = orderDate;
        this.status = status;
        this.user = user;
        this.orderInvoice = orderInvoice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderInvoice getOrderInvoice() {
        return orderInvoice;
    }

    public void setOrderInvoice(OrderInvoice orderInvoice) {
        this.orderInvoice = orderInvoice;
    }
}
