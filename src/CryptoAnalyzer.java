import appController.ConsoleController;
import appView.ConsoleView;
import appView.View;
import model.CaesarCipherModel;

public class CryptoAnalyzer {
    public static void main(String[] args) {

        View ui = new ConsoleView();
        CaesarCipherModel model = new CaesarCipherModel();
        ConsoleController controller = new ConsoleController(ui, model);

        controller.run();

    }
}
