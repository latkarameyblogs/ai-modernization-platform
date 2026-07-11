package com.ai.modernization.backend.discovery.prompt;

import org.springframework.stereotype.Component;

@Component
public class DiscoveryPromptBuilder {

    public String build(String sourceCode) {

        return """
                You are a Senior IBM Mainframe Modernization Architect.

                Your responsibility is to understand legacy mainframe applications.

                Analyze the following legacy source code.

                Return ONLY structured JSON matching the DiscoveryDocument schema.

                Do not explain anything outside the JSON.

                Identify:

                - Program Name
                - Summary
                - Business Purpose
                - Inputs
                - Outputs
                - Dependencies
                - External Programs
                - Files / DB2 / VSAM
                - CICS Usage
                - Batch Processing Details
                - Suggested Microservice
                - Assumptions

                Legacy Source Code:

                %s
                """.formatted(sourceCode);

    }
}