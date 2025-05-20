package model.context;

import model.CaesarCipherModel;

import java.nio.file.Path;

public class CipherContext {
    private final int CHUNK_SIZE = 8192;
    private CaesarCipherModel cipherModel;
    private Path inputPath;
    private Path outputPath;
    private int shiftKey;

    public CipherContext() {
        this.cipherModel = null;
        this.inputPath = null;
        this.outputPath = null;
        this.shiftKey = -1;
    }

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

    public void setCipherModel(CaesarCipherModel model) {
        this.cipherModel = model;
    }

    public void setInputPath(Path path) {
        this.inputPath = path;
    }

    public void setOutputPath(Path path) {
        this.outputPath = path;
    }

    public void setShiftKey(int key) {
        this.shiftKey = key;
    }
}
