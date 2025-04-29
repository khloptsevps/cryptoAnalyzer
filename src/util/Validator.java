package util;
import actions.Actions;

import java.nio.file.*;
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

    public static boolean isFileExistAndReadable(Path pathToFile) {
        return Files.isRegularFile(pathToFile) && Files.isReadable(pathToFile);
    }

    public static boolean isDirectory(Path path) {
        return Files.isDirectory(path);
    }

    public static boolean isTxtFile(Path path) {
        return path.getFileName().toString().toLowerCase().endsWith(".txt");


    }
}
