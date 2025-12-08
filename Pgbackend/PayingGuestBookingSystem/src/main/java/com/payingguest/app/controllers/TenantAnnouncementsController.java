package com.payingguest.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tenant")
@RequiredArgsConstructor
public class TenantAnnouncementsController {

    @GetMapping("/announcements")
    public ResponseEntity<List<Map<String, Object>>> getTenantAnnouncements() {
        // TODO: Replace with actual service logic & DTOs
        List<Map<String, Object>> announcements = List.of(
                Map.of("id", 1, "title", "Maintenance Notice", "message", "Water supply off from 10 AM–12 PM", "date", LocalDate.now()),
                Map.of("id", 2, "title", "New Security Measures", "message", "CCTV installed in lobby", "date", LocalDate.now().minusDays(2))
        );
        return ResponseEntity.ok(announcements);
    }
}
