package appController;

import appView.SwingUI.SwingView;
import model.CaesarCipherModel;
import model.actions.*;
import model.actions.Action;
import model.context.CipherContext;
import model.exceptions.CannotCreateFileException;
import model.exceptions.CannotReadFileException;
import model.exceptions.CannotWriteFileException;
import util.PathBuilder;
import util.Validator;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;


public class SwingController {
    private final SwingView view;
    private Action action;
    private CipherContext context;
    private CaesarCipherModel model;

    PathBuilder pb = new PathBuilder();

    public SwingController(SwingView view, CaesarCipherModel model) {
        this.view = view;
        this.context = new CipherContext();
        this.model = model;
        view.setContext(context);
    }

    public void initApp() {
        context.setCipherModel(model);

        view.createActionButtons(Actions.values());
        view.createIOButtons();

        view.init();
        initActionButtonsListeners();
        initIOButtonsHandlers();
        initExecuteButton();
    }

    public void run() {
        view.show();
    }


    private void initActionButtonsListeners() {
        List<JButton> buttons = view.getActionButtons();
        for (JButton button : buttons) {
            button.addActionListener((_) -> actionHandler(button));
        }
    }

    private void initIOButtonsHandlers() {
        Map<String, JButton> ioButtons = view.getIOButtons();

        JButton input = ioButtons.get("input");
        JButton output = ioButtons.get("output");

        input.addActionListener((_) -> inputButtonHandler());

        output.addActionListener((_) -> outputButtonHandler());
    }

    private void initExecuteButton() {
        JButton executeButton = view.getExecuteButton();
        executeButton.addActionListener((_) -> executeButtonHandler());
    }

    private int requestShiftKey() {
        while (true) {
            String input = view.showInputDialog("\uD83D\uDD11 Введите ключ сдвига: ", "\uD83D\uDD11 Ключ сдвига");
            if (input == null) {
                return -1;
            }
            try {
                int result = Integer.parseInt(input);
                if (result <= model.getAlphabetSize() - 1 && result >= 0) {
                    return result;
                }
                String msg = "\uD83D\uDD11 Введите ключ в диапазоне от 1 до " + (model.getAlphabetSize() - 1);
                view.showErrorDialog(msg);
            } catch (NumberFormatException e) {
                view.showErrorDialog("Необходимо ввести целое число!");
            }
        }
    }

    private void actionHandler(JButton button) {

        view.setActiveButton(button);

        action = Actions.getActionByName(button.getName());

        if (action instanceof Encrypt || action instanceof Decrypt) {
            int key = requestShiftKey();
            if (key == -1) {
                action = null;
                view.resetActiveButton();
                context.setShiftKey(key);
                view.updateInfo();
                view.updateExecuteState();
                return;
            }
            context.setShiftKey(key);
            view.updateInfo();
            view.updateExecuteState();
        }

        if (action instanceof BruteForce) {
            context.setShiftKey(1);
            view.updateInfo();
            view.updateExecuteState();
        }


        if (action instanceof Exit) {
            action.execute(null);
        }
    }

    private void inputButtonHandler() {
        JFileChooser fileChooser = view.createFileChooser();

        while (true) {
            int result = fileChooser.showOpenDialog(view.getFrame());

            Path pathToFile;
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                pathToFile = pb.getPath(path);

                if (!Validator.isTxtFile(pathToFile)) {
                    view.showErrorDialog("Это не .txt файл.");
                    continue;
                }

                if (!Validator.isValidAndReadableFile(pathToFile)) {
                    view.showErrorDialog("Файл не существует или его нельзя прочитать!");
                    continue;
                }

                if (Validator.isSamePath(pathToFile, context.getOutputPath())) {
                    view.showErrorDialog("Путь ко входящему и исходящему файлу должен быть разный!");
                    continue;
                }

                context.setInputPath(pathToFile);
                view.updateInfo();
                view.updateExecuteState();
            }
            return;
        }
    }

    private void outputButtonHandler() {
        JFileChooser fileChooser = view.createFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        while (true) {
            int result = fileChooser.showOpenDialog(view.getFrame());
            Path pathToFile;
            if (result == JFileChooser.APPROVE_OPTION) {
                File selected = fileChooser.getSelectedFile();
                pathToFile = pb.getPath(selected.getAbsolutePath());

                if (Validator.isProbablyDirectory(pathToFile)) {
                    context.setOutputPath(pathToFile);
                    view.updateInfo();
                    view.updateExecuteState();
                    return;
                }

                if (!Validator.isTxtFile(pathToFile)) {
                    view.showErrorDialog("Исходящий файл должен быть .txt или директорией!");
                    continue;
                }

                if (Validator.isSamePath(pathToFile, context.getInputPath())) {
                    view.showErrorDialog("Путь ко входящему и исходящему файлу должен быть разный!");
                    continue;
                }

                context.setOutputPath(pathToFile);
                view.updateInfo();
                view.updateExecuteState();
            }
            return;
        }
    }

    private void executeButtonHandler() {
        try {
            action.execute(context);
            view.blockAllButtons();
            view.showMessageDialog(action.successMessage());
            context.reset();
            view.updateInfo();
            view.updateExecuteState();
            view.activateAllButtons();
        } catch (CannotCreateFileException | CannotReadFileException | CannotWriteFileException e) {
            view.showErrorDialog(e.getMessage());
            context.reset();
        } catch (RuntimeException e) {
            view.showErrorDialog(e.getMessage());
            System.exit(1);
        }
    }
}
