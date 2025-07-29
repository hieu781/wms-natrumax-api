package com.natrumax.controllers;

import com.natrumax.dto.request.CommissionPoliciesRequest;
import com.natrumax.dto.request.CommissionRequest;
import com.natrumax.dto.request.UpdateCommissionPoliciesRequest;
import com.natrumax.dto.request.UpdateCommissionRequest;
import com.natrumax.dto.response.CommissionResponse;
import com.natrumax.dto.response.CommissionShortResponse;
import com.natrumax.dto.response.ReferrerResponse;
import com.natrumax.models.Commission;
import com.natrumax.services.CommissionService;
import com.natrumax.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/commissions")
public class CommissionController {

    @Autowired
    private CommissionService commissionService;

    @Autowired
    private UserService userService;

    @PutMapping("/{id}")
    public ResponseEntity<Commission> updateCommission(
            @PathVariable int id,
            @RequestBody UpdateCommissionRequest request) {
        Commission updatedCommission = commissionService.updateCommission(id, request);
        return ResponseEntity.ok(updatedCommission);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommissionShortResponse> getCommissionById(@PathVariable int id) {
        CommissionShortResponse commission = commissionService.getCommissionById(id);
        return ResponseEntity.ok(commission);
    }

    @GetMapping
    public ResponseEntity<List<CommissionShortResponse>> getAllCommissions() {
        List<CommissionShortResponse> commissions = commissionService.getAllCommissions();
        return ResponseEntity.ok(commissions);
    }

    @PostMapping("/earn")
    public ResponseEntity<?> earnCommission(@RequestBody CommissionRequest request) {
        commissionService.processCommission(request);
        return ResponseEntity.ok("Commission processed successfully.");
    }

    @GetMapping("/referrers")
    public ResponseEntity<List<ReferrerResponse>> getReferrerList() {
        List<ReferrerResponse> referrerList = userService.getReferrerList();
        return ResponseEntity.ok(referrerList);
    }

    @GetMapping("/referrer/{referrerId}")
    public ResponseEntity<CommissionResponse> getCommissionListByReferrerId(@PathVariable Long referrerId) {
        CommissionResponse response = commissionService.getCommissionListByReferrerId(referrerId);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> createCommissionPolicies(@RequestBody CommissionPoliciesRequest request) {
        Commission commission = commissionService.createCommissions(request.getReferrerId(), request.getReferralId());
        commissionService.createCommissionPolicies(commission.getCommissionId(), request.getPolicies());
        return ResponseEntity.ok("Commission policies created successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCommissionPolicies(@RequestBody UpdateCommissionPoliciesRequest request) {
        commissionService.updateCommissionPolicies(request);
        return ResponseEntity.ok("Commission policies updated successfully");
    }

    @PostMapping("/report")
    public ResponseEntity<String> createReport(
            @RequestParam int month,
            @RequestParam int year
    ) {
        commissionService.createReport(month, year);
        return ResponseEntity.ok("Report created successfully.");
    }

    @PostMapping("/history")
    public ResponseEntity<String> createCommissionHistory(
            @RequestParam int month,
            @RequestParam int year
    ) {
        commissionService.createCommissionHistory(month, year);
        return ResponseEntity.ok("Commission history created successfully.");
    }
}