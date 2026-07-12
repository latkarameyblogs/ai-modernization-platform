package com.ai.modernization.backend.orchestration.application;

import com.ai.modernization.backend.api.agent.ApiGenerationAgent;
import com.ai.modernization.backend.api.domain.ApiBlueprint;
import com.ai.modernization.backend.codegen.agent.SpringBootGenerationAgent;
import com.ai.modernization.backend.codegen.domain.GeneratedProject;
import com.ai.modernization.backend.codegen.io.GeneratedProjectWriter;
import com.ai.modernization.backend.common.io.JsonArtifactWriter;
import com.ai.modernization.backend.discovery.agent.DiscoveryAgent;
import com.ai.modernization.backend.discovery.domain.DiscoveryDocument;
import com.ai.modernization.backend.ingestion.agent.ApplicationIngestionAgent;
import com.ai.modernization.backend.ingestion.domain.ModernizationWorkspace;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class DefaultModernizationOrchestrator implements ModernizationOrchestrator {

    private final ApplicationIngestionAgent ingestionAgent;
    private final DiscoveryAgent discoveryAgent;
    private final ApiGenerationAgent apiGenerationAgent;
    private final SpringBootGenerationAgent springBootGenerationAgent;
    private final JsonArtifactWriter jsonArtifactWriter;
    private final GeneratedProjectWriter generatedProjectWriter;

    public DefaultModernizationOrchestrator(
            ApplicationIngestionAgent ingestionAgent,
            DiscoveryAgent discoveryAgent,
            ApiGenerationAgent apiGenerationAgent,
            SpringBootGenerationAgent springBootGenerationAgent,
            JsonArtifactWriter jsonArtifactWriter,
            GeneratedProjectWriter generatedProjectWriter) {

        this.ingestionAgent = ingestionAgent;
        this.discoveryAgent = discoveryAgent;
        this.apiGenerationAgent = apiGenerationAgent;
        this.springBootGenerationAgent = springBootGenerationAgent;
        this.jsonArtifactWriter = jsonArtifactWriter;
        this.generatedProjectWriter = generatedProjectWriter;
    }

    @Override
    public void modernize(String githubFileUrl) throws Exception {

        ModernizationWorkspace workspace =
                ingestionAgent.ingest(githubFileUrl);

        String sourceCode =
                workspace.sourceFiles().get(0).content();

        DiscoveryDocument discoveryDocument =
                discoveryAgent.discover(sourceCode);

        jsonArtifactWriter.write(
                discoveryDocument,
                Path.of(
                        "artifacts",
                        "discovery",
                        discoveryDocument.programName() + ".json"));

        ApiBlueprint apiBlueprint =
                apiGenerationAgent.generate(discoveryDocument);

        jsonArtifactWriter.write(
                apiBlueprint,
                Path.of(
                        "artifacts",
                        "api",
                        apiBlueprint.microserviceName() + ".json"));

        GeneratedProject generatedProject =
                springBootGenerationAgent.generate(apiBlueprint);

        generatedProjectWriter.write(generatedProject);
    }
}