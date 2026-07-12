package com.ai.modernization.backend.api.domain;

import java.util.List;

public record ApiBlueprint(

        String microserviceName,

        String boundedContext,

        List<String> entities,

        List<ApiEndpoint> endpoints,

        String database,

        List<String> externalDependencies

) {
}