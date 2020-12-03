package com.github.littleantfly.httpdataexchangecore.model;

/**
 * @author littl
 */
public interface ExchangeRequest<T> {
    /**
     * 请求的响应类型，用于方法返回时反序列化
     * @return
     */
    Class<? extends ExchangeResponse<?>> getResponse();

    T getRequestBody();

    void setRequestBody(T body);
}
