package model;

import model.alphabet.Alphabet;

public class CaesarCipherModel {
    private final String abTest = "абвгдеёжзий";

    Alphabet alphabet = new Alphabet(abTest);

    public int getAlphabetSize() {
        return alphabet.getSize();
    }

}
