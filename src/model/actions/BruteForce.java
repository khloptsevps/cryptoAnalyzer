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


public class BruteForce implements Action {
    String defaultDir = "bruteforce/";
    String defaultFilename = "attempt";
    int chunkSize;
    Path outputPath;
    Path bruteForceDir;

    @Override
    public void execute(CipherContext context) {
        CaesarCipherModel model = context.getCipherModel();
        chunkSize = context.getChunkSize();
        int attemptsCount = model.getAlphabetSize();
        Path inputPath = context.getInputPath();
        outputPath = context.getOutputPath();
        String outputFilename = outputPath.getFileName().toString();

        if (!outputFilename.endsWith(".txt")) {
            bruteForceDir = outputPath.resolve(defaultDir);
        } else {
            bruteForceDir = outputPath.getParent().resolve(defaultDir);
        }

        for (int shiftKey = 1; shiftKey < attemptsCount; shiftKey++) {
            String filename;
            Path output;

            if (!outputFilename.endsWith(".txt")) {
                filename = defaultFilename + "_" + shiftKey + ".txt";
            } else {
                filename = outputFilename.substring(0, outputFilename.indexOf(".")) + "_" + shiftKey + ".txt";
            }

            output = bruteForceDir.resolve(filename);

            try {
                FileHandler.create(output);
            } catch (IOException e) {
                ExceptionHandler.generateCreationException(e, output);
            }

            try {
                tryDecrypt(inputPath, output, shiftKey, model);
            } catch (IOException e) {
                ExceptionHandler.generateException(e, inputPath, output);
            }
        }

    }

    private void tryDecrypt(Path inputPath, Path outputPath, int shiftKey, CaesarCipherModel model) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(inputPath);
             BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            char[] buffer = new char[chunkSize];
            while (reader.ready()) {
                int read = reader.read(buffer);
                char[] readChars = read < chunkSize ? Arrays.copyOf(buffer, read) : buffer;
                char[] result = model.decrypt(readChars, shiftKey);
                writer.write(result);
            }
        }
    }

    @Override
    public String successMessage() {
        return "✅ Результаты сохранены по адресу: " + bruteForceDir;
    }
}
