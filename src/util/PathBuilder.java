package util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathBuilder {
    private final String DEFAULT_PATH = System.getProperty("user.dir");
    private final String DEFAULT_DIR = "test";

    public Path getPath(String path) {
        return buildPath(path);
    }

    public Path getDefaultPath() {
        return Paths.get(DEFAULT_PATH, DEFAULT_DIR);
    }

    public Path getDefaultPath(String fileName) {
        return Paths.get(DEFAULT_PATH, DEFAULT_DIR, fileName);
    }

    private Path buildPath(String path) {
        Path currentPath = Paths.get(path);
        if (!currentPath.isAbsolute()) {
            currentPath = Paths.get(DEFAULT_PATH, DEFAULT_DIR, path);
        }
        return currentPath.normalize();
    }



}
