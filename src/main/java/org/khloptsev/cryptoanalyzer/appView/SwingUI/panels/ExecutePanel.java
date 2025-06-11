package org.khloptsev.cryptoanalyzer.appView.SwingUI.panels;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ExecutePanel implements Panel {
    private final JPanel J_PANEL = new JPanel();

    public ExecutePanel(JButton executeButton) {
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Выполнить");
        J_PANEL.setLayout(new GridLayout(1, 1, 20, 20));
        J_PANEL.setBorder(titledBorder);
        J_PANEL.add(executeButton);
    }

    @Override
    public JPanel getPanel() {
        return J_PANEL;
    }
}
