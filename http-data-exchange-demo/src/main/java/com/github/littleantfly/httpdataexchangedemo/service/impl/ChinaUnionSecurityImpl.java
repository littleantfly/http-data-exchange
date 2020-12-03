package com.github.littleantfly.httpdataexchangedemo.service.impl;

import com.enneagon.digital.exchange.protocol.chinaunion.ChinaUnionHttpEncryptServiceImpl;
import com.enneagon.digital.exchange.protocol.chinaunion.ChinaUnionRequest;
import com.github.littleantfly.httpdataexchangecore.encrypt.EncryptService;
import com.github.littleantfly.httpdataexchangecore.external.HttpSecurityContext;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author littl
 */
@Service
public class ChinaUnionSecurityImpl implements HttpSecurityContext {

    @Resource
    private ChinaUnionConfigServiceImpl chinaUnionConfigService;

    @Override
    public EncryptService getHttpEncryptService() {
        return new ChinaUnionHttpEncryptServiceImpl(chinaUnionConfigService.getConfig("1234"));
    }

    @Override
    public Class<? extends ExchangeRequest<?>> getRequestClass() {
        return ChinaUnionRequest.class;
    }
}
