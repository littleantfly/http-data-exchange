package com.github.littleantfly.httpdataexchangecore;

import com.github.littleantfly.httpdataexchangecore.exception.DataExchangeException;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;

/**
 * @author littl
 */
public interface ExchangeHandler {

    /**
     * post请求
     * @param url
     * @param request
     * @return
     * @throws DataExchangeException
     */
    ExchangeResponse<?> post(String url, ExchangeRequest<?> request) throws DataExchangeException;

}
