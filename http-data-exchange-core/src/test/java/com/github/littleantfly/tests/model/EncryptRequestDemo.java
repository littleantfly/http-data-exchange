package com.github.littleantfly.tests.model;

import com.github.littleantfly.httpdataexchangecore.model.EncryptRequest;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import lombok.Data;

@Data
public class EncryptRequestDemo implements EncryptRequest {

    private String reqData;

    @Override
    public Class<? extends ExchangeResponse<?>> getResponse() {
        return EncryptResponseDemo.class;
    }

    @Override
    public String getRequestBody() {
        return reqData;
    }

    @Override
    public void setRequestBody(String body) {
        reqData = body;
    }

}
