package model.exceptions;

public class CannotReadFileException extends RuntimeException {
    public CannotReadFileException(String message, Throwable cause) {
        super(message, cause);
    }
}