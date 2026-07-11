package com.ai.modernization.backend.discovery.agent;

import com.ai.modernization.backend.ai.client.AIClient;
import com.ai.modernization.backend.discovery.domain.DiscoveryDocument;
import org.springframework.stereotype.Service;
import com.ai.modernization.backend.discovery.prompt.DiscoveryPromptBuilder;

@Service
public class OpenAIDiscoveryAgent implements DiscoveryAgent {

    private final AIClient aiClient;
    private final DiscoveryPromptBuilder promptBuilder;

    public OpenAIDiscoveryAgent(AIClient aiClient,
                                DiscoveryPromptBuilder promptBuilder) {
        this.aiClient = aiClient;
        this.promptBuilder = promptBuilder;
    }

    @Override
    public DiscoveryDocument discover(String sourceCode) {

        String prompt = promptBuilder.build(sourceCode);

        return aiClient.prompt(prompt, DiscoveryDocument.class);
    }
}