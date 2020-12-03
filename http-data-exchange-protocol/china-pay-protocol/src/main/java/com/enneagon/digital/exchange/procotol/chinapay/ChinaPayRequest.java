package com.enneagon.digital.exchange.procotol.chinapay;

import com.github.littleantfly.httpdataexchangecore.model.*;
import lombok.Data;

/**
 * @author littl
 */
@Data
public class ChinaPayRequest implements EncryptRequest, SignatureExchange {

    /**
     * 签名
     */
    private String signature;
    /**
     * 商户号
     */
    private String merNo;

    /**
     * 机构号
     */
    private String instId;

    /**
     * Base64后的请求报文体
     */
    private String reqData;


    @Override
    public Class<? extends ExchangeResponse<?>> getResponse() {
        return ChinaPayResponse.class;
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
        this.signature = sign;
    }

    @Override
    public String getExchangeSign() {
        return this.signature;
    }



}
