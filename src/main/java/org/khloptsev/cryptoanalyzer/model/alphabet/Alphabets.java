package org.khloptsev.cryptoanalyzer.model.alphabet;

public class Alphabets {
    private static final String russianAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String digits = "0123456789";
    private static final String punctuation = ".,:;!?\"'()-[]{}<>«»— ";

    public static String getRussianAlphabet() {
        return russianAlphabet + digits + punctuation;
    }
}
