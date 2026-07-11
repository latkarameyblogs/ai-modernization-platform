package com.ai.modernization.backend.discovery.api;

import com.ai.modernization.backend.discovery.domain.DiscoveryDocument;

public record DiscoveryResponse(
        DiscoveryDocument discoveryResult
) {
}