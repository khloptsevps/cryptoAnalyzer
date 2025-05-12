package model.alphabet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Alphabet {
    private final Map<Character, Integer> ALPHABET_MAP = new HashMap<>();
    private final List<Character> ALPHABET_LIST = new ArrayList<>();

    public Alphabet(String alphabet) {
        for (int i = 0; i < alphabet.length(); i++) {
            char ch = alphabet.charAt(i);
            ALPHABET_MAP.put(ch, i);
            ALPHABET_LIST.add(ch);
        }
    }

    public int getSize() {
        return ALPHABET_LIST.size();
    }

    public char shift(char ch, int shiftKey) {
        int currentIndex = ALPHABET_MAP.getOrDefault(Character.toLowerCase(ch), -1);
        if (currentIndex < 0) {
            return ch;
        }

        int newIndex = (currentIndex + shiftKey) % getSize();

        if (newIndex < 0) {
            newIndex += getSize();
        }

        return ALPHABET_LIST.get(newIndex);
    }
}
