package ru.kpfu.itis.exception;

public class NotCorrectPasswordException extends RuntimeException {
    public NotCorrectPasswordException() {
    }

    public NotCorrectPasswordException(String message) {
        super(message);
    }

    public NotCorrectPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotCorrectPasswordException(Throwable cause) {
        super(cause);
    }

    public NotCorrectPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
