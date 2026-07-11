package com.ai.modernization.backend.runner;

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

    public ModernizationRunner(
            DiscoveryAgent discoveryAgent,
            JsonArtifactWriter jsonArtifactWriter) {

        this.discoveryAgent = discoveryAgent;
        this.jsonArtifactWriter = jsonArtifactWriter;
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

    }
}