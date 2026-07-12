package com.ai.modernization.backend.orchestration.api;

import com.ai.modernization.backend.orchestration.application.ModernizationOrchestrator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/modernization")
public class ModernizationController {

    private final ModernizationOrchestrator modernizationOrchestrator;

    public ModernizationController(
            ModernizationOrchestrator modernizationOrchestrator) {

        this.modernizationOrchestrator = modernizationOrchestrator;
    }

    @PostMapping
    public ResponseEntity<String> modernize(
            @RequestBody ModernizationRequest request) throws Exception {

        modernizationOrchestrator.modernize(
                request.githubFileUrl());

        return ResponseEntity.ok(
                "Modernization completed successfully.");
    }
}