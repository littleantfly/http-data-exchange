package com.github.littleantfly.httpdataexchangedemo.service.impl;

import com.enneagon.digital.exchange.protocol.chinaunion.config.ChinaUnionConfig;
import org.springframework.stereotype.Service;

@Service
public class ChinaUnionConfigServiceImpl {

    public ChinaUnionConfig getConfig(String clientId) {
        //TODO load config from database
        return new ChinaUnionConfig(){
            @Override
            public String getHost() {
                return "http://localhost:8899/rest/china/union/";
            }
            @Override
            public String getSigSecret() {
                return "zdLcrqgevQp81oam";
            }
            @Override
            public String getDataSecret() {
                return "msJpdfcxy6pMfCWn";
            }
            @Override
            public String getDataSecretIv() {
                return "NXGNT1lSQrnWEjoZ";
            }
        };
    }
}
