package com.github.littleantfly.tests.model;

import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import com.github.littleantfly.httpdataexchangecore.model.SignatureExchange;
import lombok.Data;

@Data
public class SignatureResponseDemo implements ExchangeResponse<String>, SignatureExchange {

    private String respData;

    private String Signature;

    @Override
    public void setExchangeSign(String sign) {
        this.Signature = sign;
    }

    @Override
    public String getExchangeSign() {
        return this.Signature;
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
