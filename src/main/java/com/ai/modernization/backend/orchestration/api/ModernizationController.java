package com.ai.modernization.backend.orchestration.api;

import com.ai.modernization.backend.codegen.zip.ProjectZipService;
import com.ai.modernization.backend.orchestration.application.ModernizationOrchestrator;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/modernization")
public class ModernizationController {

    private final ModernizationOrchestrator modernizationOrchestrator;

    private final ProjectZipService projectZipService;

    public ModernizationController(
            ModernizationOrchestrator modernizationOrchestrator,
            ProjectZipService projectZipService) {

        this.modernizationOrchestrator = modernizationOrchestrator;
        this.projectZipService = projectZipService;
    }


    @PostMapping
    public ResponseEntity<String> modernize(
            @RequestBody ModernizationRequest request) throws Exception {

        modernizationOrchestrator.modernize(
                request.githubFileUrl());

        return ResponseEntity.ok(
                "Modernization completed successfully.");
    }

    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> download() throws Exception {

        byte[] zip =
                projectZipService.zipModernizationOutput(
                        "artifacts/discovery",
                        "artifacts/api",
                        "generated");

        ByteArrayResource resource =
                new ByteArrayResource(zip);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=customer-management-service.zip")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(zip.length)
                .body(resource);
    }
}