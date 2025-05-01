package appController;

import actions.Actions;
import appView.View;
import util.PathBuilder;
import util.Validator;

import java.nio.file.Path;

public class ConsoleController {
    private final View view;
    private String message;

    private String action;
    private Path pathToInputFile;



    public ConsoleController(View view) {
        this.view = view;
    }

    PathBuilder pb = new PathBuilder();


    public void run() {
        message = "***** Шифр Цезаря (Консольный режим) *****";
        view.printMessage(message);

        view.showMenu();
        message = "\uD83D\uDD22 Выберите действие: ";
        action = getAction(message);

        message = "\uD83D\uDD24 Введите путь к исходному файлу: ";
        pathToInputFile = getPathToInputFile(message);

        showInfo();
    }

    private void showInfo() {
        System.out.println("----------------------------------------");
        System.out.println("Состояние контроллера:");
        System.out.println("Действие: " + action);
        System.out.println("Путь ко входящему файлу: " + pathToInputFile);
        System.out.println("Путь к исходящему файлу или директории: отсутствует");
        System.out.println("Ключ шифрования: отсутствует");
        message = "Завершение работы программы!";
        view.printMessage(message);
        System.out.println("----------------------------------------");
    }

    private String getAction(String message) {
        String result;
        Actions[] actions = Actions.values();
        while (true) {
            try {
                String input = view.requestAction(message);
                result = actions[Integer.parseInt(input) - 1].name().toLowerCase();
                return result;
            } catch (NumberFormatException e) {
                view.printError("Необходимо ввести целое число!");
                message = "\uD83D\uDD22 Повторите ввод: ";
            } catch (ArrayIndexOutOfBoundsException e) {
                view.printError("Такого действия нет!");
                message = "\uD83D\uDD22 Повторите ввод: ";
            }
        }
    }

    private Path getPathToInputFile(String message) {
        while (true) {
            String input = view.requestPath(message);
            Path pathToFile;
            if (input.isEmpty()) {
                pathToFile = pb.getDefaultPath("input.txt");
                view.printMessage("✅ Установлен путь по умолчанию: " + pathToFile);
            } else {
                pathToFile = pb.getPath(input);
            }
            if (Validator.isValidAndReadableFile(pathToFile)) {
                if (Validator.isTxtFile(pathToFile)) {
                    return pathToFile;
                } else {
                    view.printError("Это не .txt файл.");
                }
            } else {
                view.printError("Файл не существует или его нельзя прочитать!");
            }
        }
    }
}
