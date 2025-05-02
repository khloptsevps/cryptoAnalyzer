import appController.ConsoleController;
import appView.ConsoleView;
import appView.View;

public class CryptoAnalyzer {
    public static void main(String[] args) {

        View ui = new ConsoleView();
        ConsoleController controller = new ConsoleController(ui);

        controller.run();
    }
}
