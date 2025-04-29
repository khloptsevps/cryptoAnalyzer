package util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PathBuilder {
    private final String DEFAULT_PATH = System.getProperty("user.dir");
    private final List<Path> pathList = new ArrayList<>();
    private final Set<Path> pathSet = new HashSet<>();
//    private final HashMap<String, Path> inOutPath = new HashMap<>();

//    public void buildInputAndOutputPath(String inputPath, String outputPath) {
//        Path input = buildPath(inputPath);
//        Path output = buildPath(outputPath);
//        inOutPath.put("input", input);
//        inOutPath.put("output", output);
//    }

    public void addPath(String path) {
        Path newPath = buildPath(path);
        if (!pathSet.contains(newPath)) {
            pathList.add(newPath);
            pathSet.add(newPath);
        }
    }

    public List<Path> getPathList() {
        return pathList;
    }

    private Path buildPath(String path) {
        final String DEFAULT_DIR = "test";
        Path currentPath = Paths.get(path);
        if (!currentPath.isAbsolute()) {
            if (currentPath.getParent() == null) {
                currentPath = Paths.get(DEFAULT_PATH, DEFAULT_DIR, path);
            } else {
                currentPath = Paths.get(DEFAULT_PATH, path);
            }
        }
        return currentPath;
    }



}
