package com.github.littleantfly.httpdataexchangedemo.service.impl;

import com.github.littleantfly.httpdataexchangecore.encrypt.EncryptService;
import com.github.littleantfly.httpdataexchangecore.model.EncryptExchange;
import com.github.littleantfly.httpdataexchangecore.model.SignatureExchange;
import com.github.littleantfly.httpdataexchangecore.utils.Base64Util;

import java.nio.charset.StandardCharsets;

public class Base64EncryptServiceImpl implements EncryptService {

    @Override
    public String encode(EncryptExchange exchange) {
        return new String(Base64Util.encode(exchange.getEncryptBody().getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decode(EncryptExchange exchange) {
        String respStrDecode = null;
        try {
            respStrDecode = new String(Base64Util.decode(exchange.getEncryptBody().toCharArray()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respStrDecode;
    }

    @Override
    public String signature(SignatureExchange data) {
        return null;
    }

    @Override
    public boolean verifySignature(SignatureExchange response) {
        return false;
    }
}
