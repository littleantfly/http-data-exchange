package com.github.littleantfly.httpdataexchangecore.exception;

public class DataExchangeRequestSignatureException extends DataExchangeRequestException {

    public DataExchangeRequestSignatureException() {
        super();
    }

    public DataExchangeRequestSignatureException(String msg) {
        super(msg);
    }

    public DataExchangeRequestSignatureException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DataExchangeRequestSignatureException(Throwable cause) {
        super(cause);
    }
}
