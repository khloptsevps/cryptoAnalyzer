import appController.Controller;
import appController.SwingController;
import appView.ConsoleView;
import appView.SwingUI.SwingView;
import appView.View;
import model.CaesarCipherModel;

import javax.swing.*;

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
