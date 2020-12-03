package com.github.littleantfly.httpdataexchangecore.wrapper;

import com.alibaba.fastjson.JSON;
import com.github.littleantfly.httpdataexchangecore.ExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.encrypt.EncryptService;
import com.github.littleantfly.httpdataexchangecore.exception.DataExchangeRequestSignatureException;
import com.github.littleantfly.httpdataexchangecore.exception.DataExchangeVerifySignatureException;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import com.github.littleantfly.httpdataexchangecore.model.SignatureExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * 添加签名/验签处理
 * @author littl
 */
@Slf4j
public class SignatureExchangeHandlerWrapper implements ExchangeHandler {

    private final ExchangeHandler requestHandler;

    private final EncryptService encryptService;

    public SignatureExchangeHandlerWrapper(ExchangeHandler requestHandler, EncryptService encryptService) {
        this.requestHandler = requestHandler;
        this.encryptService = encryptService;
    }


    @Override
    public ExchangeResponse<?> post(String url, ExchangeRequest<?> request) throws DataExchangeRequestSignatureException, DataExchangeVerifySignatureException {
        if (log.isDebugEnabled()) {
            log.debug("signature request url={}, request={}", url, JSON.toJSONString(request));
        }
        //create and set signature
        SignatureExchange body = (SignatureExchange)request;
        String signature = encryptService.signature(body);
        if (StringUtils.isEmpty(signature)) {
            throw new DataExchangeRequestSignatureException();
        }
        body.setExchangeSign(signature);

        if (log.isDebugEnabled()) {
            log.debug("signature request url={}, signature request={}", url, JSON.toJSONString(request));
        }
        SignatureExchange res = (SignatureExchange)requestHandler.post(url, request);

        if (log.isDebugEnabled()) {
            log.debug("signature request url={}, signature response={}", url, JSON.toJSONString(res));
        }
        // verify signature
        boolean verifySign = encryptService.verifySignature(res);
        if (!verifySign) {
            throw new DataExchangeVerifySignatureException();
        }
        return (ExchangeResponse<?>)res;
    }
}
