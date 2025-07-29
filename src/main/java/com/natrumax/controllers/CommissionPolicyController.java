package com.natrumax.controllers;

import com.natrumax.dto.request.CreateCommissionPolicyRequest;
import com.natrumax.dto.request.UpdateCommissionPolicyRequest;
import com.natrumax.dto.response.CommissionPolicyShortResponse;
import com.natrumax.models.CommissionPolicies;
import com.natrumax.services.CommissionPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/commission-policies")
public class CommissionPolicyController {

    @Autowired
    private CommissionPolicyService commissionPolicyService;

    @PostMapping
    public ResponseEntity<CommissionPolicies> createCommissionPolicy(@RequestBody CreateCommissionPolicyRequest request) {
        CommissionPolicies savedPolicy = commissionPolicyService.createCommissionPolicy(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPolicy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommissionPolicies> updateCommissionPolicy(
            @PathVariable Long id,
            @RequestBody UpdateCommissionPolicyRequest request) {
        CommissionPolicies updatedPolicy = commissionPolicyService.updateCommissionPolicy(id, request);
        return ResponseEntity.ok(updatedPolicy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommissionPolicyShortResponse> getCommissionPolicyById(@PathVariable Long id) {
        CommissionPolicyShortResponse policy = commissionPolicyService.getCommissionPolicyById(id);
        return ResponseEntity.ok(policy);
    }

    @GetMapping
    public ResponseEntity<List<CommissionPolicyShortResponse>> getAllCommissionPolicies() {
        List<CommissionPolicyShortResponse> policies = commissionPolicyService.getAllCommissionPolicies();
        return ResponseEntity.ok(policies);
    }
}
