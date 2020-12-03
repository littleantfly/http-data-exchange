package com.github.littleantfly.httpdataexchangecore.impl;

import com.github.littleantfly.httpdataexchangecore.ExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.TokenService;
import com.github.littleantfly.httpdataexchangecore.exception.DataExchangeRequestSignatureException;
import com.github.littleantfly.httpdataexchangecore.exception.DataExchangeVerifySignatureException;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

/**
 * 获取Token处理
 * @author jim
 */
public class HeaderTokenRequestHandlerWrapper implements ExchangeHandler {

    private final TokenService tokenService;

    private final ExchangeHandler requestHandler;


    public HeaderTokenRequestHandlerWrapper(ExchangeHandler requestHandler, TokenService tokenService) {
        this.requestHandler = requestHandler;
        this.tokenService = tokenService;
    }


    @Override
    public ExchangeResponse post(String url, ExchangeRequest request) throws DataExchangeRequestSignatureException, DataExchangeVerifySignatureException {
// TODO
//        tokenService.getTokenKey(), tokenService.getToken();
        return requestHandler.post(url, request);
    }
}
