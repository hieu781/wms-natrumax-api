package com.natrumax.services;

import com.natrumax.models.Order;
import com.natrumax.models.OrderModifyHistory;
import com.natrumax.repository.OrderModifyHistoryRepository;
import com.natrumax.repository.OrderRepository;
import com.natrumax.services.interfaces.IOrderModifyHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderModifyHistoryService implements IOrderModifyHistoryService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderModifyHistoryRepository orderModifyHistoryRepository;

    public void createOrderModifyHistory(Long orderId) {
        OrderModifyHistory history = new OrderModifyHistory();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        history.setOrder(order);
        history.setTitle("Tạo đơn hàng");
        history.setActor(order.getUser().getAccountName());
        history.setCreateDate(LocalDateTime.now());
        orderModifyHistoryRepository.save(history);
    }
    public void createOrderModifyHistoryUpdate(Long orderId,String newStatus){
        OrderModifyHistory history = new OrderModifyHistory();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        history.setOrder(order);
        history.setTitle("Cập nhật trạng thái đơn hàng:" + newStatus);
        history.setActor(order.getUser().getAccountName());
        history.setCreateDate(LocalDateTime.now());
        orderModifyHistoryRepository.save(history);
    }
}
