package org.khloptsev.cryptoanalyzer.appView.SwingUI.panels;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class FileChooserPanel extends JPanel implements Panel {
    private JPanel jPanel = new JPanel();
    private final Map<String, JButton> IO_BUTTONS;

    public FileChooserPanel(Map<String, JButton> chooserButtons) {
        IO_BUTTONS = chooserButtons;
        init();
    }


    private void init() {
        jPanel.setBorder(BorderFactory.createTitledBorder("Выберите файлы"));
        jPanel.setLayout(new GridLayout(2, 1, 10, 20));

        IO_BUTTONS.forEach( (type, button) -> jPanel.add(button));
    }

    @Override
    public JPanel getPanel() {
        return jPanel;
    }
}
