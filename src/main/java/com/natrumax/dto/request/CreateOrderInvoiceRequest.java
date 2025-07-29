package com.natrumax.dto.request;

import com.natrumax.models.Enum.EPaymentMethod;
import com.natrumax.models.Enum.ETransactionStatus;
import jakarta.validation.constraints.*;
import java.sql.Struct;
import java.time.LocalDateTime;

public class CreateOrderInvoiceRequest  {

    private int discountId;
    private double totalAmount;
    @NotBlank(message = "Payment method cannot be blank")
    private EPaymentMethod paymentMethod;


    public CreateOrderInvoiceRequest() {
    }

    public CreateOrderInvoiceRequest(int discountId, double totalAmount, EPaymentMethod paymentMethod) {
        this.discountId = discountId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
    }

    public EPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(EPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
