package org.khloptsev.cryptoanalyzer.appView.SwingUI.panels;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class ActionsPanel extends JPanel implements Panel {
    private final JPanel jPanel = new JPanel();

    public ActionsPanel(List<JButton> actionButtons) {
        init(actionButtons);
    }

    private void init(List<JButton> actionButtons) {
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Выберите действие");
        jPanel.setBorder(titledBorder);
        jPanel.setLayout(new GridLayout(2, 2, 10, 10));
        for (JButton actionButton : actionButtons) {
            jPanel.add(actionButton);
        }
    }
    @Override
    public JPanel getPanel() {
        return jPanel;
    }
}
