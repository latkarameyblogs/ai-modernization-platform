package com.ai.modernization.backend.codegen.agent;

import com.ai.modernization.backend.ai.client.AIClient;
import com.ai.modernization.backend.api.domain.ApiBlueprint;
import com.ai.modernization.backend.codegen.domain.GeneratedProject;
import com.ai.modernization.backend.codegen.prompt.SpringBootGenerationPromptBuilder;
import org.springframework.stereotype.Service;

@Service
public class OpenAISpringBootGenerationAgent implements SpringBootGenerationAgent {

    private final AIClient aiClient;
    private final SpringBootGenerationPromptBuilder promptBuilder;

    public OpenAISpringBootGenerationAgent(
            AIClient aiClient,
            SpringBootGenerationPromptBuilder promptBuilder) {

        this.aiClient = aiClient;
        this.promptBuilder = promptBuilder;
    }

    @Override
    public GeneratedProject generate(ApiBlueprint apiBlueprint) {

        String prompt = promptBuilder.build(apiBlueprint);

        return aiClient.prompt(prompt, GeneratedProject.class);
    }
}