package dto;

import model.actions.Action;

import java.nio.file.Path;

public record CipherRequest(Action action, Path inputPath, Path outputPath, int key) {
}
