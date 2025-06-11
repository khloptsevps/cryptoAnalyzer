package org.khloptsev.cryptoanalyzer.util;

import java.nio.file.*;

public class Validator {

    public static boolean isValidAndReadableFile(Path path) {
        return (Files.exists(path) && Files.isRegularFile(path) && Files.isReadable(path));
    }

    public static boolean isTxtFile(Path path) {
        return path.getFileName().toString().toLowerCase().endsWith(".txt");
    }

    public static boolean isProbablyDirectory(Path path) {
        return !path.getFileName().toString().contains(".");
    }

    public static boolean isSamePath(Path path1, Path path2) {
        return path1.equals(path2);
    }

}
