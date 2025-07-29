package com.natrumax.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.natrumax.models.Enum.EInvoiceStatus;
import com.natrumax.models.Enum.EPaymentMethod;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
public class Invoices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoices_id")
    private int invoiceId;

    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private EPaymentMethod paymentMethod;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Column(name = "transfer_image", length = 500)
    private String transferImage;

    @JsonIgnore
    private String cloudinaryTransferImageId;

    @Column(name = "refund_image", length = 500)
    private String refundImage;

    @JsonIgnore
    private String cloudinaryRefundImageId;


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 255)
    private EInvoiceStatus status;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifyDate;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @OneToOne(mappedBy = "invoices", cascade = CascadeType.ALL)
    @JsonIgnore
    private Order order;

    // Getters and Setters


    public Invoices() {
    }

    public Invoices(int invoiceId, LocalDateTime paymentDate, EPaymentMethod paymentMethod, double totalAmount, String transferImage, String cloudinaryTransferImageId, String refundImage, String cloudinaryRefundImageId, EInvoiceStatus status, LocalDateTime createDate, LocalDateTime modifyDate, Discount discount, Order order) {
        this.invoiceId = invoiceId;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.transferImage = transferImage;
        this.cloudinaryTransferImageId = cloudinaryTransferImageId;
        this.refundImage = refundImage;
        this.cloudinaryRefundImageId = cloudinaryRefundImageId;
        this.status = status;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.discount = discount;
        this.order = order;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public EPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(EPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransferImage() {
        return transferImage;
    }

    public void setTransferImage(String transferImage) {
        this.transferImage = transferImage;
    }

    public String getCloudinaryTransferImageId() {
        return cloudinaryTransferImageId;
    }

    public void setCloudinaryTransferImageId(String cloudinaryTransferImageId) {
        this.cloudinaryTransferImageId = cloudinaryTransferImageId;
    }

    public String getRefundImage() {
        return refundImage;
    }

    public void setRefundImage(String refundImage) {
        this.refundImage = refundImage;
    }

    public String getCloudinaryRefundImageId() {
        return cloudinaryRefundImageId;
    }

    public void setCloudinaryRefundImageId(String cloudinaryRefundImageId) {
        this.cloudinaryRefundImageId = cloudinaryRefundImageId;
    }

    public EInvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(EInvoiceStatus status) {
        this.status = status;
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
