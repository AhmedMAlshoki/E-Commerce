package com.example.ECommerce.Controllers;


import com.example.ECommerce.DTOs.ReportDTO;
import com.example.ECommerce.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @PostMapping("/report")
    @PreAuthorize("hasRole('ROLE_CUSTOMER') ")
    public ResponseEntity<?> addReport(@RequestBody ReportDTO reportDTO) {
        try {
            return ResponseEntity.ok(reportService.addReport(reportDTO));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/report/{id}")
    @PreAuthorize("@ReportService.isOwner(#id)")
    public ResponseEntity<?> updateReport(@PathVariable Long id, @RequestBody ReportDTO reportDTO) {
        try {
            return ResponseEntity.ok(reportService.updateReport(id, reportDTO));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/report/{id}")
    @PreAuthorize("@ReportService.isOwner(#id) or hasRole('ROLE_SUPPORT')")
    public ResponseEntity<?> deleteReport(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reportService.deleteReport(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/report/{id}")
    @PreAuthorize("@ReportService.isOwner(#id) or hasRole('ROLE_SUPPORT')")
    public ResponseEntity<?> getReport(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reportService.getReport(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/reports/user/{id}")
    @PreAuthorize("hasRole('ROLE_SUPPORT')")
    public ResponseEntity<?> getReportsByUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reportService.getReportsByUser(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/reports/product/{id}")
    @PreAuthorize("hasRole('ROLE_SUPPORT') or @ProductService.isOwner(#id)")
    public ResponseEntity<?> getReportsByProduct(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reportService.getReportsByProduct(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/reports/forUser/{id}")
    @PreAuthorize("hasRole('ROLE_SUPPORT')")
    public ResponseEntity<?> getReportsForUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reportService.getReportsOnUser(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/report/resolve/{id}")
    @PreAuthorize("hasRole('ROLE_SUPPORT')")
    public ResponseEntity<?> resolveReport(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reportService.resolveReport(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
