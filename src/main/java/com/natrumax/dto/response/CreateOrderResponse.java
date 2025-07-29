package com.natrumax.dto.response;

import com.natrumax.models.Enum.EOrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class CreateOrderResponse {
    private int orderId;
    private LocalDateTime orderDate;
    private EOrderStatus status;
    private List<OrderDetailResponse> orderDetailResponses;
    private OrderInvoiceResponse orderInvoiceResponse;

    public CreateOrderResponse() {
    }

    public CreateOrderResponse(int orderId, LocalDateTime orderDate, EOrderStatus status, List<OrderDetailResponse> orderDetailResponses, OrderInvoiceResponse orderInvoiceResponse) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.orderDetailResponses = orderDetailResponses;
        this.orderInvoiceResponse = orderInvoiceResponse;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public EOrderStatus getStatus() {
        return status;
    }

    public void setStatus(EOrderStatus status) {
        this.status = status;
    }

    public List<OrderDetailResponse> getOrderDetailResponses() {
        return orderDetailResponses;
    }

    public void setOrderDetailResponses(List<OrderDetailResponse> orderDetailResponses) {
        this.orderDetailResponses = orderDetailResponses;
    }

    public OrderInvoiceResponse getOrderInvoiceResponse() {
        return orderInvoiceResponse;
    }

    public void setOrderInvoiceResponse(OrderInvoiceResponse orderInvoiceResponse) {
        this.orderInvoiceResponse = orderInvoiceResponse;
    }
}
