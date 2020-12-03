package com.github.littleantfly.httpdataexchangecore.exception;

public class DataExchangeRequestException extends DataExchangeException {

    public DataExchangeRequestException() {
        super();
    }

    public DataExchangeRequestException(String msg) {
        super(msg);
    }

    public DataExchangeRequestException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DataExchangeRequestException(Throwable cause) {
        super(cause);
    }
}
