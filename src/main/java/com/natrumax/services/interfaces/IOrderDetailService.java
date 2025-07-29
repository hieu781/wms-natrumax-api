package com.natrumax.services.interfaces;

import com.natrumax.dto.request.*;
import com.natrumax.models.Discount;
import com.natrumax.models.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailService {
    List<OrderDetail> getAllOrderDetail();

    Optional<OrderDetail> getOrderDetailById(int id);

    Discount createDiscount(CreateOrderDetailRequest request);

    Discount updateDiscount(int id, UpdateOrderDetailRequest request);
}
