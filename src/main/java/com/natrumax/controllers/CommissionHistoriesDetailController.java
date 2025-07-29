package com.natrumax.controllers;

import com.natrumax.dto.request.CommissionHistoriesDetailCreateRequest;
import com.natrumax.dto.request.CommissionHistoriesDetailUpdateRequest;
import com.natrumax.models.CommissionHistoriesDetail;
import com.natrumax.services.CommissionHistoriesDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/commission-histories-details")
public class CommissionHistoriesDetailController {

    @Autowired
    private CommissionHistoriesDetailService service;

    @GetMapping
    public ResponseEntity<List<CommissionHistoriesDetail>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommissionHistoriesDetail> getById(@PathVariable Long id) {
        Optional<CommissionHistoriesDetail> detail = service.getById(id);
        return detail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CommissionHistoriesDetail> create(@RequestBody CommissionHistoriesDetailCreateRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommissionHistoriesDetail> update(@PathVariable Long id, @RequestBody CommissionHistoriesDetailUpdateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }
}
