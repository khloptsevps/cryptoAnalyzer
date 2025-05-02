package model.alphabet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс представляет алфавит.
 * Предоставляет набор методов для работы с алфавитом.
 */
public class Alphabet {
    private final Map<Character, Integer> ALPHABET_MAP = new HashMap<>();
    private final List<Character> ALPHABET_LIST = new ArrayList<>();

    public Alphabet(String alphabet) {
        for (int i = 0; i < alphabet.length(); i++) {
            char digit = alphabet.charAt(i);
            ALPHABET_MAP.put(digit, i);
            ALPHABET_LIST.add(digit);
        }
    }

    /**
     * Функция вычисляет размер алфавита.
     *
     * @return размер алфавита
     */
    public int getSize() {
        return ALPHABET_LIST.size();
    }

    /**
     * Функция возвращает индекс переданного символа.
     * Если символа нет в алфавите, вернет -1
     * @return index or -1
     */
    public int getIndexFromChar(char digit) {
        return ALPHABET_MAP.getOrDefault(digit, -1);
    }

    /**
     * Функция возвращает символ по переданному индексу.
     *
     * @return char
     *
     */
    public char getCharFromIndex(int index) {
        return ALPHABET_LIST.get(index);
    }
}
