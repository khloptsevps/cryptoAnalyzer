package org.khloptsev.cryptoanalyzer.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathBuilder {
    private final Path DEFAULT_PATH = Paths.get(System.getProperty("user.dir"));
    private final String DEFAULT_DIR;

    public PathBuilder() {
        DEFAULT_DIR = "example/";
    }

    public PathBuilder(String defaultDirName) {
        DEFAULT_DIR = String.format("%s\\", defaultDirName);
    }

    public Path getPath(String path) {
        return buildPath(path);
    }

    public Path getDefaultPath() {
        return DEFAULT_PATH.resolve(DEFAULT_DIR);
    }

    public Path getDefaultPath(String fileName) {
        return DEFAULT_PATH.resolve(DEFAULT_DIR + fileName);
    }

    private Path buildPath(String path) {
        Path currentPath = Paths.get(path);

        if (!currentPath.isAbsolute()) {
            if (currentPath.getParent() != null) {
                currentPath = DEFAULT_PATH.resolve(currentPath);
            } else {
                currentPath = DEFAULT_PATH.resolve(DEFAULT_DIR + currentPath);
            }
        }
        return currentPath.normalize();
    }
}
