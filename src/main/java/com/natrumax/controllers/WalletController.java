package com.natrumax.controllers;

import com.natrumax.dto.request.CreateWalletRequest;
import com.natrumax.dto.request.UpdateWalletRequest;
import com.natrumax.models.User;
import com.natrumax.models.Wallet;
import com.natrumax.repository.UserRepository;
import com.natrumax.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable int id) {
        return ResponseEntity.ok(walletService.getWalletById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Wallet> getWalletByUserId(@PathVariable Long userId) {
        // Lấy user theo id
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Gọi service để update
        walletService.updateWalletBalanceFromMisa(user);
        return ResponseEntity.ok(walletService.getWalletByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody CreateWalletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(walletService.createWallet(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wallet> updateWallet(@PathVariable int id, @RequestBody UpdateWalletRequest request) {
        return ResponseEntity.ok(walletService.updateWallet(id, request));
    }

    @PutMapping("/update-balance/{userId}")
    public ResponseEntity<?> updateWalletBalance(@PathVariable Long userId) {
        // Lấy user theo id
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Gọi service để update
        walletService.updateWalletBalanceFromMisa(user);

        return ResponseEntity.ok("Wallet balance updated successfully.");
    }
}
