package model.exceptions;

public class CannotCreateFileException extends RuntimeException {
    public CannotCreateFileException(String message) {
        super(message);
    }

    public CannotCreateFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
