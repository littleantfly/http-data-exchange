package com.github.littleantfly.httpdataexchangecore.external;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.github.littleantfly.httpdataexchangecore.annotation.AutoEncrypt;
import com.github.littleantfly.httpdataexchangecore.model.EncryptRequest;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import com.github.littleantfly.httpdataexchangecore.model.SignatureExchange;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * 请求数据接收处理类
 *
 * @author Jim
 * @version 1.0
 * @date 2019/7/20 13:57
 */
@Slf4j
@ControllerAdvice
public class RequestBodyDecryptAdvice extends AbstractAutoEncrypt implements RequestBodyAdvice {

    public RequestBodyDecryptAdvice() {
        log.info("request decrypted init");
    }

    @Override
    public boolean supports(MethodParameter methodParameter, @NotNull Type targetType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        AutoEncrypt annotation = methodParameter.getContainingClass().getAnnotation(AutoEncrypt.class);
        return super.support(annotation);
    }

    @NotNull
    @Override
    public HttpInputMessage beforeBodyRead(@NotNull HttpInputMessage inputMessage, @NotNull MethodParameter parameter, @NotNull Type targetType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        //根据不同的请求获取不同的配置信息，并解密.  不同的请求获取不同的Crypto对象
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if (log.isDebugEnabled()) {
            log.debug("request decrypted start url={}", request.getServletPath());
        }
        HttpSecurityContext securityService = super.getSecurityService();
        return new HttpInputMessage() {
            @NotNull
            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }

            @NotNull
            @Override
            public InputStream getBody() throws IOException {
                String content = IoUtil.read(inputMessage.getBody(), "UTF-8");
                if (log.isDebugEnabled()) {
                    log.debug("request decrypted before data={}", content);
                }
                //解密
                ExchangeRequest<?> exchangeRequest = JSON.parseObject(content, securityService.getRequestClass());
                if(exchangeRequest instanceof EncryptRequest) {
                    EncryptRequest encryptRequest = (EncryptRequest)exchangeRequest;
                    encryptRequest.setEncryptBody(securityService.getHttpEncryptService().decode(encryptRequest));
                    if (log.isDebugEnabled()) {
                        log.debug("request decrypted after data={}", encryptRequest.getEncryptBody());
                    }
                    exchangeRequest = encryptRequest;
                }
                //验签
                if(exchangeRequest instanceof SignatureExchange) {
                    securityService.getHttpEncryptService().verifySignature((SignatureExchange)exchangeRequest);
                }
                return new ByteArrayInputStream(JSON.toJSONString(exchangeRequest).getBytes(StandardCharsets.UTF_8));
            }
        };
    }

    @NotNull
    @Override
    public Object afterBodyRead(@NotNull Object body, @NotNull HttpInputMessage inputMessage, @NotNull MethodParameter parameter, @NotNull Type targetType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, @NotNull HttpInputMessage inputMessage, @NotNull MethodParameter parameter, @NotNull Type targetType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }


}
