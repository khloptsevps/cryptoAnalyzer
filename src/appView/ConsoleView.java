package appView;

import model.actions.Actions;
import java.util.Scanner;

public class ConsoleView implements View {
    Scanner console = new Scanner(System.in);

    @Override
    public void printMessage(String message) {
        System.out.println(message + "\n");
    }

    @Override
    public void printError(String message) {
        System.out.printf("❌ %s%n", message);
    }

    @Override
    public String requestPath(String message) {
        System.out.print("\uD83D\uDD24 " + message);
        return console.nextLine();
    }

    @Override
    public String requestUserInput(String message) {
        System.out.print("\uD83D\uDD22 " + message);
        return console.nextLine();
    }

    @Override
    public void showMenu(Actions[] actions) {
        System.out.println("\uD83D\uDCCB Доступные действия: ");
        for (int i = 0; i < actions.length; i++) {
            System.out.printf("\t %d. %s%n", i + 1,  actions[i].getLabel());
        }
        System.out.println();
    }
}
