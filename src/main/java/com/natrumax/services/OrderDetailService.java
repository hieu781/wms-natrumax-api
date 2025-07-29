package com.natrumax.services;

import com.natrumax.dto.request.CreateOrderDetailRequest;
import com.natrumax.dto.request.UpdateOrderDetailRequest;
import com.natrumax.models.Order;
import com.natrumax.models.OrderDetail;
import com.natrumax.models.Product;
import com.natrumax.repository.OrderDetailRepository;
import com.natrumax.repository.OrderRepository;
import com.natrumax.repository.ProductRepository;
import com.natrumax.services.APIService.MisaService;
import com.natrumax.models.*;
import com.natrumax.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WarehouseProductRepository warehouseProductRepository;
    @Autowired
    private UserRepository userRepository;

    public OrderDetail getOrderDetailById(int id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderDetail not found with id: " + id));
    }

    public OrderDetail updateOrderDetail(int id, UpdateOrderDetailRequest request) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderDetail not found with id: " + id));

        if (request.getHasPromotion() != null) {

            orderDetail.setBonus(request.getHasPromotion());
        }
        if (request.getQuantity() != null) {
            orderDetail.setQuantity(request.getQuantity());
        }

        orderDetail.setModifyDate(LocalDateTime.now());

        return orderDetailRepository.save(orderDetail);
    }
    @Transactional
    public void createOrderDetail(Long orderId, CreateOrderDetailRequest detailRequest, Long userId) {
        OrderDetail orderDetail = new OrderDetail();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Product product = productRepository.findByProductId(detailRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Warehouse warehouse = user.getUserWarehouses()
                .stream()
                .filter(uw -> "Member".equalsIgnoreCase(uw.getRoleInWarehouse()))
                .map(UserWarehouse::getWarehouse)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User is not a Member in any warehouse"));

        WarehouseProduct warehouseProduct = warehouseProductRepository.findByWarehouse_WarehouseIdAndProduct_ProductId(warehouse.getWarehouseId(), product.getProductId())
        .orElseThrow(() -> new RuntimeException("Warehouse product not found"));

        warehouseProduct.setQuantity(warehouseProduct.getQuantity()-detailRequest.getQuantity());

        warehouseProductRepository.save(warehouseProduct);
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setPrice(detailRequest.getPrice());
        orderDetail.setQuantity(detailRequest.getQuantity());
        orderDetail.setBonus(detailRequest.isBonus());
        orderDetail.setCreateDate(LocalDateTime.now());
        orderDetailRepository.save(orderDetail);
    }
}
