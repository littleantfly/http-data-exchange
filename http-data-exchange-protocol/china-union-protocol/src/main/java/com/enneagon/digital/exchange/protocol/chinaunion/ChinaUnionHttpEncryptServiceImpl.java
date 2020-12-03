package com.enneagon.digital.exchange.protocol.chinaunion;

import com.enneagon.digital.exchange.protocol.chinaunion.config.ChinaUnionConfig;
import com.enneagon.digital.exchange.protocol.chinaunion.utils.AESUtil;
import com.enneagon.digital.exchange.protocol.chinaunion.utils.HMacMD5Util;
import com.github.littleantfly.httpdataexchangecore.encrypt.EncryptService;
import com.github.littleantfly.httpdataexchangecore.model.EncryptExchange;
import com.github.littleantfly.httpdataexchangecore.model.SignatureExchange;

import java.nio.charset.StandardCharsets;

/**
 * @author littl
 */
public class ChinaUnionHttpEncryptServiceImpl implements EncryptService {

    private final ChinaUnionConfig chinaUnionConfig;

    public ChinaUnionHttpEncryptServiceImpl(ChinaUnionConfig chinaUnionConfig) {
        this.chinaUnionConfig = chinaUnionConfig;
    }

    @Override
    public String encode(EncryptExchange exchange) {
        String data = exchange.getEncryptBody();
        return AESUtil.encrypt(data, chinaUnionConfig.getDataSecret(), chinaUnionConfig.getDataSecretIv());
    }

    @Override
    public String decode(EncryptExchange exchange) {
        return AESUtil.decrypt(exchange.getEncryptBody(), chinaUnionConfig.getDataSecret(), chinaUnionConfig.getDataSecretIv());
    }

    @Override
    public String signature(SignatureExchange exchange) {

        if(exchange instanceof ChinaUnionResponse) {
            ChinaUnionResponse response = (ChinaUnionResponse) exchange;


            return HMacMD5Util.byte2HexStr(HMacMD5Util.getHmacMd5Bytes(chinaUnionConfig.getSigSecret().getBytes(StandardCharsets.UTF_8),
                    (response.getRet() + response.getMsg() + response.getData()).getBytes(StandardCharsets.UTF_8)));
        } else {
            ChinaUnionRequest request = (ChinaUnionRequest)exchange;

            return HMacMD5Util.byte2HexStr(HMacMD5Util.getHmacMd5Bytes(chinaUnionConfig.getSigSecret().getBytes(StandardCharsets.UTF_8),
                    (request.getOperatorID()+ request.getData()+request.getTimeStamp()+request.getSeq()).getBytes(StandardCharsets.UTF_8)));
        }
    }

    @Override
    public boolean verifySignature(SignatureExchange exchange) {
        return true;
    }
}
