package com.github.littleantfly.httpdataexchangecore.exception;

public class DataExchangeEncodeException extends DataExchangeRequestException {

    public DataExchangeEncodeException() {
        super();
    }

    public DataExchangeEncodeException(String msg) {
        super(msg);
    }

    public DataExchangeEncodeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DataExchangeEncodeException(Throwable cause) {
        super(cause);
    }
}
