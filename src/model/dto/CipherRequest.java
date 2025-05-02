package model.dto;

import java.nio.file.Path;

public record CipherRequest(String action, Path inputPath, Path outputPath, int key) {
}
