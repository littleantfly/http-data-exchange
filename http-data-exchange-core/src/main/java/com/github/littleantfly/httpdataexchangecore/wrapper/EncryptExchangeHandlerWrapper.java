package com.github.littleantfly.httpdataexchangecore.wrapper;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.littleantfly.httpdataexchangecore.ExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.encrypt.EncryptService;
import com.github.littleantfly.httpdataexchangecore.exception.DataExchangeDecodeException;
import com.github.littleantfly.httpdataexchangecore.exception.DataExchangeEncodeException;
import com.github.littleantfly.httpdataexchangecore.model.EncryptRequest;
import com.github.littleantfly.httpdataexchangecore.model.EncryptResponse;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 加密解密的HTTP处理
 * @author littl
 */
@Slf4j
public class EncryptExchangeHandlerWrapper implements ExchangeHandler {


    private final ExchangeHandler requestHandler;

    /**
     * 加解密服务
     */
    private final EncryptService encryptService;


    public EncryptExchangeHandlerWrapper(ExchangeHandler requestHandler, EncryptService encryptService) {
        this.requestHandler = requestHandler;
        this.encryptService = encryptService;
    }

    @Override
    public ExchangeResponse<String> post(String url, ExchangeRequest<?> request) throws DataExchangeEncodeException, DataExchangeDecodeException {
        if (log.isDebugEnabled()) {
            log.debug("encrypt request url={}, request={}", url, JSON.toJSONString(request));
        }
        EncryptRequest encryptRequest = (EncryptRequest)request;
        //请求加密
        encryptRequest.setRequestBody(encryptService.encode(encryptRequest));

        if (log.isDebugEnabled()) {
            log.debug("encrypt request url={}, encode request={}", url, JSON.toJSONString(encryptRequest));
        }
        EncryptResponse resp = (EncryptResponse)requestHandler.post(url, encryptRequest);
        if (log.isDebugEnabled()) {
            log.debug("encrypt request url={}, response={}", url, resp);
        }
        //响应解密
        if(ObjectUtil.isNotNull(resp) && StrUtil.isNotBlank(resp.getResponseBody())) {
            String decode = encryptService.decode(resp);
            resp.setResponseBody(decode);
        }
        if (log.isDebugEnabled()) {
            log.debug("encrypt request url={}, decode response={}", url, JSON.toJSONString(resp));
        }
        return resp;
    }

}
