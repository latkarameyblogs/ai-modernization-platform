package com.ai.modernization.backend.api.agent;

import com.ai.modernization.backend.ai.client.AIClient;
import com.ai.modernization.backend.api.domain.ApiBlueprint;
import com.ai.modernization.backend.api.prompt.ApiGenerationPromptBuilder;
import com.ai.modernization.backend.discovery.domain.DiscoveryDocument;
import org.springframework.stereotype.Service;

@Service
public class OpenAIApiGenerationAgent implements ApiGenerationAgent {

    private final AIClient aiClient;
    private final ApiGenerationPromptBuilder promptBuilder;

    public OpenAIApiGenerationAgent(AIClient aiClient,
                                    ApiGenerationPromptBuilder promptBuilder) {

        this.aiClient = aiClient;
        this.promptBuilder = promptBuilder;
    }

    @Override
    public ApiBlueprint generate(DiscoveryDocument discoveryDocument) {

        String prompt = promptBuilder.build(discoveryDocument);

        return aiClient.prompt(prompt, ApiBlueprint.class);
    }
}