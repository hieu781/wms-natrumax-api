package com.natrumax.models;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.natrumax.models.Enum.EOrderStatus;
import com.natrumax.models.Enum.EOrderType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
=======
import jakarta.persistence.*;
import java.util.Date;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
<<<<<<< HEAD
    //Easter egg
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false)
    private EOrderType orderType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 255)
    private EOrderStatus status;

    @Column(name = "sale_code")
    private String saleCode;

    @Column(name = "inventory_out_code")
    private String inventoryOutCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
=======

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
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
<<<<<<< HEAD
    @JsonManagedReference
    private List<OrderDetail> orderDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id", referencedColumnName = "invoices_id")
    private Invoices invoices;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private  List<OrderModifyHistory> orderModifyHistories;

    public EOrderType getOrderType() {
        return orderType;
    }

    // Getters and Setters
    public Order() {
    }

    public Order(Long orderId, LocalDateTime orderDate, EOrderStatus status, String saleCode, String inventoryOutCode, LocalDateTime createdDate, LocalDateTime modifiedDate, User user, List<OrderDetail> orderDetails, Invoices invoices, List<OrderModifyHistory> orderModifyHistories) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.saleCode = saleCode;
        this.inventoryOutCode = inventoryOutCode;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.user = user;
        this.orderDetails = orderDetails;
        this.invoices = invoices;
        this.orderModifyHistories = orderModifyHistories;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderType(EOrderType orderType) {
        this.orderType = orderType;
    }

    public EOrderStatus getStatus() {
        return status;
    }

    public void setStatus(EOrderStatus status) {
        this.status = status;
    }

    public String getSaleCode() {
        return saleCode;
    }

    public void setSaleCode(String saleCode) {
        this.saleCode = saleCode;
    }

    public String getInventoryOutCode() {
        return inventoryOutCode;
    }

    public void setInventoryOutCode(String inventoryOutCode) {
        this.inventoryOutCode = inventoryOutCode;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

=======
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

>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

<<<<<<< HEAD
    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Invoices getInvoices() {
        return invoices;
    }

    public void setInvoices(Invoices invoices) {
        this.invoices = invoices;
    }

    public List<OrderModifyHistory> getOrderModifyHistories() {
        return orderModifyHistories;
    }

    public void setOrderModifyHistories(List<OrderModifyHistory> orderModifyHistories) {
        this.orderModifyHistories = orderModifyHistories;
=======
    public OrderInvoice getOrderInvoice() {
        return orderInvoice;
    }

    public void setOrderInvoice(OrderInvoice orderInvoice) {
        this.orderInvoice = orderInvoice;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    }
}
