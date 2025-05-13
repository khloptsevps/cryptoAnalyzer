package model;

import model.alphabet.Alphabet;
import model.alphabet.Alphabets;

public class CaesarCipherModel {

    Alphabet alphabet = new Alphabet(Alphabets.getRussianAlphabet());

    public int getAlphabetSize() {
        return alphabet.getSize();
    }

    public char[] encrypt(char[] chars, int shiftKey) {
        char[] result = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            char shiftedChar = alphabet.shift(currentChar, shiftKey);
            result[i] = shiftedChar;
        }
        return result;
    }

    public char[] decrypt(char[] chars, int shiftKey) {
        return encrypt(chars, -shiftKey);
    }
}
