package model.actions;

public enum Actions {
    ENCRYPT("\uD83D\uDD10 Зашифровать"),
    DECRYPT("\uD83D\uDD13 Расшифровать"),
    EXIT("\uD83D\uDC4B Завершить работу приложения");

    private final String label;

    Actions(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
