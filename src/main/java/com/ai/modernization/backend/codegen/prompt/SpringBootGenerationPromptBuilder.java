package com.ai.modernization.backend.codegen.prompt;

import com.ai.modernization.backend.api.domain.ApiBlueprint;
import org.springframework.stereotype.Component;

@Component
public class SpringBootGenerationPromptBuilder {

    public String build(ApiBlueprint blueprint) {

        return """
You are a Senior Java Developer.

Generate a MINIMAL but COMPILABLE Spring Boot 3.5 project.

Technology Stack:
- Java 21
- Maven
- Spring Boot 3.5
- Spring Web
- Validation
- No Lombok

Generate ONLY these files:

1. pom.xml
2. README.md
3. src/main/resources/application.yml
4. src/main/java/com/modernization/CustomerApplication.java
5. src/main/java/com/modernization/controller/CustomerController.java
6. src/main/java/com/modernization/service/CustomerService.java

Return ONLY JSON matching the GeneratedProject schema.

Every GeneratedFile must contain:

- path
- content

API Blueprint:

%s
""".formatted(blueprint);

    }

}