package com.enneagon.digital.exchange.procotol.chinapay;

import com.github.littleantfly.httpdataexchangecore.model.EncryptResponse;
import com.github.littleantfly.httpdataexchangecore.model.SignatureExchange;
import lombok.Data;

/**
 * @author littl
 */
@Data
public class ChinaPayResponse implements EncryptResponse, SignatureExchange {

    private String signature;

    private String merNo;

    private String instId;

    private String respData;


    @Override
    public void setExchangeSign(String sign) {
        this.signature = sign;
    }

    @Override
    public String getExchangeSign() {
        return this.signature;
    }


    @Override
    public void setResponseBody(String body) {
        this.respData = body;
    }

    @Override
    public String getResponseBody() {
        return this.respData;
    }
}
