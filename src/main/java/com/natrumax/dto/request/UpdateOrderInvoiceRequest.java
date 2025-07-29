package com.natrumax.dto.request;

import com.natrumax.models.Enum.EInvoiceStatus;
import com.natrumax.models.Enum.EOrderStatus;
import com.natrumax.models.Enum.EPaymentMethod;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class UpdateOrderInvoiceRequest {
    @NotNull(message = "Payment date cannot be null")
    @PastOrPresent(message = "Payment date cannot be in the future")
    private LocalDateTime paymentDate;

    @NotBlank(message = "Payment method cannot be blank")
    private EPaymentMethod paymentMethod;

    @NotNull(message = "Total amount cannot be null")
    @Min(value = 0, message = "Total amount must be greater than 0")
    private Double totalAmount;

    @NotNull(message = "Order status cannot be null")
    private EInvoiceStatus status;

    @NotNull(message = "Discount ID cannot be null")
    @Positive(message = "Discount ID must be a positive number")
    private Integer discountId;

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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public EInvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(EInvoiceStatus status) {
        this.status = status;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }
}
