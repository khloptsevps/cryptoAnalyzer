import appController.Controller;
import appView.ConsoleView;
import appView.View;
import model.CaesarCipherModel;

public class CryptoAnalyzer {
    public static void main(String[] args) {

        View ui = new ConsoleView();
        CaesarCipherModel model = new CaesarCipherModel();
        Controller controller = new Controller(ui, model);

        controller.run();

    }
}
