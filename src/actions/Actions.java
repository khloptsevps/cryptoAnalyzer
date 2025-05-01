package actions;

public enum Actions {
    ENCRYPT("зашифровать"),
    DECRYPT("расшифровать");

    private final String label;

    Actions(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
