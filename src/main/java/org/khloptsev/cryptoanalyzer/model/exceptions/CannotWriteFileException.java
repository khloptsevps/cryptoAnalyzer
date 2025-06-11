package org.khloptsev.cryptoanalyzer.model.exceptions;

public class CannotWriteFileException extends RuntimeException {
    public CannotWriteFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
