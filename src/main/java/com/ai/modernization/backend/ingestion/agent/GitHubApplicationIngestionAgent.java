package com.ai.modernization.backend.ingestion.agent;

import com.ai.modernization.backend.ingestion.domain.ModernizationWorkspace;
import com.ai.modernization.backend.ingestion.domain.SourceFile;
import com.ai.modernization.backend.ingestion.domain.SourceType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GitHubApplicationIngestionAgent implements ApplicationIngestionAgent {

    private final RestClient restClient;

    public GitHubApplicationIngestionAgent(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    @Override
    public ModernizationWorkspace ingest(String githubFileUrl) {

        System.out.println();
        System.out.println("=======================================");
        System.out.println("APPLICATION INGESTION AGENT");
        System.out.println("=======================================");

        System.out.println("Downloading : " + githubFileUrl);

        String sourceCode = restClient
                .get()
                .uri(githubFileUrl)
                .retrieve()
                .body(String.class);

        SourceFile sourceFile = new SourceFile(
                extractFileName(githubFileUrl),
                githubFileUrl,
                sourceCode,
                SourceType.COBOL
        );

        return new ModernizationWorkspace(
                extractProgramName(githubFileUrl),
                List.of(sourceFile)
        );
    }

    /**
     * Converts
     *
     * https://github.com/IBM/cics-genapp
     *
     * to
     *
     * https://raw.githubusercontent.com/IBM/cics-genapp/main/base/src/LGACDB01.cbl
     */
    private String buildRawGitHubUrl(
            String repositoryUrl,
            String applicationFolder,
            String entryProgram) {

        String base = repositoryUrl
                .replace("https://github.com/",
                        "https://raw.githubusercontent.com/");

        return base +
                "/main/" +
                applicationFolder +
                "/" +
                entryProgram +
                ".cbl";
    }

    private String extractFileName(String url) {

        return url.substring(url.lastIndexOf('/') + 1);

    }

    private String extractProgramName(String url) {

        String file = extractFileName(url);

        return file.substring(0, file.lastIndexOf('.'));

    }
}