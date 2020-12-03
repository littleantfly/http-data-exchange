package com.enneagon.digital.exchange.protocol.chinaunion;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.github.littleantfly.httpdataexchangecore.model.EncryptRequest;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import com.github.littleantfly.httpdataexchangecore.model.SignatureExchange;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class ChinaUnionRequest implements EncryptRequest, SignatureExchange {

    private String OperatorID;

    private String Data;

    private String TimeStamp;

    private String Seq;

    private String Sig;


    @Override
    public Class<? extends ExchangeResponse<?>> getResponse() {
        return ChinaUnionResponse.class;
    }

    @Override
    public String getRequestBody() {
        return this.Data;
    }

    @Override
    public void setRequestBody(String body) {
        this.Data = body;
    }

    @Override
    public void setExchangeSign(String sign) {
        this.Sig = sign;
    }

    @Override
    public String getExchangeSign() {
        return this.Sig;
    }
}
