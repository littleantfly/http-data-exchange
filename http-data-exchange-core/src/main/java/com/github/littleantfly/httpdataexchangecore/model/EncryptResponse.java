package com.github.littleantfly.httpdataexchangecore.model;

/**
 * 加密响应体
 * @author jim
 */
public interface EncryptResponse extends ExchangeResponse<String>, EncryptExchange {

    @Override
    default String getEncryptBody() {
        return getResponseBody();
    }

    @Override
    default void setEncryptBody(String body) {
        setResponseBody(body);
    }
}
