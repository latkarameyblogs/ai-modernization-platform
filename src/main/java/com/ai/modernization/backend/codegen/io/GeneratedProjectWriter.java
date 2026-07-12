package com.ai.modernization.backend.codegen.io;

import com.ai.modernization.backend.codegen.domain.GeneratedFile;
import com.ai.modernization.backend.codegen.domain.GeneratedProject;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class GeneratedProjectWriter {

    public void write(GeneratedProject project) throws Exception {

        Path root = Path.of("generated", sanitize(project.projectName()));

        Files.createDirectories(root);

        for (GeneratedFile file : project.files()) {

            Path target = root.resolve(file.path());

            Files.createDirectories(target.getParent());

            Files.writeString(target, file.content());

            System.out.println("Generated : " + target);
        }
    }

    private String sanitize(String projectName) {

        return projectName
                .toLowerCase()
                .replace(" ", "-");
    }

}