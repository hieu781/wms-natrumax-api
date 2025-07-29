package com.natrumax.controllers;

import com.natrumax.dto.response.ProductReportResponse;
import com.natrumax.dto.response.ProductSoldResponse;
import com.natrumax.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/user")
    public ResponseEntity<List<ProductReportResponse>> getUserReport(
            @RequestParam Long userId,
            @RequestParam int month,
            @RequestParam int year) {

        List<ProductReportResponse> reports = reportService.getUserReport(userId, month, year);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/sold")
    public ResponseEntity<List<ProductSoldResponse>> getSoldReportByMonthYear(
            @RequestParam int month,
            @RequestParam int year) {

        List<ProductSoldResponse> result = reportService.getSoldReport(month, year);
        return ResponseEntity.ok(result);
    }
}
