package com.github.littleantfly.httpdataexchangecore.exception;

public class DataExchangeException extends RuntimeException {

    public DataExchangeException() {
        super();
    }

    public DataExchangeException(String msg) {
        super(msg);
    }

    public DataExchangeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DataExchangeException(Throwable cause) {
        super(cause);
    }
}
