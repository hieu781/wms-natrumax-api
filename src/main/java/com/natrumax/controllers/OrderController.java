package com.natrumax.controllers;

import com.natrumax.dto.request.CreateOrderDetailRequest;
import com.natrumax.dto.request.CreateOrderRequest;
import com.natrumax.dto.request.UpdateOrderRequest;
import com.natrumax.dto.response.CreateOrderResponse;
import com.natrumax.dto.response.GetAllOrderResponse;
import com.natrumax.dto.response.GetOrderByIdResponse;
import com.natrumax.dto.response.OrderResponse;
import com.natrumax.models.*;
import com.natrumax.models.Enum.EInvoiceStatus;
import com.natrumax.models.Enum.EOrderStatus;
import com.natrumax.models.Enum.EPaymentMethod;
import com.natrumax.models.Enum.ERole;
import com.natrumax.repository.OrderRepository;
import com.natrumax.services.APIService.KiotVietService;
import com.natrumax.services.APIService.MisaService;
import com.natrumax.services.OrderInvoiceService;
import com.natrumax.services.OrderModifyHistoryService;
import com.natrumax.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderInvoiceService orderInvoiceService;
    @Autowired
    private MisaService misaService;
    @Autowired
    private KiotVietService kiotVietService;
    @Autowired
    private OrderModifyHistoryService orderModifyHistoryService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getOrderList();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long id) {
        List<Order> orders = orderService.getByUserId(id);

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/success-invoice")
    public ResponseEntity<List<Order>> getAllSuccessOrderInvoice(){
        return getAllSuccessOrderInvoice();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order response = orderService.getOrderById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest createOrderRequest){
        Order order = orderService.createOrder(createOrderRequest);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/update-codes/{orderId}")
    public ResponseEntity<?> updateOrderCodes(
            @PathVariable Long orderId,
            @RequestBody UpdateOrderRequest request) {

        orderService.updateOrderCodes(orderId, request);
        return ResponseEntity.ok("Order codes updated successfully");
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getByStatus(@PathVariable String status) {
        List<Order> orders = orderService.getByStatus(EOrderStatus.valueOf(status));
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/invoice-status/{status}")
    public ResponseEntity<List<Order>> getByInvoiceStatus(@PathVariable String status) {
        List<Order> orders = orderService.getByInvoiceStatus(EInvoiceStatus.valueOf(status));
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/rollback-status/{orderId}")
    public ResponseEntity<String> rollBackOrderStatus(@PathVariable Long orderId){
        Order order = orderService.getOrderById(orderId);

        EOrderStatus currentStatus = order.getStatus();
        EOrderStatus newStatus;

        switch (currentStatus) {
            case PACKED:
                newStatus = EOrderStatus.CONFIRMED;
                break;
            case SHIPPED:
                newStatus = EOrderStatus.PACKED;
                break;
            default:
                throw new IllegalStateException("Cannot rollback from status: " + currentStatus);
        }

        orderService.updateOrderStatus(orderId,newStatus.name());

        return ResponseEntity.ok("Order status roll back successfully from "+currentStatus+" to "+newStatus);

    }

    @PutMapping("/cancel-order/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId){
        Order order = orderService.getOrderById(orderId);

        String clientSecret = misaService.getClientSecretByUser(order.getUser());

        if(misaService.isInventoryOutMissing(clientSecret,order)){
            orderService.cancelOrder(orderId);
            orderModifyHistoryService.createOrderModifyHistoryUpdate(orderId, EOrderStatus.CANCELED.name());
            return ResponseEntity.ok("Cancel order success");
        }else {
            return ResponseEntity.ok("Cant cancel order if inventory already out ");
        }
    }

    @PutMapping("/update-status/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);

        EOrderStatus recentStatus = order.getStatus();
        EOrderStatus newStatus;

        String clientSecret = misaService.getClientSecretByUser(order.getUser());

        switch (recentStatus) {
            case PENDING:
                newStatus = EOrderStatus.CONFIRMED;
                break;
            case CONFIRMED:
                newStatus = EOrderStatus.PACKED;
                break;
            case PACKED:
                newStatus = EOrderStatus.SHIPPED;
                break;
            case SHIPPED:
                newStatus = EOrderStatus.DELIVERED;
                break;
            default:
                throw new RuntimeException("Invalid status transition from: " + recentStatus);
        }
        if(newStatus == EOrderStatus.CONFIRMED){
            boolean isCreateSuccess = true;

            if(order.getInvoices().getPaymentMethod()== EPaymentMethod.BANK_TRANSFER){
                User member = order.getUser();

                Warehouse warehouse = member.getUserWarehouses()
                        .stream()
                        .filter(uw -> "Member".equalsIgnoreCase(uw.getRoleInWarehouse()))
                        .map(UserWarehouse::getWarehouse)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("User is not a Member in any warehouse"));

                User owner = warehouse.getUserWarehouses()
                        .stream()
                        .filter(uw -> "Owner".equalsIgnoreCase(uw.getRoleInWarehouse()))
                        .map(UserWarehouse::getUser)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Warehouse does not have an Owner"));

                double finalAmount = order.getInvoices().getTotalAmount()*(100-order.getInvoices().getDiscount().getDiscountPercent())/100;
                String receiptCode = misaService.createReceipt(member, owner, finalAmount);
                isCreateSuccess = receiptCode !=null;
            }
            if(isCreateSuccess){
                for (OrderDetail detail : order.getOrderDetails()) {
                    CreateOrderDetailRequest detailRequest = new CreateOrderDetailRequest();
                    detailRequest.setProductId(detail.getProduct().getProductId());
                    detailRequest.setQuantity(detail.getQuantity());
                    detailRequest.setBonus(detail.isBonus());

                }
                misaService.createMisaSale(clientSecret,order);
                orderInvoiceService.confirmOrderInvoice(order);
                orderService.updateOrderStatus(orderId,newStatus.name());
            }else return ResponseEntity.ok("Order status update fail from "+recentStatus+" to "+newStatus);
        }else if (newStatus == EOrderStatus.DELIVERED) {

            boolean isFullExport = misaService.isInventoryFullyExported(clientSecret,order);
            boolean isMissing = misaService.isInventoryOutMissing(clientSecret,order);
            
            if(!isMissing){
                if(isFullExport){
                    String role = order.getUser().getRole().toString();
                    if(role.equals(ERole.ROLE_DISTRIBUTOR.name())){
                        if(misaService.createMisaInventoryIn(order)){
                            orderService.updateOrderStatus(orderId,newStatus.name());
                        }
                    }
                    if(role.equals(ERole.ROLE_BRANCH_OWNER.name())){
                        kiotVietService.createKiotVietPurchaseOrder(order);
                        orderService.updateOrderStatus(orderId,newStatus.name());
                    }
                }else {
                    throw new RuntimeException("Inventory is not sufficient to deliver the order.");
                }
            }else {
                throw new EntityNotFoundException("Inventory out is missing");
            }

        }else {
            orderService.updateOrderStatus(orderId, newStatus.name());
        }
        orderModifyHistoryService.createOrderModifyHistoryUpdate(orderId,newStatus.getLabel());
       return ResponseEntity.ok("Order status update successfully from "+recentStatus+" to "+newStatus);
    }

}
