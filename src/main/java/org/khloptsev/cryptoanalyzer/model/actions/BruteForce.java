package org.khloptsev.cryptoanalyzer.model.actions;

import org.khloptsev.cryptoanalyzer.model.CaesarCipherModel;
import org.khloptsev.cryptoanalyzer.model.KeyEvaluator.KeyEvaluator;
import org.khloptsev.cryptoanalyzer.model.context.CipherContext;
import org.khloptsev.cryptoanalyzer.model.exceptions.ExceptionHandler;
import org.khloptsev.cryptoanalyzer.model.fileHandler.FileHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;


public class BruteForce implements Action {
    String defaultDir = "bruteforce/";
    String defaultFilename = "attempt";
    int chunkSize;
    Path outputPath;
    Path bruteForceDir;
    private ArrayList<Integer> successKeys = new ArrayList<>();
    private int successKey;

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
            String template = "%s_%d.txt";
            if (!outputFilename.endsWith(".txt")) {
                filename = String.format(template, defaultFilename, shiftKey);
            } else {
                filename = String.format(template, outputFilename.substring(0, outputFilename.indexOf(".")), shiftKey);
            }

            output = bruteForceDir.resolve(filename);

            try {
                FileHandler.create(output);
            } catch (IOException e) {
                ExceptionHandler.generateCreationException(e, output);
            }

            try {
                int detectedKey = detectKey(inputPath, shiftKey, model);
                if (detectedKey > 0) {
                    successKey = detectedKey;
                    tryDecrypt(inputPath, output, successKey, model);
                } else {
                    FileHandler.removeFile(output);
                }

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

    private int detectKey(Path inputPath, int key, CaesarCipherModel model) throws IOException {
        try(BufferedReader reader = Files.newBufferedReader(inputPath)) {
            char[] buffer = new char[chunkSize];
            int read = reader.read(buffer);
            char[] readChars = read < chunkSize ? Arrays.copyOf(buffer, read) : buffer;
            char[] result = model.decrypt(readChars, key);
            return KeyEvaluator.evaluate(result) ? key : 0;
        }
    }

    @Override
    public String successMessage() {
        if (successKey < 1) {
            return "🔒 Не удалось подобрать ключ. Попробуйте вручную.";
        }
        int detectedKey = successKey;
        successKey = 0;
        return String.format("✅ Результаты сохранены по адресу: %s%n\uD83D\uDD11 Ключи: %d", bruteForceDir, detectedKey);
    }
}
