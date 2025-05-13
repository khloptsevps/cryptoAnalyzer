package model.actions;

import model.CaesarCipherModel;

public class Decrypt extends AbstractCipherActions {
    @Override
    public String successMessage() {
        return "✅ Файл успешно расшифрован.\n✅ Результат сохранен по адресу: " + getOutputPath();
    }

    @Override
    protected String getDefaultDir() {
        return "decrypt/";
    }

    @Override
    protected String getDefaultFilename() {
        return "decrypt.txt";
    }

    @Override
    protected char[] shift(CaesarCipherModel model, char[] chars, int shiftKey) {
        return model.decrypt(chars, shiftKey);
    }
}
