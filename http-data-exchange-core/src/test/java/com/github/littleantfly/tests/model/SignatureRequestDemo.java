package com.github.littleantfly.tests.model;

import com.github.littleantfly.httpdataexchangecore.model.*;
import lombok.Data;

@Data
public class SignatureRequestDemo implements EncryptRequest, SignatureExchange {

    private String reqData;

    private String Signature;




    @Override
    public Class<? extends ExchangeResponse<?>> getResponse() {
        return SignatureResponseDemo.class;
    }

    @Override
    public String getRequestBody() {
        return this.reqData;
    }

    @Override
    public void setRequestBody(String body) {
        this.reqData = body;
    }


    @Override
    public void setExchangeSign(String sign) {
        this.Signature = sign;
    }

    @Override
    public String getExchangeSign() {
        return Signature;
    }
}
