package com.natrumax.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {
    private Long orderId;
    private LocalDateTime orderDate;
    private String saleCode;
    private String inventoryOutCode;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<OrderDetailResponse> orderDetails;
    private OrderInvoiceResponse orderInvoice;
    private String status;

    public OrderResponse() {
    }

    public OrderResponse(Long orderId, LocalDateTime orderDate, String saleCode, String inventoryOutCode, LocalDateTime createdDate, LocalDateTime modifiedDate, List<OrderDetailResponse> orderDetails, OrderInvoiceResponse orderInvoice, String estatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.saleCode = saleCode;
        this.inventoryOutCode = inventoryOutCode;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.orderDetails = orderDetails;
        this.orderInvoice = orderInvoice;
        this.status = estatus;
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

    public List<OrderDetailResponse> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailResponse> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderInvoiceResponse getOrderInvoice() {
        return orderInvoice;
    }

    public void setOrderInvoice(OrderInvoiceResponse orderInvoice) {
        this.orderInvoice = orderInvoice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
