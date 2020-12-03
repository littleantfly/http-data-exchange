package com.enneagon.digital.exchange.protocol.chinaunion;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.github.littleantfly.httpdataexchangecore.model.EncryptResponse;
import com.github.littleantfly.httpdataexchangecore.model.SignatureExchange;
import lombok.Data;

/**
 * @author littl
 */
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class ChinaUnionResponse implements EncryptResponse, SignatureExchange {

    /**
     * Ret
     */
    private int Ret;

    /**
     * Msg
     */
    private String Msg;

    /**
     * Data
     */
    private String Data;

    /**
     * Sig
     */
    private String Sig;

    @Override
    public void setResponseBody(String body) {
        this.Data = body;
    }

    @Override
    public String getResponseBody() {
        return this.Data;
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
