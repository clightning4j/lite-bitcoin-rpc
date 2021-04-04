package io.github.clightning4j.litebtc.exceptions;

public class UtilsExceptions extends Exception {
    public UtilsExceptions() {
    }

    public UtilsExceptions(String message) {
        super(message);
    }

    public UtilsExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilsExceptions(Throwable cause) {
        super(cause);
    }

    public UtilsExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
