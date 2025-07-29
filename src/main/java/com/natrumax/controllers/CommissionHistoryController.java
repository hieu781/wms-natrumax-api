package com.natrumax.controllers;

import com.natrumax.dto.request.CreateCommissionHistoryRequest;
import com.natrumax.dto.request.UpdateCommissionHistoryRequest;
import com.natrumax.dto.response.CommissionHistoriesResponse;
import com.natrumax.dto.response.CommissionHistoryShortResponse;
import com.natrumax.models.CommissionHistories;
import com.natrumax.repository.CommissionHistoryRepository;
import com.natrumax.services.CommissionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/commission-histories")
public class CommissionHistoryController {

    @Autowired
    private CommissionHistoryService commissionHistoryService;

    @Autowired
    private CommissionHistoryRepository commissionHistoryRepository;

    @PostMapping
    public ResponseEntity<CommissionHistories> createCommissionHistory(
            @RequestBody CreateCommissionHistoryRequest request) {
        CommissionHistories savedHistory = commissionHistoryService.createCommissionHistory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommissionHistories> updateCommissionHistory(
            @PathVariable Long id,
            @RequestBody UpdateCommissionHistoryRequest request) {
        CommissionHistories updatedHistory = commissionHistoryService.updateCommissionHistory(id, request);
        return ResponseEntity.ok(updatedHistory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommissionHistoryShortResponse> getCommissionHistoryById(@PathVariable Long id) {
        CommissionHistoryShortResponse history = commissionHistoryService.getCommissionHistoryById(id);
        return ResponseEntity.ok(history);
    }

    @GetMapping
    public ResponseEntity<List<CommissionHistoryShortResponse>> getAllCommissionHistories() {
        List<CommissionHistoryShortResponse> histories = commissionHistoryService.getAllCommissionHistories();
        return ResponseEntity.ok(histories);
    }


    @GetMapping("/{userId}/history")
    public ResponseEntity<List<CommissionHistories>> getCommissionHistory(@PathVariable Long userId) {
        List<CommissionHistories> commissionHistories = commissionHistoryService.getCommissionHistoriesByUserId(userId);
        return ResponseEntity.ok(commissionHistories);
    }

    @GetMapping("/commissions")
    public ResponseEntity<CommissionHistoriesResponse> getCommissionListByReferrerIdAndMonthAndYear(
            @RequestParam Long referrerId,
            @RequestParam int month,
            @RequestParam int year
    ) {
        CommissionHistoriesResponse response = commissionHistoryService.getCommissionListByReferrerIdAndMonthAndYear(referrerId, month, year);
        return ResponseEntity.ok(response);
    }
}
