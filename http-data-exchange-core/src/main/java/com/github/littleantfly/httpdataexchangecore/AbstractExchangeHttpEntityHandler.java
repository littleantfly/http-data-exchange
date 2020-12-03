package com.github.littleantfly.httpdataexchangecore;

import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.Map;

/**
 * @author littl
 */
public abstract class AbstractExchangeHttpEntityHandler<T extends Map<String, ?>> implements ExchangeHttpEntityHandler {

    @Override
    public HttpEntity<T> getHttpEntity(ExchangeRequest request, HttpHeaders httpHeaders) {
        T params = parseToMap(request);
        removeUselessKeys(params);
        return new HttpEntity<>(params, httpHeaders);
    }

    /**
     * 将req对象转为Map
     * @param req
     * @return
     */
    protected abstract T parseToMap(ExchangeRequest req);

    protected void removeUselessKeys(Map<String, ?> paramMap) {
        paramMap.remove("response");
        paramMap.remove("encryptBody");
        paramMap.remove("exchangeSign");
        paramMap.remove("requestBody");
    }

}
