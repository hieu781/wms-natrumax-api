package com.natrumax.dto.response;

import com.natrumax.models.Enum.EInvoiceStatus;
import com.natrumax.models.Enum.EOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GetAllInvoiceResponse {
    private double totalAmount;
    private double discountPercent;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private EInvoiceStatus status;

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public EInvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(EInvoiceStatus status) {
        this.status = status;
    }
}
