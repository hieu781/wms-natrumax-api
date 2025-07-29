package com.natrumax.controllers;

import com.natrumax.dto.request.PromotionRequest;
import com.natrumax.dto.response.PromotionResponse;
import com.natrumax.models.Promotion;
import com.natrumax.models.User;
import com.natrumax.repository.UserRepository;
import com.natrumax.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public ResponseEntity<?> createPromotion(@RequestBody PromotionRequest request) {
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        try {
            Promotion promotion = promotionService.createPromotion(user, request);

            PromotionResponse response = new PromotionResponse(
                    promotion.getPromotionId(),
                    promotion.getQuantityToGetPromotion(),
                    promotion.isSameProduct(),
                    promotion.getBonusQuantity()
            );

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        List<Promotion> promotions = promotionService.getAllPromotions();
        return ResponseEntity.ok(promotions);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getPromotionByUserId(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Promotion promotion = promotionService.getPromotionByUser(user);
        if (promotion == null) {
            return ResponseEntity.ok("No promotion available for this user");
        }

        // Chuyển đổi Promotion sang DTO để loại bỏ thông tin User
        PromotionResponse response = new PromotionResponse(
                promotion.getPromotionId(),
                promotion.getQuantityToGetPromotion(),
                promotion.isSameProduct(),
                promotion.getBonusQuantity()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/check-promotion")
    public ResponseEntity<?> checkPromotion(@RequestBody PromotionRequest request) {
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Promotion promotion = promotionService.checkPromotion(user, request.getProduct(), request.getQuantityBought());

        if (promotion != null) {
            int bonus = promotionService.calculateBonus(promotion, request.getQuantityBought());
            return ResponseEntity.ok(new PromotionResponse(
                    request.getQuantityBought(),
                    promotion.getQuantityToGetPromotion(),
                    promotion.isSameProduct(),
                    bonus
            ));
        } else {
            return ResponseEntity.ok("No promotion available");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePromotion(@PathVariable Long id, @RequestBody PromotionRequest request) {
        Promotion updatedPromotion = promotionService.updatePromotion(id, request);
        if (updatedPromotion == null) {
            return ResponseEntity.badRequest().body("Promotion not found or invalid user");
        }

        // Chuyển đổi Promotion sang DTO để loại bỏ thông tin User
        PromotionResponse response = new PromotionResponse(
                updatedPromotion.getPromotionId(),
                updatedPromotion.getQuantityToGetPromotion(),
                updatedPromotion.isSameProduct(),
                updatedPromotion.getBonusQuantity()
        );

        return ResponseEntity.ok(response);
    }
}
