package com.enneagon.digital.exchange.protocol.chinaunion;

import com.alibaba.fastjson.JSON;
import com.enneagon.digital.exchange.protocol.chinaunion.biz.QueryStationInfoVO;
import com.enneagon.digital.exchange.protocol.chinaunion.biz.QueryStationsInfoForm;
import com.enneagon.digital.exchange.protocol.chinaunion.config.ChinaUnionConfig;
import com.github.littleantfly.httpdataexchangecore.ExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.wrapper.EncryptExchangeHandlerWrapper;
import com.github.littleantfly.httpdataexchangecore.wrapper.PerpetualExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.wrapper.SignatureExchangeHandlerWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class ChinaUnionService {

    private final String host;

    private final ExchangeHandler chinaUnionExchangeHandler;

    public ChinaUnionService(ChinaUnionConfig chinaUnionConfig) {
        host = chinaUnionConfig.getHost();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        PerpetualExchangeHandler perpetualExchangeHandler = new PerpetualExchangeHandler(new RestTemplate(), httpHeaders);
        ChinaUnionHttpEncryptServiceImpl chinaUnionHttpEncryptService = new ChinaUnionHttpEncryptServiceImpl(chinaUnionConfig);
        SignatureExchangeHandlerWrapper signatureRequestHandlerWrapper = new SignatureExchangeHandlerWrapper(perpetualExchangeHandler, chinaUnionHttpEncryptService);
        chinaUnionExchangeHandler = new EncryptExchangeHandlerWrapper(signatureRequestHandlerWrapper, chinaUnionHttpEncryptService);
    }

    private ChinaUnionRequest getRequest(Object form) {
        ChinaUnionRequest request = new ChinaUnionRequest();
        request.setData(JSON.toJSONString(form));
        return request;
    }

    private <T> T getResponseData(ChinaUnionResponse response, Class<T> clazz) {
        return JSON.parseObject(response.getData(), clazz);
    }

    public QueryStationInfoVO queryStationInfo(QueryStationsInfoForm form){
        ChinaUnionRequest request = getRequest(form);
        ChinaUnionResponse response = (ChinaUnionResponse)chinaUnionExchangeHandler.post(host+"/query_stations_info", request);
        return getResponseData(response, QueryStationInfoVO.class);
    }

}
