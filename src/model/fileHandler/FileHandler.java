package model.fileHandler;

import util.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler {

    public static Path create(Path path, String directory, String name) throws IOException {
        if (Validator.isProbablyDirectory(path)) {
            path = path.resolve(directory, name);
            FileHandler.create(path);
            return path;
        } else {
            String fileName = path.getFileName().toString();
            path = path.getParent().resolve(directory, fileName);
            FileHandler.create(path);
            return path;
        }
    }

    private static void create(Path path) throws IOException {
        Files.createDirectories(path.getParent());
        if (Files.exists(path)) {
            Files.delete(path);
            Files.createFile(path);
        } else {
            Files.createFile(path);
        }

    }
}
