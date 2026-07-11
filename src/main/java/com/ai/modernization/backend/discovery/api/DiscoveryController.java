package com.ai.modernization.backend.discovery.api;

import com.ai.modernization.backend.discovery.agent.DiscoveryAgent;
import com.ai.modernization.backend.discovery.domain.DiscoveryDocument;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/discovery")
public class DiscoveryController {

    private final DiscoveryAgent discoveryAgent;

    public DiscoveryController(DiscoveryAgent discoveryAgent) {
        this.discoveryAgent = discoveryAgent;
    }

    @PostMapping("/analyze")
    public DiscoveryResponse analyze(@RequestBody DiscoveryRequest request) {

        DiscoveryDocument discoveryResult =
                discoveryAgent.discover(request.sourceCode());

        return new DiscoveryResponse(discoveryResult);
    }
}