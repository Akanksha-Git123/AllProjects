package com.payingguest.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tenant")
@RequiredArgsConstructor
public class TenantDocumentsController {

    @GetMapping("/documents")
    public ResponseEntity<List<Map<String, Object>>> getTenantDocuments() {
        // TODO: Replace with actual service logic & DTOs
        List<Map<String, Object>> docs = List.of(
                Map.of("id", 1, "title", "Rental Agreement", "url", "/docs/agreement.pdf"),
                Map.of("id", 2, "title", "PG Rules", "url", "/docs/rules.pdf")
        );
        return ResponseEntity.ok(docs);
    }
}
