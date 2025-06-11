package org.khloptsev.cryptoanalyzer.model.actions;

import org.khloptsev.cryptoanalyzer.model.CaesarCipherModel;

public class Encrypt extends AbstractCipherActions {
    @Override
    public String successMessage() {
        return "✅ Файл успешно зашифрован.\n✅ Результат сохранен по адресу: " + getOutputPath();
    }

    @Override
    String getDefaultDir() {
        return "encrypt/";
    }

    @Override
    String getDefaultFilename() {
        return "encrypt.txt";
    }

    @Override
    char[] shift(CaesarCipherModel model, char[] chars, int shiftKey) {
        return model.encrypt(chars, shiftKey);
    }
}

