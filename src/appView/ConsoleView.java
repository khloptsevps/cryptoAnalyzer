package appView;

import actions.Actions;
import java.util.Scanner;

public class ConsoleView implements View {
    Scanner console = new Scanner(System.in);

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printError(String message) {
        System.out.printf("❌ %s ", message);
    }

    @Override
    public String requestPath(String message) {
        System.out.print(message);
        return console.nextLine();
    }

    @Override
    public String requestAction(String message) {
        System.out.print(message);
        return console.nextLine();
    }

    @Override
    public void showMenu() {
        System.out.println("\uD83D\uDCCB Доступные действия: ");
        Actions[] actions = Actions.values();
        for (int i = 0; i < actions.length; i++) {
            System.out.printf("\t %d. %s - %s%n", i + 1, actions[i].name().toLowerCase(), actions[i].getLabel());
        }
    }
}
