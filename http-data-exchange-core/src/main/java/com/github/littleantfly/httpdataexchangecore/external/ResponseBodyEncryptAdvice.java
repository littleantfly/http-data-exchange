package com.github.littleantfly.httpdataexchangecore.external;

import cn.hutool.core.util.StrUtil;
import com.github.littleantfly.httpdataexchangecore.annotation.AutoEncrypt;
import com.github.littleantfly.httpdataexchangecore.model.EncryptResponse;
import com.github.littleantfly.httpdataexchangecore.model.SignatureExchange;
import com.github.littleantfly.httpdataexchangecore.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * ResponseBodyEncryptAdvice
 *
 * @author Jim
 * @version 1.0
 * @date 2019/7/20 15:41
 */
@Slf4j
@ControllerAdvice
public class ResponseBodyEncryptAdvice extends AbstractAutoEncrypt implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        AutoEncrypt annotation = returnType.getContainingClass().getAnnotation(AutoEncrypt.class);
        return super.support(annotation);
    }

    @Override
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter returnType, @NotNull MediaType selectedContentType, @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("response encrypted url={}", request.getURI().getPath());
        }
        //加密
        if(body instanceof EncryptResponse) {
            EncryptResponse encryptResponse = (EncryptResponse) body;
            if (log.isDebugEnabled()) {
                log.debug("response encrypted url={}, before data={}", request.getURI().getPath(), encryptResponse.getResponseBody());
            }
            encryptResponse.setResponseBody(super.getSecurityService().getHttpEncryptService().encode(encryptResponse));
            if (log.isDebugEnabled()) {
                log.debug("response encrypted url={}, after data={}", request.getURI().getPath(), encryptResponse.getResponseBody());
            }
        }
        //加签
        if(body instanceof SignatureExchange) {
            SignatureExchange signatureResponse = (SignatureExchange) body;
            signatureResponse.setExchangeSign(super.getSecurityService().getHttpEncryptService().signature(signatureResponse));
            if (log.isDebugEnabled()) {
                log.debug("response encrypted url={}, signature data={}", request.getURI().getPath(), signatureResponse.getExchangeSign());
            }
        }
        return body;
    }

}
