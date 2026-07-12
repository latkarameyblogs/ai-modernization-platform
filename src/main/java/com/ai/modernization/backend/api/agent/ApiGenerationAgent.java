package com.ai.modernization.backend.api.agent;

import com.ai.modernization.backend.api.domain.ApiBlueprint;
import com.ai.modernization.backend.discovery.domain.DiscoveryDocument;

public interface ApiGenerationAgent {

    ApiBlueprint generate(DiscoveryDocument discoveryDocument);

}