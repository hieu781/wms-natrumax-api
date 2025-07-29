package com.natrumax.controllers;

import com.natrumax.dto.request.CreateLotteryCodeRequest;
import com.natrumax.dto.request.UpdateLotteryCodeRequest;
import com.natrumax.dto.response.GetAllOrderResponse;
import com.natrumax.models.LotteryCode;
import com.natrumax.services.LotteryCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lottery-codes")
public class LotteryCodeController {

    @Autowired
    private LotteryCodeService lotteryCodeService;

    @GetMapping
    public ResponseEntity<List<LotteryCode>> getAllLotteryCodes() {
        List<LotteryCode> lotteryCodes = lotteryCodeService.getAllLotteryCodes();
        return ResponseEntity.ok(lotteryCodes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotteryCode> getOrderById(@PathVariable Integer id) {
        LotteryCode response = lotteryCodeService.getLotteryCodeById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<LotteryCode> createLotteryCode(@RequestBody CreateLotteryCodeRequest request) {
        LotteryCode newLotteryCode = lotteryCodeService.createLotteryCode(request);
        return ResponseEntity.ok(newLotteryCode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotteryCode> updateLotteryCode(@PathVariable int id, @RequestBody UpdateLotteryCodeRequest request) {
        LotteryCode updatedLotteryCode = lotteryCodeService.updateLotteryCode(id, request);
        return ResponseEntity.ok(updatedLotteryCode);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LotteryCode>> getLotteryCodesByUserId(@PathVariable Long userId) {
        List<LotteryCode> lotteryCodes = lotteryCodeService.getLotteryCodesByUserId(userId);
        return ResponseEntity.ok(lotteryCodes);
    }

    @PutMapping("/{id}/change-status")
    public ResponseEntity<?> toggleLotteryCodeStatus(@PathVariable int id) {
        try {
            LotteryCode lotteryCode = lotteryCodeService.changeStatus(id);
            return ResponseEntity.ok("Lottery code is " + (lotteryCode.isStatus() ? "activated" : "deactivated"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
