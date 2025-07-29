package com.natrumax.controllers;

import com.natrumax.dto.request.CreateProvinceRequest;
import com.natrumax.dto.request.UpdateProvinceRequest;
import com.natrumax.models.Province;
import com.natrumax.services.ProvinceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    public ResponseEntity<List<Province>> getAllProvinces() {
        List<Province> provinces = provinceService.getAllProvinces();
        return ResponseEntity.ok(provinces);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Province> getProvinceDetail(@PathVariable Long id) {
        return provinceService.getProvinceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Province> createProvince(@Valid @RequestBody CreateProvinceRequest request) {
        Province savedProvince = provinceService.createProvince(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProvince);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Province> updateProvince(@PathVariable Long id, @Valid @RequestBody UpdateProvinceRequest request) {
        return provinceService.updateProvince(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
