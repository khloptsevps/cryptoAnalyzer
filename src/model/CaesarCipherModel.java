package model;

import model.alphabet.Alphabet;

public class CaesarCipherModel {
    /**
    private final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
            "0123456789.,!?\"':;-() ";
    */

    private final String abTest = "абвгдеёжзий";

    Alphabet alphabet = new Alphabet(abTest);


    public int getAlphabetSize() {
        return alphabet.getSize();
    }

    public char getAlphabetChar(int index) {
        return alphabet.getCharFromIndex(index);
    }
}
