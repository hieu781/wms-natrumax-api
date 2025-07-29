package com.natrumax.controllers;

import com.natrumax.dto.request.CreateBankRequest;
import com.natrumax.dto.request.UpdateBankRequest;
import com.natrumax.models.Banks;
import com.natrumax.services.interfaces.IBankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/banks")
public class BankController {

    @Autowired
    private IBankService bankService;
    @GetMapping("/{id}")
    public Banks getBankById(@PathVariable Long id) {
        return bankService.getBankById(id);
    }

    @GetMapping("/user/{userId}")
    public Banks getBankByUserId(@PathVariable Long userId) {
        return bankService.getBankByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Banks> createBank(@RequestBody @Valid CreateBankRequest request) {
        Banks newBank = bankService.createBank(request);
        return ResponseEntity.ok(newBank);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Banks> updateBank(@PathVariable Long id, @RequestBody @Valid UpdateBankRequest request) {
        Banks updatedBank = bankService.updateBank(id, request);
        return ResponseEntity.ok(updatedBank);
    }
}
