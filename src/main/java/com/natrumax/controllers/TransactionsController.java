package com.natrumax.controllers;

import com.natrumax.dto.request.CreateTransactionRequest;
import com.natrumax.dto.response.TransactionResponse;
import com.natrumax.models.Enum.ETransactionStatus;
import com.natrumax.models.Transactions;
import com.natrumax.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;
    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions() {
        return ResponseEntity.ok(transactionsService.getAllTransactions());
    }

    @GetMapping("/wallet/{walletId}")
    public ResponseEntity<List<Transactions>> getTransactionsByWalletId(@PathVariable int walletId) {
        return ResponseEntity.ok(transactionsService.getTransactionsByWalletId(walletId));
    }

    @PostMapping
    public ResponseEntity<Transactions> createTransaction(@RequestBody CreateTransactionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionsService.createTransaction(request));
    }

    @PostMapping("/transfer-image/{transactionsId}")
    public ResponseEntity<?> uploadTransferImage(@PathVariable final int transactionsId, @RequestPart final MultipartFile file) {
        transactionsService.uploadTransferImage(transactionsId, file);
        return ResponseEntity.ok("Upload successfully");
    }

    @PostMapping("/refund-image/{transactionsId}")
    public ResponseEntity<?> uploadRefundImage(@PathVariable final int transactionsId, @RequestPart final MultipartFile file) {
        transactionsService.uploadRefundImage(transactionsId, file);
        return ResponseEntity.ok("Upload successfully");
    }

    @PutMapping("/{id}/change-status")
    public ResponseEntity<Transactions> updateStatus(
            @PathVariable int id,
            @RequestParam String status) {
        Transactions updatedTransaction = transactionsService.updateTransactionStatus(id, status);
        return ResponseEntity.ok(updatedTransaction);
    }

    @GetMapping("/exclude-pending")
    public ResponseEntity<List<Transactions>> getTransactionsExcludePending() {
        List<Transactions> transactions = transactionsService.getTransactionsExcludingPending();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByStatus(@PathVariable ETransactionStatus status) {
        List<TransactionResponse> transactions = transactionsService.getTransactionsByStatus(status);
        return ResponseEntity.ok(transactions);
    }
}
