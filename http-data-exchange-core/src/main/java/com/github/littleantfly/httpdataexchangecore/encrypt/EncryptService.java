package com.github.littleantfly.httpdataexchangecore.encrypt;

import com.github.littleantfly.httpdataexchangecore.model.EncryptExchange;
import com.github.littleantfly.httpdataexchangecore.model.SignatureExchange;

/**
 * 加密服务
 * @author jim
 */
public interface EncryptService {

    String encode(EncryptExchange exchange);

    String decode(EncryptExchange exchange);

    String signature(SignatureExchange exchange);

    boolean verifySignature(SignatureExchange exchange);
}
