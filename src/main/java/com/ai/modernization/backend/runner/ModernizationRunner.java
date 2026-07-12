package com.ai.modernization.backend.runner;

import com.ai.modernization.backend.api.agent.ApiGenerationAgent;
import com.ai.modernization.backend.api.domain.ApiBlueprint;
import com.ai.modernization.backend.codegen.agent.SpringBootGenerationAgent;
import com.ai.modernization.backend.codegen.domain.GeneratedProject;
import com.ai.modernization.backend.codegen.io.GeneratedProjectWriter;
import com.ai.modernization.backend.common.io.JsonArtifactWriter;
import com.ai.modernization.backend.discovery.agent.DiscoveryAgent;
import com.ai.modernization.backend.discovery.domain.DiscoveryDocument;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class ModernizationRunner implements CommandLineRunner {

    private final DiscoveryAgent discoveryAgent;

    private final JsonArtifactWriter jsonArtifactWriter;

    private final ApiGenerationAgent apiGenerationAgent;

    private final GeneratedProjectWriter generatedProjectWriter;


    private final SpringBootGenerationAgent springBootGenerationAgent;

    public ModernizationRunner(
            DiscoveryAgent discoveryAgent,
            ApiGenerationAgent apiGenerationAgent,
            SpringBootGenerationAgent springBootGenerationAgent,
            JsonArtifactWriter jsonArtifactWriter, GeneratedProjectWriter generatedProjectWriter1) {

        this.discoveryAgent = discoveryAgent;
        this.apiGenerationAgent = apiGenerationAgent;
        this.springBootGenerationAgent = springBootGenerationAgent;
        this.jsonArtifactWriter = jsonArtifactWriter;
        this.generatedProjectWriter = generatedProjectWriter1;
    }
    @Override
    public void run(String... args) throws Exception {

        Path cobolFile = Path.of("C:\\projects\\modernization\\cics-genapp\\base\\src\\lgacdb01.cbl");

        String sourceCode = Files.readString(cobolFile);

        DiscoveryDocument discoveryDocument =
                discoveryAgent.discover(sourceCode);

        Path output = Path.of(
                "artifacts",
                "discovery",
                discoveryDocument.programName() + ".json"
        );

        jsonArtifactWriter.write(discoveryDocument, output);

        System.out.println("Discovery artifact created : " + output);

        System.out.println(discoveryDocument);

        ApiBlueprint apiBlueprint =
                apiGenerationAgent.generate(discoveryDocument);

        System.out.println(apiBlueprint);

        Path apiArtifact = Path.of(
                "artifacts",
                "api",
                apiBlueprint.microserviceName() + ".json"
        );

        jsonArtifactWriter.write(apiBlueprint, apiArtifact);

        System.out.println("API Blueprint created : " + apiArtifact);

        GeneratedProject generatedProject =
                springBootGenerationAgent.generate(apiBlueprint);

        System.out.println(generatedProject.projectName());

        System.out.println("Files Generated : " +
                generatedProject.files().size());

        generatedProjectWriter.write(generatedProject);

        System.out.println("Spring Boot project generated successfully.");


    }
}