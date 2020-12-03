package com.github.littleantfly.httpdataexchangecore.encrypt;

import com.github.littleantfly.httpdataexchangecore.model.EncryptExchange;
import com.github.littleantfly.httpdataexchangecore.model.SignatureExchange;
import com.github.littleantfly.httpdataexchangecore.utils.Base64Util;

import java.nio.charset.StandardCharsets;

public class DefaultHttpEncryptServiceImpl implements EncryptService {

    @Override
    public String encode(EncryptExchange exchange) {
        return new String(Base64Util.encode(exchange.getEncryptBody().getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decode(EncryptExchange exchange) {
        try {
            return new String(Base64Util.decode(exchange.getEncryptBody().toCharArray()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String signature(SignatureExchange data) {
        return "abc123";
    }

    @Override
    public boolean verifySignature(SignatureExchange response) {
        return "123abc".equals(response.getExchangeSign());
    }
}
