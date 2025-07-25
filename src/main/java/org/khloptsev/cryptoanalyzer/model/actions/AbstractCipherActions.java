package org.khloptsev.cryptoanalyzer.model.actions;

import org.khloptsev.cryptoanalyzer.model.CaesarCipherModel;
import org.khloptsev.cryptoanalyzer.model.context.CipherContext;
import org.khloptsev.cryptoanalyzer.model.exceptions.ExceptionHandler;
import org.khloptsev.cryptoanalyzer.model.fileHandler.FileHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

abstract class AbstractCipherActions implements Action {
    abstract String getDefaultDir();
    abstract String getDefaultFilename();
    abstract char[] shift(CaesarCipherModel model, char[] chars, int shiftKey);
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

    String getOutputPath() {
        return output.toString();
    }
}
