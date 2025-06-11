package org.khloptsev.cryptoanalyzer.appController;

import org.khloptsev.cryptoanalyzer.model.actions.BruteForce;
import org.khloptsev.cryptoanalyzer.model.context.CipherContext;
import org.khloptsev.cryptoanalyzer.model.actions.Action;
import org.khloptsev.cryptoanalyzer.model.actions.Actions;
import org.khloptsev.cryptoanalyzer.appView.View;
import org.khloptsev.cryptoanalyzer.model.CaesarCipherModel;
import org.khloptsev.cryptoanalyzer.model.exceptions.CannotCreateFileException;
import org.khloptsev.cryptoanalyzer.model.exceptions.CannotReadFileException;
import org.khloptsev.cryptoanalyzer.model.exceptions.CannotWriteFileException;
import org.khloptsev.cryptoanalyzer.util.PathBuilder;
import org.khloptsev.cryptoanalyzer.util.Validator;

import java.nio.file.Path;

public class Controller {
    private final View view;
    private final CaesarCipherModel cipherModel;

    public Controller(View view, CaesarCipherModel cipherModel) {
        this.view = view;
        this.cipherModel = cipherModel;
    }

    PathBuilder pb = new PathBuilder("files");
    CipherContext context = new CipherContext();


    public void run() {
        printIntro();

        Action action = requestAction();

        context.setCipherModel(cipherModel);
        context.setInputPath(requestPathToInputFile());
        context.setOutputPath(requestPathToOutput());

        if (action instanceof BruteForce) {
            context.setShiftKey(1);
        } else {
            context.setShiftKey(requestShiftKey());
        }


        try {
            action.execute(context);
            view.printMessage(action.successMessage());
        } catch (CannotCreateFileException | CannotReadFileException | CannotWriteFileException e) {
            view.printError(e.getMessage());
            System.exit(1);
        } catch (RuntimeException e) {
            view.printError(e.getMessage());
            System.exit(2);
        }
    }

    private Action requestAction() {
        String message = "Выберите действие: ";
        Actions action;
        Actions[] actions = Actions.values();
        while (true) {
            try {
                String input = view.requestUserInput(message);
                action = actions[Integer.parseInt(input) - 1];
                if (action.name().equalsIgnoreCase("exit")) {
                    applicationExit(action.name());
                }
                return Actions.getActionByName(action.name());
            } catch (NumberFormatException e) {
                view.printError("Необходимо ввести целое число!");
                message = "Повторите ввод: ";
            } catch (ArrayIndexOutOfBoundsException e) {
                view.printError("Такого действия нет!");
                message = "Повторите ввод: ";
            }
        }
    }

    private Path requestPathToInputFile() {
        Path pathToFile;
        while (true) {
            String input = view.requestPath("Введите путь ко входящему файлу: ");
            pathToFile = input.isEmpty() ? pb.getDefaultPath("example.txt") : pb.getPath(input);
            System.out.println(pathToFile);
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
                if (result <= cipherModel.getAlphabetSize() - 1 && result >= 0) {
                    view.printMessage("✅ Ваш ключ: " + result);
                    return result;
                }
                view.printMessage("\uD83D\uDD11 Введите ключ в диапазоне от 1 до " + (cipherModel.getAlphabetSize() - 1));
            } catch (NumberFormatException e) {
                view.printError("Необходимо ввести целое число!\n");
                message = "Повторите ввод: ";
            }
        }
    }

    private void printIntro() {
        view.printMessage("***** Шифратор Цезаря *****");
        Actions[] actions = Actions.values();
        view.showMenu(actions);
    }

    private void applicationExit(String actionName) {
        view.printMessage("\uD83D\uDC4B Завершение работы приложения по запросу пользователя...");
        Actions.getActionByName(actionName).execute(null);
    }
}
