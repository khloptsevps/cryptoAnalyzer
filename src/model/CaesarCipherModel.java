package model;

import model.alphabet.Alphabet;
import model.alphabet.Alphabets;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class CaesarCipherModel {
    private BufferedReader reader;

    Alphabet alphabet = new Alphabet(Alphabets.getRussianAlphabet());

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public int getAlphabetSize() {
        return alphabet.getSize();
    }


    public void openStreams(Path pathToFile) throws IOException {
        reader = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8);
    }

    public void closeStreams() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }

    public char[] readChunk(int chunkSize) throws IOException {
        char[] buffer = new char[chunkSize];
        int charsRead = reader.read(buffer);
        if (charsRead == -1) {
            return null;
        }
        if (charsRead < chunkSize) {
            return Arrays.copyOf(buffer, charsRead);
        }
        return buffer;
    }
}
