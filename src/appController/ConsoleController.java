package appController;

import model.actions.Actions;
import appView.View;
import model.CaesarCipherModel;
import model.dto.CipherRequest;
import util.PathBuilder;
import util.Validator;

import java.nio.file.Path;

public class ConsoleController {
    private final View view;
    private final CaesarCipherModel model;

    public ConsoleController(View view, CaesarCipherModel model) {
        this.view = view;
        this.model = model;
    }

    PathBuilder pb = new PathBuilder();


    public void run() {
        printIntro();
        CipherRequest request = new CipherRequest(
                requestAction(),
                requestPathToInputFile(),
                requestPathToOutput(),
                requestShiftKey()
        );
    }

    private String requestAction() {
        String message = "Выберите действие: ";
        String actionName;
        Actions[] actions = Actions.values();
        while (true) {
            try {
                String input = view.requestUserInput(message);
                actionName = actions[Integer.parseInt(input) - 1].name();
                if (actionName.equalsIgnoreCase("exit")) {
                    applicationExit(actionName);
                }
                return actionName;
            } catch (NumberFormatException e) {
                view.printError("Необходимо ввести целое число!");
                message = "\uD83D\uDD22 Повторите ввод: ";
            } catch (ArrayIndexOutOfBoundsException e) {
                view.printError("Такого действия нет!");
                message = "\uD83D\uDD22 Повторите ввод: ";
            }
        }
    }

    private Path requestPathToInputFile() {
        Path pathToFile;
        while (true) {
            String input = view.requestPath("Введите путь ко входящему файлу: ");
            pathToFile = input.isEmpty() ? pb.getDefaultPath("input.txt") : pb.getPath(input);

            if (Validator.isValidAndReadableFile(pathToFile)) {
                if (Validator.isTxtFile(pathToFile)) {
                    view.printMessage("✅ Установлен путь " + pathToFile);
                    return pathToFile;
                } else {
                    view.printError("Это не .txt файл.");
                }
            } else {
                view.printError("Файл не существует или его нельзя прочитать!\n");
            }
        }
    }

    private Path requestPathToOutput() {
        Path pathToFile;
        while (true) {
            String input = view.requestPath("Введите путь к исходящему файлу или директории: ");
            pathToFile = input.isEmpty() ? pb.getDefaultPath() : pb.getPath(input);
            view.printMessage("✅ Установлен путь: " + pathToFile);

            if (Validator.isTxtFile(pathToFile)) {
                return pathToFile;
            }

            if (Validator.isProbablyDirectory(pathToFile)) {
                return pathToFile;
            }

            view.printError("Исходящий файл должен быть .txt или директорией!");
        }
    }

    private int requestShiftKey() {
        int result;
        String message = "\uD83D\uDD11 Введите ключ сдвига: ";
        while (true) {
            try {
                String input = view.requestUserInput(message);
                result = Integer.parseInt(input);
                if (result <= model.getAlphabetSize() - 1 && result != 0) {
                    view.printMessage("✅ Ваш ключ: " + result);
                    return result;
                }
                view.printMessage("\uD83D\uDD11 Введите ключ в диапазоне от 1 до " + (model.getAlphabetSize() - 1));
            } catch (NumberFormatException e) {
                view.printError("Необходимо ввести целое число!\n");
                message = "Повторите ввод: ";
            }
        }
    }

    private void printIntro() {
        view.printMessage("***** Шифр Цезаря (Консольный режим) *****");
        Actions[] actions = Actions.values();
        view.showMenu(actions);
    }

    private void applicationExit(String actionName) {
        view.printMessage("\uD83D\uDC4B Завершение работы приложения по запросу пользователя...");
        Actions.getActionByName(actionName).execute(
                new CipherRequest(null, null, null, 0)
        );
    }
}
