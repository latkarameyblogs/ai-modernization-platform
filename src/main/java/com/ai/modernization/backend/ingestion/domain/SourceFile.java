package com.ai.modernization.backend.ingestion.domain;

public record SourceFile(

        String fileName,

        String relativePath,

        String content,

        SourceType sourceType

) {
}