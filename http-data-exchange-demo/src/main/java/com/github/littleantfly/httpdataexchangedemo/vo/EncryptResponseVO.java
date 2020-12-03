package com.github.littleantfly.httpdataexchangedemo.vo;

import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import lombok.Data;

@Data
public class EncryptResponseVO implements ExchangeResponse<String> {

    private String respData;


    @Override
    public void setResponseBody(String body) {
        this.respData = body;
    }

    @Override
    public String getResponseBody() {
        return respData;
    }
}
