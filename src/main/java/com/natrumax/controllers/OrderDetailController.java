package com.natrumax.controllers;

import com.natrumax.dto.request.UpdateOrderDetailRequest;
import com.natrumax.models.OrderDetail;
import com.natrumax.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable int id) {
        return ResponseEntity.ok(orderDetailService.getOrderDetailById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable int id, @RequestBody UpdateOrderDetailRequest request) {
        return ResponseEntity.ok(orderDetailService.updateOrderDetail(id, request));
    }
}
