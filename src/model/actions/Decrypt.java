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

public class Decrypt implements Action{
    @Override
    public void execute(CipherContext context) {
        String defaultDir = "decrypt/";
        String defaultName = "decrypt.txt";
        CaesarCipherModel cipherModel = context.getCipherModel();
        int chunkSize = context.getChunkSize();
        int shiftKey = context.getShiftKey();

        Path input = context.getInputPath();
        Path output = context.getOutputPath();

        try {
            output = FileHandler.create(output, defaultDir, defaultName);
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
                char[] decrypted = cipherModel.decrypt(readChars, shiftKey);
                writer.write(decrypted);
            }
        } catch (IOException e) {
            ExceptionHandler.generateException(e, input, output);
        }
    }
}
