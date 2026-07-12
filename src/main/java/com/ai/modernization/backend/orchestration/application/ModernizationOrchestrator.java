package com.ai.modernization.backend.orchestration.application;

public interface ModernizationOrchestrator {

    void modernize(String githubFileUrl) throws Exception;

}