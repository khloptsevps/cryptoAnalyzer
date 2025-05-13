package model.actions;

import model.CaesarCipherModel;

public class Encrypt extends AbstractCipherActions {
    @Override
    public String successMessage() {
        return "✅ Файл успешно зашифрован.\n✅ Результат сохранен по адресу: " + getOutputPath();
    }

    @Override
    protected String getDefaultDir() {
        return "encrypt/";
    }

    @Override
    protected String getDefaultFilename() {
        return "encrypt.txt";
    }

    @Override
    protected char[] shift(CaesarCipherModel model, char[] chars, int shiftKey) {
        return model.encrypt(chars, shiftKey);
    }
}

