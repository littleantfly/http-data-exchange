package com.github.littleantfly.httpdataexchangecore.exception;

public class DataExchangeVerifySignatureException extends DataExchangeResponseException {

    public DataExchangeVerifySignatureException() {
        super();
    }

    public DataExchangeVerifySignatureException(String msg) {
        super(msg);
    }

    public DataExchangeVerifySignatureException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DataExchangeVerifySignatureException(Throwable cause) {
        super(cause);
    }
}
