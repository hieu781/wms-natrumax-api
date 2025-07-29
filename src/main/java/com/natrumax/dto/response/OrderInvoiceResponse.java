package com.natrumax.dto.response;

import com.natrumax.models.Enum.EInvoiceStatus;
import com.natrumax.models.Enum.EOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderInvoiceResponse {
    private int invoiceId;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private double totalAmount;
    private EInvoiceStatus status;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private DiscountResponse discount;

    public OrderInvoiceResponse() {
    }

    public OrderInvoiceResponse(int invoiceId, LocalDateTime paymentDate, String paymentMethod, double totalAmount, EInvoiceStatus status, LocalDateTime createDate, LocalDateTime modifyDate, DiscountResponse discount) {
        this.invoiceId = invoiceId;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.discount = discount;
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

    public DiscountResponse getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountResponse discount) {
        this.discount = discount;
    }
}
