package org.khloptsev.cryptoanalyzer;

import org.khloptsev.cryptoanalyzer.appController.Controller;
import org.khloptsev.cryptoanalyzer.appController.SwingController;
import org.khloptsev.cryptoanalyzer.appView.ConsoleView;
import org.khloptsev.cryptoanalyzer.appView.SwingUI.SwingView;
import org.khloptsev.cryptoanalyzer.appView.View;
import org.khloptsev.cryptoanalyzer.model.CaesarCipherModel;

import javax.swing.SwingUtilities;

public class CryptoAnalyzer {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-gui")) {
            SwingUtilities.invokeLater(() -> {
                CaesarCipherModel model = new CaesarCipherModel();
                SwingView ui = new SwingView();
                SwingController controller = new SwingController(ui, model);
                controller.initApp();
                controller.run();
            });
        } else {
            View ui = new ConsoleView();
            CaesarCipherModel model = new CaesarCipherModel();
            Controller controller = new Controller(ui, model);
            controller.run();
        }
    }
}
