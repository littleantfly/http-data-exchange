package com.github.littleantfly.httpdataexchangecore.model;

/**
 * @author littl
 */
public interface EncryptRequest extends ExchangeRequest<String>, EncryptExchange {

    @Override
    default String getEncryptBody() {
        return getRequestBody();
    }

    @Override
    default void setEncryptBody(String body) {
        setRequestBody(body);
    }
}
