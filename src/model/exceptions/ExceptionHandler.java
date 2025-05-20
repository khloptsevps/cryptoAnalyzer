package model.exceptions;

import java.io.IOException;
import java.nio.file.Path;

public class ExceptionHandler {
    public static void generateException(IOException e, Path input, Path output) {
        String message = e.getMessage().toLowerCase();
        String inputName = input.getFileName().toString().toLowerCase();
        String outputName = output.getFileName().toString().toLowerCase();

        if (message.contains(inputName)) {
            throw new CannotReadFileException("Ошибка чтения входного файла: " + input, e);
        } else if (message.contains(outputName)) {
            throw new CannotWriteFileException("Ошибка записи в файл: " + output, e);
        } else {
            throw new RuntimeException("Непредвиденная ошибка при обработке файлов", e);
        }
    }

    public static void generateCreationException(IOException e, Path output) {
        throw new CannotCreateFileException("Не удалось создать файл или директорию: " + output, e);
    }
}
