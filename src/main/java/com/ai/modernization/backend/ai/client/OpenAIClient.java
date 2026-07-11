package com.ai.modernization.backend.ai.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class OpenAIClient implements AIClient {

    private final ChatClient chatClient;

    public OpenAIClient(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public <T> T prompt(String prompt, Class<T> responseType) {

        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .entity(responseType);
    }
}