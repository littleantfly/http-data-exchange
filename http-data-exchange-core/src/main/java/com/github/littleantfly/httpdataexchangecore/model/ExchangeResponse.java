package com.github.littleantfly.httpdataexchangecore.model;

/**
 * @author littl
 */
public interface ExchangeResponse<T> {

    void setResponseBody(T body);

    T getResponseBody();
}
