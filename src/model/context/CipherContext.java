package model.context;

import model.CaesarCipherModel;

import java.nio.file.Path;

public class CipherContext {
    final int CHUNK_SIZE = 50;
    final CaesarCipherModel cipherModel;
    final Path inputPath;
    final Path outputPath;
    final int shiftKey;

    public CipherContext(CaesarCipherModel cipherModel, Path inputPath, Path outputPath, int shiftKey) {
        this.cipherModel = cipherModel;
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.shiftKey = shiftKey;
    }

    public CaesarCipherModel getCipherModel() {
        return cipherModel;
    }

    public Path getInputPath() {
        return inputPath;
    }

    public Path getOutputPath() {
        return outputPath;
    }

    public int getShiftKey() {
        return shiftKey;
    }

    public int getChunkSize() {
        return CHUNK_SIZE;
    }
}
