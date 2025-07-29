package com.natrumax.services.interfaces;

import com.natrumax.dto.request.CreateOrderRequest;
import com.natrumax.dto.request.UpdateOrderRequest;
import com.natrumax.dto.response.CreateOrderResponse;
import com.natrumax.dto.response.GetAllOrderResponse;
import com.natrumax.dto.response.GetOrderByIdResponse;
import com.natrumax.dto.response.OrderResponse;
import com.natrumax.models.Enum.EInvoiceStatus;
import com.natrumax.models.Enum.EOrderStatus;
import com.natrumax.models.Order;
import jakarta.transaction.Transactional;

import java.util.List;

public interface IOrderService {
    Order createOrder(CreateOrderRequest orderRequest);

    @Transactional
    void updateOrderStatus(Long orderId, String status);

    @Transactional
    public void cancelOrder(Long orderId);

    void updateOrderCodes(Long orderId, UpdateOrderRequest request);

    List<Order> getOrderList();

    Order getOrderById(Long orderId);

    List<Order> getByUserId(Long userId);

    public List<Order> getByStatus(EOrderStatus status);

    public List<Order> getByInvoiceStatus(EInvoiceStatus status);
}
