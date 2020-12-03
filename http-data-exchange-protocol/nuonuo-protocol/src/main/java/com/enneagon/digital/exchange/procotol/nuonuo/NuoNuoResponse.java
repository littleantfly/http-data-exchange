package com.enneagon.digital.exchange.procotol.nuonuo;

import com.github.littleantfly.httpdataexchangecore.model.EncryptResponse;

/**
 * @author littl
 */
public class NuoNuoResponse implements EncryptResponse {

    private String status;

    private String message;

    private String body;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    @Override
    public void setResponseBody(String body) {
        this.body = body;
    }

    @Override
    public String getResponseBody() {
        return this.body;
    }
}
