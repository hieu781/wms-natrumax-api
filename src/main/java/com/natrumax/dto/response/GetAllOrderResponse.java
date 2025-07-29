package com.natrumax.dto.response;

import com.natrumax.models.Enum.EOrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class GetAllOrderResponse {
    private LocalDateTime orderDate;
    private String saleCode;
    private String inventoryOutCode;
    private EOrderStatus status;
    @NotNull(message = "Order details cannot be null")
    @Size(min = 1, message = "At least one order detail must be provided")
    @Valid
    private List<GetAllOrderDetailResponse> getAllOrderDetailResponses;

    @NotNull(message = "Order invoice cannot be null")
    @Valid
    private GetAllInvoiceResponse getAllInvoiceResponse;

    @NotNull(message = "Order history cannot be null")
    @Size(min = 1, message = "At least one order history must be provided")
    @Valid
    private List<GetAllOrderModifyHistoryResponse> getAllOrderModifyHistoryResponse;



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

    public EOrderStatus getStatus() {
        return status;
    }

    public void setStatus(EOrderStatus status) {
        this.status = status;
    }

    public List<GetAllOrderDetailResponse> getGetAllOrderDetailResponses() {
        return getAllOrderDetailResponses;
    }

    public void setGetAllOrderDetailResponses(List<GetAllOrderDetailResponse> getAllOrderDetailResponses) {
        this.getAllOrderDetailResponses = getAllOrderDetailResponses;
    }

    public GetAllInvoiceResponse getGetAllInvoiceResponse() {
        return getAllInvoiceResponse;
    }

    public void setGetAllInvoiceResponse(GetAllInvoiceResponse getAllInvoiceResponse) {
        this.getAllInvoiceResponse = getAllInvoiceResponse;
    }

    public List<GetAllOrderModifyHistoryResponse> getGetAllOrderModifyHistoryResponse() {
        return getAllOrderModifyHistoryResponse;
    }

    public void setGetAllOrderModifyHistoryResponse(List<GetAllOrderModifyHistoryResponse> getAllOrderModifyHistoryResponse) {
        this.getAllOrderModifyHistoryResponse = getAllOrderModifyHistoryResponse;
    }
}
