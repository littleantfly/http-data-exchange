package com.enneagon.digital.exchange.procotol.chinapay;

import cn.hutool.core.map.MapUtil;
import com.chinapay.secss.SecssUtil;
import com.enneagon.digital.exchange.procotol.chinapay.utils.EncryptUtil;
import com.github.littleantfly.httpdataexchangecore.encrypt.EncryptService;
import com.github.littleantfly.httpdataexchangecore.model.EncryptExchange;
import com.github.littleantfly.httpdataexchangecore.model.SignatureExchange;
import com.github.littleantfly.httpdataexchangecore.utils.Base64Util;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author littl
 */
public class ChinaPayHttpEncryptServiceImpl implements EncryptService {

    private final SecssUtil secssUtil;

    public ChinaPayHttpEncryptServiceImpl(SecssUtil secssUtil) {
        this.secssUtil = secssUtil;
    }

    @Override
    public String encode(EncryptExchange exchange) {
        return new String(Base64Util.encode(exchange.getEncryptBody().getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decode(EncryptExchange exchange) {
        String respStrDecode = null;
        try {
            respStrDecode = new String(Base64Util.decode(exchange.getEncryptBody().toCharArray()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respStrDecode;
    }

    @Override
    public String signature(SignatureExchange request) {
        ChinaPayRequest chinaPayRequest = (ChinaPayRequest)request;
        String encodeBody = EncryptUtil.encode(chinaPayRequest.getReqData(), "SHA-512");
        Map<String, String> signMap = MapUtil.newHashMap();
        signMap.put("reqData", encodeBody);
        secssUtil.sign(signMap);
        return secssUtil.getSign();
    }

    @Override
    public boolean verifySignature(SignatureExchange response) {
        ChinaPayResponse chinaPayResponse = (ChinaPayResponse)response;
        Map<String, Object> verifyMap = MapUtil.newHashMap();
        verifyMap.put("respData", EncryptUtil.encode(chinaPayResponse.getRespData(), "SHA-512"));
        verifyMap.put("signature", chinaPayResponse.getSignature());
        secssUtil.verify(verifyMap);
        return ("00".equals(secssUtil.getErrCode()));
    }
}
