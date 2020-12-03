package com.enneagon.digital.exchange.procotol.nuonuo;

import com.enneagon.digital.exchange.procotol.nuonuo.utils.DESDZFP;
import com.github.littleantfly.httpdataexchangecore.encrypt.EncryptService;
import com.github.littleantfly.httpdataexchangecore.model.EncryptExchange;
import com.github.littleantfly.httpdataexchangecore.model.SignatureExchange;
import lombok.extern.slf4j.Slf4j;

/**
 * @author littl
 */
@Slf4j
public class NuoNuoHttpEncryptServiceImpl implements EncryptService {

    @Override
    public String encode(EncryptExchange exchange) {
        return DESDZFP.encrypt(exchange.getEncryptBody());
    }

    @Override
    public String decode(EncryptExchange exchange) {
        try {
            return DESDZFP.decrypt(exchange.getEncryptBody());
        } catch (Exception e) {
            log.error("nuonuo decode error, msg={}", e.getMessage());
        }
        return null;
    }

    @Override
    public String signature(SignatureExchange request) {
        return null;
    }

    @Override
    public boolean verifySignature(SignatureExchange response) {
        return false;
    }
}
