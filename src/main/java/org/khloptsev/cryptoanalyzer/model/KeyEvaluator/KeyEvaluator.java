package org.khloptsev.cryptoanalyzer.model.KeyEvaluator;

import java.util.List;

public class KeyEvaluator {
    private static final List<String> MOST_COMMON_RUSSIAN_WORDS = List.of(
            "и", "в", "не", "на", "я", "что", "тот", "с", "быть", "а",
            "по", "он", "это", "как", "она", "к", "у", "за", "мы", "вы",
            "из", "его", "для", "так", "же", "от", "сказать", "все", "но", "о"
    );

    private static final int WORD_THRESHOLD = 3;

    public static boolean evaluate(char[] chars) {
        int matchCount = 0;
        String text = new String(chars);
        for (String word : MOST_COMMON_RUSSIAN_WORDS) {
            String keyWord = String.format(" %s ", word);
            if (text.contains(keyWord)) {
                matchCount++;
            }
        }
        return matchCount >= WORD_THRESHOLD;
    }
}
