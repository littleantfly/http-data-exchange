package com.github.littleantfly.tests.model;

import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import lombok.Data;

@Data
public class EncryptResponseDemo implements ExchangeResponse<String> {

    private String respData;


    @Override
    public void setResponseBody(String body) {
        this.respData = body;
    }

    @Override
    public String getResponseBody() {
        return this.respData;
    }
}
