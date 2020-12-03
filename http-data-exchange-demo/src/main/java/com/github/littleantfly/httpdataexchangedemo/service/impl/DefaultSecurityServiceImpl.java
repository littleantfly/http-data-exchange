package com.github.littleantfly.httpdataexchangedemo.service.impl;

import com.github.littleantfly.httpdataexchangecore.encrypt.EncryptService;
import com.github.littleantfly.httpdataexchangecore.external.HttpSecurityContext;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import com.github.littleantfly.httpdataexchangedemo.form.EncryptRequestForm;
import org.springframework.stereotype.Component;

/**
 * @author littl
 */
@Component
public class DefaultSecurityServiceImpl implements HttpSecurityContext {

    private EncryptService httpEncryptService = new Base64EncryptServiceImpl();


    @Override
    public EncryptService getHttpEncryptService() {
        return httpEncryptService;
    }

    @Override
    public Class<? extends ExchangeRequest<?>> getRequestClass() {
        return EncryptRequestForm.class;
    }
}
