package appView.SwingUI.panels;

import model.context.CipherContext;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.nio.file.Path;

public class InfoPanel extends JPanel {
    private final JPanel jPanel = new JPanel();
    private JLabel inputFilePath;
    private JLabel outputFilePath;
    private JLabel shiftKey;
    private JLabel state;
    private CipherContext context;

    public InfoPanel(CipherContext context) {
        this.context = context;
        init();
    }

    private void init() {
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Информация");
        jPanel.setLayout(new GridLayout(4, 1, 5, 5));
        jPanel.setBorder(titledBorder);

        inputFilePath = new JLabel("Исходный файл: [не выбран]");
        outputFilePath = new JLabel("Файл вывода или директория: [не задано]");
        shiftKey = new JLabel("Ключ: [не задан]");
        state = new JLabel(genStateString("red", "Не готов!"));

        jPanel.add(inputFilePath);
        jPanel.add(outputFilePath);
        jPanel.add(shiftKey);
        jPanel.add(state);

    }

    public JPanel getPanel() {
        return jPanel;
    }

    public void updateInfo() {
        String key = context.getShiftKey() == -1 ? "[не задан]" : Integer.toString(context.getShiftKey());
        String updateShiftKey = String.format("Ключ: %s", key);
        shiftKey.setText(updateShiftKey);

        Path inputPath = context.getInputPath();
        String inputPathInfo = inputPath == null ? "[не задан]" : inputPath.toString();
        inputFilePath.setText(String.format("Исходный файл: %s", inputPathInfo));

        Path outputPath = context.getOutputPath();
        String outputPathInfo = outputPath == null ? "[не задан]" : outputPath.toString();
        outputFilePath.setText(String.format("Файл вывода или директория: %s", outputPathInfo));

        updateStateInfo();
    }

    private void updateStateInfo() {
        if (context.isValid()) {
            state.setText(genStateString("green", "Готов!"));
            return;
        }
        state.setText(genStateString("red", "Не готов!"));
    }


    private String genStateString(String color, String text) {
       return String.format("<html><span style='color:black;'>Статус: </span><span style='color:%s;'>%s</span></html>", color, text);
    }
}
