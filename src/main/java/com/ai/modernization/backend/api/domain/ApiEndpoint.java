package com.ai.modernization.backend.api.domain;

public record ApiEndpoint(

        String method,

        String path,

        String description

) {
}