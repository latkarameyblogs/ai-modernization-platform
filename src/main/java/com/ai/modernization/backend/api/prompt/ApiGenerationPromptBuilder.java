package com.ai.modernization.backend.api.prompt;

import com.ai.modernization.backend.discovery.domain.DiscoveryDocument;
import org.springframework.stereotype.Component;

@Component
public class ApiGenerationPromptBuilder {

    public String build(DiscoveryDocument document) {

        return """
You are an Enterprise Solution Architect.

Your task is to design a Spring Boot microservice from the supplied DiscoveryDocument.

Return ONLY JSON matching the ApiBlueprint schema.

Design principles:

- Single Responsibility Principle
- Domain Driven Design
- RESTful APIs
- PostgreSQL
- Stateless Microservice
- Spring Boot 3.x

Discovery Document:

%s
""".formatted(document);

    }
}