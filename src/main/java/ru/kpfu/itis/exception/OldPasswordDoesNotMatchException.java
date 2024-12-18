package ru.kpfu.itis.exception;

public class OldPasswordDoesNotMatchException extends RuntimeException {
    public OldPasswordDoesNotMatchException() {
    }

    public OldPasswordDoesNotMatchException(String message) {
        super(message);
    }

    public OldPasswordDoesNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public OldPasswordDoesNotMatchException(Throwable cause) {
        super(cause);
    }

    public OldPasswordDoesNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
