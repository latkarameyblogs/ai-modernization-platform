package com.ai.modernization.backend.discovery.domain;

import java.util.List;

public record DiscoveryDocument(
        String programName,
        String summary,
        String businessPurpose,
        List<String> inputs,
        List<String> outputs,
        List<String> dependencies,
        List<String> externalPrograms,
        List<String> files,
        String cicsUsage,
        String batchProcessing,
        String suggestedMicroservice,
        List<String> assumptions
) {
}