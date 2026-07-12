package com.ai.modernization.backend.ingestion.agent;

import com.ai.modernization.backend.ingestion.domain.ModernizationWorkspace;

public interface ApplicationIngestionAgent {

    ModernizationWorkspace ingest(
            String githubFileUrl
    ) throws Exception;

}