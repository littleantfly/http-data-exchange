package com.github.littleantfly.httpdataexchangecore.exception;

public class DataExchangeResponseException extends DataExchangeException {

    public DataExchangeResponseException() {
        super();
    }

    public DataExchangeResponseException(String msg) {
        super(msg);
    }

    public DataExchangeResponseException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DataExchangeResponseException(Throwable cause) {
        super(cause);
    }
}
