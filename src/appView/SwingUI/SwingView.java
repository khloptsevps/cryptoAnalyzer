package appView.SwingUI;
import appView.SwingUI.panels.ActionsPanel;
import appView.SwingUI.panels.ExecutePanel;
import appView.SwingUI.panels.FileChooserPanel;
import appView.SwingUI.panels.InfoPanel;
import appView.SwingUI.panels.Panel;
import model.actions.Actions;
import model.context.CipherContext;
import util.Validator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

public class SwingView {

    private JFrame frame;

    private CipherContext context;

    private final List<JButton> ACTION_BUTTONS = new ArrayList<>();
    private final Map<String, JButton> IO_BUTTONS = new LinkedHashMap<>();
    private final List<JButton> ALL_BUTTONS = new ArrayList<>();

    private JButton executeButton;
    private JButton currentActiveButton;

    private final Color defaultButtonColor = UIManager.getColor("Button.background");
    private InfoPanel infoPanel = null;


    public void init() {
        frame = new JFrame("***** Шифратор Цезаря *****");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        executeButton = createExeCuteButtonButton();

        Panel ActionsPanel = new ActionsPanel(ACTION_BUTTONS);
        Panel fileChooserPanel = new FileChooserPanel(IO_BUTTONS);
        infoPanel = new InfoPanel(this.context);
        Panel executePanel = new ExecutePanel(executeButton);

        frame.add(ActionsPanel.getPanel(), BorderLayout.CENTER);
        frame.add(fileChooserPanel.getPanel(), BorderLayout.CENTER);
        frame.add(infoPanel.getPanel(), BorderLayout.CENTER);
        frame.add(executePanel.getPanel(), BorderLayout.CENTER);

        ALL_BUTTONS.addAll(ACTION_BUTTONS);
        ALL_BUTTONS.addAll(IO_BUTTONS.values());
    }


    public List<JButton> getActionButtons() {
        return ACTION_BUTTONS;
    }

    public Map<String, JButton> getIOButtons() {
        return IO_BUTTONS;
    }

    public void show() {
        frame.setVisible(true);
    }

    public void createActionButtons(Actions[] actions) {
        for (Actions action : actions) {
            JButton button = new JButton(action.getLabel());
            button.setName(action.name());
            ACTION_BUTTONS.add(button);
        }
    }

    public void createIOButtons() {
        IOButtons[] ioButtons = IOButtons.values();
        for (IOButtons ioButton : ioButtons) {
            JButton button = new JButton(ioButton.getLabel());
            button.setName(ioButton.name().toLowerCase());
            IO_BUTTONS.put(button.getName(), button);
        }
    }

    private JButton createExeCuteButtonButton() {
        JButton button = new JButton("Выполнить");
        button.setName("executeButton");
        button.setEnabled(false);
        return button;
    }


    public void setActiveButton(JButton button) {
        if (currentActiveButton != null) {
            resetButtonStyle(currentActiveButton);
        }
        button.setBackground(Color.GREEN);
        button.setOpaque(true);
        button.setBorderPainted(false);
        currentActiveButton = button;
    }

    private void resetButtonStyle(JButton button) {
        button.setBackground(defaultButtonColor);
        button.setOpaque(true);
        button.setBorderPainted(true);
    }

    public void resetActiveButton() {
        if (currentActiveButton != null) {
            resetButtonStyle(currentActiveButton);
            currentActiveButton = null;
        }
    }

    public String showInputDialog(String message, String title) {
        return JOptionPane.showInputDialog(frame, message, title, JOptionPane.QUESTION_MESSAGE);
    }

    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(frame, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(frame, message, "Успешное выполнение операции!", JOptionPane.INFORMATION_MESSAGE);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void updateInfo() {
        infoPanel.updateInfo();
    }

    public void setContext(CipherContext context) {
        this.context = context;
    }

    public JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setMultiSelectionEnabled(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовые файлы (*.txt)", "txt");
        fileChooser.setFileFilter(filter);
        return fileChooser;
    }

    public JButton getExecuteButton() {
        return executeButton;
    }

    public void updateExecuteState() {
        executeButton.setEnabled(Validator.validateContext(context));
    }

    public void blockAllButtons() {
        for (JButton button : ALL_BUTTONS) {
            button.setEnabled(false);
        }
        executeButton.setEnabled(false);

        resetActiveButton();
    }

    public void activateAllButtons() {
        for (JButton button : ALL_BUTTONS) {
            button.setEnabled(true);
        }
    }
}
