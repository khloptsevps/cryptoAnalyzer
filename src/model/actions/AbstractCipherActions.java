package model.actions;

import model.CaesarCipherModel;
import model.context.CipherContext;
import model.exceptions.ExceptionHandler;
import model.fileHandler.FileHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

abstract class AbstractCipherActions implements Action {
    protected abstract String getDefaultDir();
    protected abstract String getDefaultFilename();
    protected abstract char[] shift(CaesarCipherModel model, char[] chars, int shiftKey);
    private Path output;

    @Override
    public void execute(CipherContext context) {
        CaesarCipherModel cipherModel = context.getCipherModel();
        int chunkSize = context.getChunkSize();
        int shiftKey = context.getShiftKey();

        Path input = context.getInputPath();
        output = context.getOutputPath();

        try {
            output = FileHandler.create(output, getDefaultDir(), getDefaultFilename());
        } catch (IOException e) {
            ExceptionHandler.generateCreationException(e, output);
        }

        try (
                BufferedReader reader = Files.newBufferedReader(input);
                BufferedWriter writer = Files.newBufferedWriter(output)
        ) {
            char[] buffer = new char[chunkSize];
            while (reader.ready()) {
                int read = reader.read(buffer);
                char[] readChars = read < chunkSize ? Arrays.copyOf(buffer, read) : buffer;
                char[] result = shift(cipherModel, readChars, shiftKey);
                writer.write(result);
            }

        } catch (IOException e) {
            ExceptionHandler.generateException(e, input, output);
        }
    }

    protected String getOutputPath() {
        return output.toString();
    }
}
