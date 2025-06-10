package model.actions;

public enum Actions {
    ENCRYPT("\uD83D\uDD10 Зашифровать", new Encrypt()),
    DECRYPT("\uD83D\uDD13 Расшифровать", new Decrypt()),
    BRUTEFORCE("\uD83D\uDD75 Брутфорс (подобрать ключ)", new BruteForce()),
    EXIT("\uD83D\uDC4B Завершить работу приложения", new Exit());

    private final String label;
    private final Action action;

    Actions(String label, Action action) {
        this.label = label;
        this.action = action;
    }

    public String getLabel() {
        return this.label;
    }

    public Action getAction() {
        return this.action;
    }

    public static Action getActionByName(String actionName) {
          return Actions.valueOf(actionName.toUpperCase()).getAction();
    }
}
