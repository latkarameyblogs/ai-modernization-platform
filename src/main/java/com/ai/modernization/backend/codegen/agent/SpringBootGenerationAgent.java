package com.ai.modernization.backend.codegen.agent;

import com.ai.modernization.backend.api.domain.ApiBlueprint;
import com.ai.modernization.backend.codegen.domain.GeneratedProject;

public interface SpringBootGenerationAgent {

    GeneratedProject generate(ApiBlueprint apiBlueprint);

}
