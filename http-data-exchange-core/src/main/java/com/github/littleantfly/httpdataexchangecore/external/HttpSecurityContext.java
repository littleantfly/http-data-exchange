package com.github.littleantfly.httpdataexchangecore.external;

import com.github.littleantfly.httpdataexchangecore.encrypt.EncryptService;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;

/**
 * http请求安全内容
 * @author jim
 */
public interface HttpSecurityContext {

    EncryptService getHttpEncryptService();

    Class<? extends ExchangeRequest<?>> getRequestClass();
}
