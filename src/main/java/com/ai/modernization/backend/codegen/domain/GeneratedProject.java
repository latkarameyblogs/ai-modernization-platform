package com.ai.modernization.backend.codegen.domain;

import java.util.List;

public record GeneratedProject(

        String projectName,

        List<GeneratedFile> files

) {
}