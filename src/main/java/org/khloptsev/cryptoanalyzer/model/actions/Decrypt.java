package org.khloptsev.cryptoanalyzer.model.actions;

import org.khloptsev.cryptoanalyzer.model.CaesarCipherModel;

public class Decrypt extends AbstractCipherActions {
    @Override
    public String successMessage() {
        return "✅ Файл успешно расшифрован.\n✅ Результат сохранен по адресу: " + getOutputPath();
    }

    @Override
    String getDefaultDir() {
        return "decrypt/";
    }

    @Override
    String getDefaultFilename() {
        return "decrypt.txt";
    }

    @Override
    char[] shift(CaesarCipherModel model, char[] chars, int shiftKey) {
        return model.decrypt(chars, shiftKey);
    }
}
