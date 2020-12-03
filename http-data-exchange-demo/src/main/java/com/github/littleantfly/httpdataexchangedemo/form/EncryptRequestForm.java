package com.github.littleantfly.httpdataexchangedemo.form;

import com.github.littleantfly.httpdataexchangecore.model.EncryptRequest;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import com.github.littleantfly.httpdataexchangedemo.vo.EncryptResponseVO;
import lombok.Data;

@Data
public class EncryptRequestForm implements EncryptRequest {

    private String reqData;

    private String sign;



    @Override
    public Class<? extends ExchangeResponse<?>> getResponse() {
        return EncryptResponseVO.class;
    }

    @Override
    public String getRequestBody() {
        return this.reqData;
    }

    @Override
    public void setRequestBody(String body) {
        this.reqData = body;
    }
}
