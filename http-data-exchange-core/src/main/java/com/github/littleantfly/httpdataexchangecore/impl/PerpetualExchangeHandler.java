package com.github.littleantfly.httpdataexchangecore.impl;

import com.alibaba.fastjson.JSON;
import com.github.littleantfly.httpdataexchangecore.DataHandler;
import com.github.littleantfly.httpdataexchangecore.ExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.ExchangeHttpEntityFactory;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 原始的HTTP请求处理
 *
 * @author littl
 */
@Slf4j
public class PerpetualExchangeHandler implements ExchangeHandler {

    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private DataHandler dataHandler;

    private static HttpHeaders headers = new HttpHeaders();

    /**
     * 默认传参方式为Form表单
     */
    static {
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    }

    public PerpetualExchangeHandler(RestTemplate restTemplate) {
        this(restTemplate, headers, null);
    }

    public PerpetualExchangeHandler(RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this(restTemplate, httpHeaders, null);
    }

    public PerpetualExchangeHandler(RestTemplate restTemplate, HttpHeaders httpHeaders, DataHandler dataHandler) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
        this.dataHandler = dataHandler;
    }

    @Override
    public ExchangeResponse<?> post(String url, ExchangeRequest<?> request) {
        HttpEntity<?> httpEntity = ExchangeHttpEntityFactory.getHttpEntity(request, httpHeaders);
        if (log.isDebugEnabled()) {
            log.debug("perpetual request url={}, httpEntity={}", url, JSON.toJSONString(httpEntity));
        }
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        if (log.isDebugEnabled()) {
            log.debug("perpetual request url={}, response={}", url, JSON.toJSONString(responseEntity));
        }
        String body = handlerData(responseEntity);
        //反序列化为响应对象
        return JSON.parseObject(body, request.getResponse());
    }

    private String handlerData(ResponseEntity<String> responseEntity) {
        if (dataHandler != null) {
            Object data = dataHandler.handler(responseEntity.getBody());
            if (data instanceof String) {
                return data.toString();
            }
            return JSON.toJSONString(data);
        }
        return responseEntity.getBody();
    }

    public void setDataHandler(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }
}
