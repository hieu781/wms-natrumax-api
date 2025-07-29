package com.natrumax.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.natrumax.config.KiotVietApiClient;
import com.natrumax.config.MisaApiClient;
import com.natrumax.dto.request.*;
import com.natrumax.models.*;
import com.natrumax.models.Enum.EInvoiceStatus;
import com.natrumax.models.Enum.EOrderStatus;
import com.natrumax.models.Enum.EOrderType;
import com.natrumax.models.MockAPI.InventoryInItem;
import com.natrumax.models.MockAPI.MisaInventoryOut;
import com.natrumax.models.MockAPI.MisaSaleItem;
import com.natrumax.models.MockAPI.SaleResponse;
import com.natrumax.models.MockAPI.request.MisaInventoryInRequest;
import com.natrumax.models.MockAPI.response.MisaCustomerResponse;
import com.natrumax.models.MockAPI.response.MisaInventoryInResponse;
import com.natrumax.models.MockAPI.response.MisaSaleResponse;
import com.natrumax.repository.*;
import com.natrumax.services.APIService.KiotVietService;
import com.natrumax.services.APIService.MisaService;
import com.natrumax.services.interfaces.IOrderService;
import jakarta.persistence.EntityNotFoundException;
//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderInvoiceRepository orderInvoiceRepository;
    @Autowired
    private OrderModifyHistoryRepository orderModifyHistoryRepository;
    @Autowired
    private OrderInvoiceService orderInvoiceService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderModifyHistoryService orderModifyHistoryService;
    @Autowired
    private  MisaService misaService;


    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Order createOrder(CreateOrderRequest orderRequest) {
        Long userId = orderRequest.getUserId();

        //Create invoice
        Invoices invoice = orderInvoiceService.createInvoice(orderRequest.getCreateOrderInvoiceRequests());
        //create order
        Order order = createOrder(invoice.getInvoiceId(), userId, EOrderStatus.PENDING);
        //Create order detail
        for (CreateOrderDetailRequest detail : orderRequest.getCreateOrderDetailRequests()) {
            orderDetailService.createOrderDetail(order.getOrderId(), detail, userId);
        }

        orderModifyHistoryService.createOrderModifyHistory(order.getOrderId());

        Order orderWithDetails = orderRepository.findById(order.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

//        OrderResponse orderResponse = OrderMapper.toOrderResponse(orderWithDetails);


        return orderWithDetails;
    }



    public Order createOrder(int invoiceId, Long userId, EOrderStatus status) {
        Order order = new Order();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        Invoices invoices = orderInvoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("invoices not found"));
        order.setUser(user);
        order.setInvoices(invoices);
        order.setStatus(status);
        order.setOrderType(EOrderType.NORMAL);
        order.setCreatedDate(LocalDateTime.now());
        order.setOrderDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id:" + orderId));
        order.setStatus(EOrderStatus.valueOf(status.toUpperCase()));
        order.setModifiedDate(LocalDateTime.now());
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id:" + orderId));
        order.setStatus(EOrderStatus.CANCELED);
        order.setModifiedDate(LocalDateTime.now());
        order.getInvoices().setStatus(EInvoiceStatus.CANCELED);
        order.getInvoices().setModifyDate(LocalDateTime.now());
        orderRepository.save(order);
    }

//    @Transactional
//    public void processUpdateOrderStatus(Long orderId) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//        EOrderStatus recentStatus = order.getEStatus();
//        EOrderStatus newStatus;
//        switch (recentStatus) {
//            case PENDING:
//                newStatus = EOrderStatus.CONFIRMED;
//                break;
//            case CONFIRMED:
//                newStatus = EOrderStatus.PACKED;
//                break;
//            case PACKED:
//                newStatus = EOrderStatus.SHIPPED;
//                break;
//            case SHIPPED:
//                newStatus = EOrderStatus.DELIVERED;
//                break;
//            default:
//                throw new RuntimeException("Invalid status transition from: " + recentStatus);
//        }
//
//        if (newStatus == EOrderStatus.CONFIRMED) {
//            // For CONFIRMED, create Misa sale for each order detail.
//            for (OrderDetail detail : order.getOrderDetails()) {
//                CreateOrderDetailRequest detailRequest = new CreateOrderDetailRequest();
//                detailRequest.setProductId(detail.getProduct().getProductId());
//                detailRequest.setQuantity(detail.getQuantity());
//                detailRequest.setBonus(detail.isBonus());
//                // Extract client secret, using your provided logic.
//                String clientSecret = misaService.getClientSecretByUser(order.getUser(), detail.getProduct());
//                misaService.createMisaSale(clientSecret,order);
//            }
//            updateOrderStatus(orderId, newStatus.name());
//        } else if (newStatus == EOrderStatus.DELIVERED) {
//            // For DELIVERED, check inventory via saleCode.
//            String inventoryOut = order.getInventoryOutCode();
//            if ("Xuất đủ".equalsIgnoreCase(inventoryOut)) {
//                updateOrderStatus(orderId, newStatus.name());
//                order.setInventoryOutCode(inventoryOut);
//                kiotVietService.createKiotVietPurchaseOrder(order);
//            } else {
//                throw new RuntimeException("Inventory is not sufficient to deliver the order.");
//            }
//        } else {
//            // For PACKED and SHIPPED, just update status.
//            updateOrderStatus(orderId, newStatus.name());
//        }
//
//        // Log the update in order modification history.
//        orderModifyHistoryService.createOrderModifyHistoryUpdate(orderId,newStatus.name());
//
//    }
    @Override
    public void updateOrderCodes(Long orderId, UpdateOrderRequest request) {
        // Find order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Update fields
        order.setInventoryOutCode(request.getInventoryOutCode());
        order.setSaleCode(request.getSaleCode());
        order.setModifiedDate(LocalDateTime.now());

        // Save modification history
        OrderModifyHistory history = new OrderModifyHistory();
        history.setOrder(order);
        history.setTitle(request.getTitle());
        history.setCreateDate(LocalDateTime.now());
        history.setModifyDate(LocalDateTime.now());

        // Save updates
        orderModifyHistoryRepository.save(history);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderList() {
        List<Order> orderList = orderRepository.findAll();

        return orderList;
    }

    @Override
    public Order getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id:" +orderId));

        return order;
    }

    @Override
    public List<Order> getByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> getByStatus(EOrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<Order> getByInvoiceStatus(EInvoiceStatus status) {
        return orderRepository.findByInvoices_Status(status);
    }

    public List<Order> getAllOrderInvoiceSuccess() {return orderRepository.findAllByInvoices_Status(EInvoiceStatus.SUCCESS);}
}
