package com.natrumax.controllers;

import com.natrumax.dto.request.UpdateOrderInvoiceRequest;
import com.natrumax.models.Invoices;
import com.natrumax.services.OrderInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/order-invoices")
public class OrderInvoiceController {

    @Autowired
    private OrderInvoiceService orderInvoiceService;

    @GetMapping("/{id}")
    public ResponseEntity<Invoices> getOrderInvoiceById(@PathVariable int id) {
        return ResponseEntity.ok(orderInvoiceService.getOrderInvoiceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoices> updateOrderInvoice(@PathVariable int id, @RequestBody UpdateOrderInvoiceRequest request) {
        return ResponseEntity.ok(orderInvoiceService.updateOrderInvoice(id, request));
    }

    @PostMapping("/transfer-image/{orderId}")
    public ResponseEntity<?> uploadTransferImage(@PathVariable final Long orderId, @RequestPart final MultipartFile file) {
        orderInvoiceService.uploadTransferImage(orderId, file);
        return ResponseEntity.ok("Upload successfully");
    }

    @PostMapping("/refund-image/{orderId}")
    public ResponseEntity<?> uploadRefundImage(@PathVariable final Long orderId, @RequestPart final MultipartFile file) {
        orderInvoiceService.uploadRefundImage(orderId, file);
        return ResponseEntity.ok("Upload successfully");
    }
}
