package com.natrumax.dto.request;

import com.natrumax.models.Enum.EOrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

public class CreateOrderRequest {
    @NotNull(message = "User ID cannot be null")
    @Positive(message = "User ID must be a positive number")
    private Long userId;

    @NotNull(message = "Order details cannot be null")
    @Size(min = 1, message = "At least one order detail must be provided")
    @Valid
    private List<CreateOrderDetailRequest> createOrderDetailRequests;

    @NotNull(message = "Order invoice cannot be null")
    @Valid
    private CreateOrderInvoiceRequest createOrderInvoiceRequests;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(Long userId, LocalDateTime orderDate, EOrderStatus status, LocalDateTime createDate, List<CreateOrderDetailRequest> createOrderDetailRequests, CreateOrderInvoiceRequest createOrderInvoiceRequests) {
        this.userId = userId;
        this.createOrderDetailRequests = createOrderDetailRequests;
        this.createOrderInvoiceRequests = createOrderInvoiceRequests;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CreateOrderInvoiceRequest getCreateOrderInvoiceRequests() {
        return createOrderInvoiceRequests;
    }

    public void setCreateOrderInvoiceRequests(CreateOrderInvoiceRequest createOrderInvoiceRequests) {
        this.createOrderInvoiceRequests = createOrderInvoiceRequests;
    }

    public List<CreateOrderDetailRequest> getCreateOrderDetailRequests() {
        return createOrderDetailRequests;
    }

    public void setCreateOrderDetailRequests(List<CreateOrderDetailRequest> createOrderDetailRequests) {
        this.createOrderDetailRequests = createOrderDetailRequests;
    }
}
