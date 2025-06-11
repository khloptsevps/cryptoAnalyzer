package org.khloptsev.cryptoanalyzer.appView;

import org.khloptsev.cryptoanalyzer.model.actions.Actions;

public interface View {
    void printMessage(String message);
    void printError(String message);
    String requestPath(String message);
    void showMenu(Actions[] actions);
    String requestUserInput(String message);
}
