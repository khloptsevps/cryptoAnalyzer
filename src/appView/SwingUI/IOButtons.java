package appView.SwingUI;

public enum IOButtons {
    INPUT("\uD83D\uDCDC Открыть файл"),
    OUTPUT("\uD83D\uDCC1 Сохранить в файл или директорию" );

    private final String LABEL;

    IOButtons(String label) {
        LABEL = label;
    }

    public String getLabel() {
        return LABEL;
    }
}
