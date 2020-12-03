package com.enneagon.digital.exchange.procotol.nuonuo;

import com.github.littleantfly.httpdataexchangecore.model.EncryptRequest;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import lombok.Data;

/**
 * @author littl
 */
@Data
public class NuoNuoRequest implements EncryptRequest {

    private String order;

    @Override
    public Class<? extends ExchangeResponse<?>> getResponse() {
        return NuoNuoResponse.class;
    }

    @Override
    public String getRequestBody() {
        return this.order;
    }

    @Override
    public void setRequestBody(String body) {
        this.order = body;
    }


}
