package com.ai.modernization.backend.runner;

import com.ai.modernization.backend.config.ModernizationProperties;

import com.ai.modernization.backend.orchestration.application.ModernizationOrchestrator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

//@Component
public class ModernizationRunner implements CommandLineRunner {

    private final ModernizationOrchestrator modernizationOrchestrator;
    private final ModernizationProperties modernizationProperties;
    public ModernizationRunner(
            ModernizationOrchestrator modernizationOrchestrator,
            ModernizationProperties modernizationProperties) {

        this.modernizationOrchestrator = modernizationOrchestrator;
        this.modernizationProperties = modernizationProperties;
    }

    @Override
    public void run(String... args) throws Exception {

        modernizationOrchestrator.modernize(
                modernizationProperties.getGithubFileUrl());

    }
}