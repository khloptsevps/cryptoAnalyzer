package model.actions;

import model.CaesarCipherModel;
import model.alphabet.Alphabet;
import model.context.CipherContext;
import java.io.IOException;


public class Encrypt implements Action {
    @Override
    public void execute(CipherContext context) {
        CaesarCipherModel cipherModel = context.getCipherModel();
        Alphabet alphabet = cipherModel.getAlphabet();
        try {
            cipherModel.openStreams(context.getInputPath());
            char[] currentChars;
            while ((currentChars = cipherModel.readChunk(context.getChunkSize())) != null) {
                char[] encrypted = new char[currentChars.length];
                for (int i = 0; i < currentChars.length; i++) {
                    char currentChar = currentChars[i];
                    boolean isUpperCase = Character.isUpperCase(currentChar);
                    char newChar = alphabet.shift(currentChar, context.getShiftKey());
                    encrypted[i] = isUpperCase ? Character.toUpperCase(newChar) : newChar;
                }
            }

            cipherModel.closeStreams();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

