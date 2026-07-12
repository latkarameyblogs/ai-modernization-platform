package com.ai.modernization.backend.ingestion.domain;

import java.util.List;

public record ModernizationWorkspace(

        String applicationName,

        List<SourceFile> sourceFiles

) {
}