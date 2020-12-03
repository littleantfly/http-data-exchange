package com.github.littleantfly.tests.model;

import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import lombok.Data;

@Data
public class SimpleExchangeRequest implements ExchangeRequest<String> {

    private String name;



    @Override
    public Class<? extends ExchangeResponse<?>> getResponse() {
        return null;
    }

    @Override
    public String getRequestBody() {
        return name;
    }

    @Override
    public void setRequestBody(String body) {
        name = body;
    }
}
