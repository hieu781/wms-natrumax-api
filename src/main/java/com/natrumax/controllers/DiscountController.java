package com.natrumax.controllers;

import com.natrumax.dto.request.CreateDiscountRequest;
import com.natrumax.dto.request.GetDiscountByTotalAmountRequest;
import com.natrumax.dto.request.UpdateDiscountRequest;
import com.natrumax.models.Discount;
import com.natrumax.models.LotteryCode;
import com.natrumax.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discounts")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        List<Discount> discounts = discountService.getAllDiscounts();
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiscountById(@PathVariable Integer id) {
        return discountService.getDiscountById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Discount not found"));
    }

    @PostMapping
    public ResponseEntity<?> createDiscount(@RequestBody CreateDiscountRequest request) {
        try {
            Discount discount = discountService.createDiscount(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Discount created with ID: " + discount.getDiscountId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiscount(@PathVariable Integer id, @RequestBody UpdateDiscountRequest request) {
        Discount updatedDiscount = discountService.updateDiscount(id, request);
        if (updatedDiscount != null) {
            return ResponseEntity.ok(updatedDiscount);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Discount not found");
        }
    }

    @PutMapping("/{id}/change-status")
    public ResponseEntity<?> updateDiscountStatus(@PathVariable Integer id) {
        try {
            Discount discount = discountService.changeStatus(id);
            return ResponseEntity.ok("Discount code is " + (discount.isStatus() ? "activated" : "deactivated"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    @PostMapping("/get-by-total-amount")
    public ResponseEntity<?> getDiscountByTotalAmount(@RequestBody GetDiscountByTotalAmountRequest request){
        Discount discount = discountService.getDiscountByTotalAmount(request);

        if(discount != null){
            return ResponseEntity.ok(discount);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You dont have discount");
        }
    }
}
