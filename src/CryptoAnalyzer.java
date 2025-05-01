import appController.ConsoleController;
import appView.ConsoleView;
import appView.View;

public class CryptoAnalyzer {
    public static void main(String[] args) {
        // если параметров нет, то запускаем консольный режим
        // args из командной строки действие, путь до файла, путь выходного файла, ключ.
        // установка состояния приложения в контроллере.

        View ui = new ConsoleView();
        ConsoleController controller = new ConsoleController(ui);
        controller.run();
    }
}
