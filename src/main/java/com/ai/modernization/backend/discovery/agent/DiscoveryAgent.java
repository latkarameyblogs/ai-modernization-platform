package com.ai.modernization.backend.discovery.agent;

import com.ai.modernization.backend.discovery.domain.DiscoveryDocument;

public interface DiscoveryAgent {

    DiscoveryDocument discover(String sourceCode);

}