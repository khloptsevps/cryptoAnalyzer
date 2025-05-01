package appView;

public interface View {
    void printMessage(String message);
    void printError(String message);
    String requestPath(String message);
    void showMenu();
    String requestAction(String message);
}
