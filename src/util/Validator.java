package util;
import actions.Actions;
import java.util.HashSet;
import java.util.Set;

public class Validator {
    private static final Set<String> ACTIONS = new HashSet<>();

    static {
        for (Actions action: Actions.values()) {
            ACTIONS.add(action.name().toLowerCase());
        }
    }

    public static boolean isValidAction(String actionName) {
        return ACTIONS.contains(actionName.toLowerCase());
    }

    public static boolean validateFile(String pathToFile) {
        // TODO логику валидации файла, существует ли файл по переданному пути
        return false;
    }
    public static boolean validatePath(String pathToFile) {
        // TODO логику валидации пути, проверить корректен ли путь.
        return false;
    }
}
