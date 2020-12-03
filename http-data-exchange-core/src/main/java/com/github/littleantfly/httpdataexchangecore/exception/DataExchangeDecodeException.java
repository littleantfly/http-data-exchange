package com.github.littleantfly.httpdataexchangecore.exception;

public class DataExchangeDecodeException extends DataExchangeResponseException {

    public DataExchangeDecodeException() {
        super();
    }

    public DataExchangeDecodeException(String msg) {
        super(msg);
    }

    public DataExchangeDecodeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DataExchangeDecodeException(Throwable cause) {
        super(cause);
    }
}
