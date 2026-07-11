package com.ai.modernization.backend.common.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class JsonArtifactWriter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void write(Object object, Path outputFile) throws Exception {

        Files.createDirectories(outputFile.getParent());

        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(outputFile.toFile(), object);
    }

}