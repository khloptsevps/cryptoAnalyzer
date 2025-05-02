package model.dto;

import java.nio.file.Path;

public class CipherRequest {
    private final String action;
    private final Path inputPath;
    private final Path outputPath;
    private final int key;

    public CipherRequest(String action, Path inputPath, Path outputPath, int key) {
        this.action = action;
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.key = key;
    }

    public String getAction() {
        return action;
    }

    public Path getInputPath() {
        return inputPath;
    }

    public Path getOutputPath() {
        return outputPath;
    }

    public int getKey() {
        return key;
    }
}
