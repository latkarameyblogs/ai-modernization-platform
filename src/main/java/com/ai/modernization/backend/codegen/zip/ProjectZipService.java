package com.ai.modernization.backend.codegen.zip;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ProjectZipService {

    public byte[] zipModernizationOutput(
            String discoveryFolder,
            String apiFolder,
            String generatedProjectFolder) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (ZipOutputStream zos = new ZipOutputStream(baos)) {

            addFolder(zos, Path.of(discoveryFolder), "discovery");

            addFolder(zos, Path.of(apiFolder), "api");

            addFolder(zos, Path.of(generatedProjectFolder), "springboot");

        }

        return baos.toByteArray();
    }

    private void addFolder(
            ZipOutputStream zos,
            Path root,
            String zipRoot) throws IOException {

        Files.walk(root)
                .filter(Files::isRegularFile)
                .forEach(path -> {

                    try {

                        String entryName =
                                zipRoot + "/" + root.relativize(path);

                        zos.putNextEntry(new ZipEntry(entryName));

                        Files.copy(path, zos);

                        zos.closeEntry();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });

    }
}