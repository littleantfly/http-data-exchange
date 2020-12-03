package com.enneagon.digital.exchange.procotol.chinapay;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.chinapay.secss.SecssUtil;
import com.enneagon.digital.exchange.procotol.chinapay.biz.ChinaPayInvoiceApplyForm;
import com.enneagon.digital.exchange.procotol.chinapay.biz.ChinaPayInvoiceApplyVO;
import com.enneagon.digital.exchange.procotol.chinapay.biz.ChinaPayInvoiceQueryForm;
import com.enneagon.digital.exchange.procotol.chinapay.biz.ChinaPayInvoiceQueryVO;
import com.enneagon.digital.exchange.procotol.chinapay.config.ChinaPayConfig;
import com.github.littleantfly.httpdataexchangecore.ExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.encrypt.EncryptService;
import com.github.littleantfly.httpdataexchangecore.impl.EncryptExchangeHandlerWrapper;
import com.github.littleantfly.httpdataexchangecore.impl.PerpetualExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.impl.SignatureExchangeHandlerWrapper;
import com.github.littleantfly.httpdataexchangecore.model.EncryptResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author littl
 */
@Slf4j
public class ChinaPayService {

    private final ChinaPayConfig chinaPayConfig;
    private ExchangeHandler exchangeHandler;

    private static final RestTemplate REST_CLIENT;


    static {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // 设置超时
        requestFactory.setConnectTimeout(180000);
        requestFactory.setReadTimeout(180000);
        //利用复杂构造器可以实现超时设置，内部实际实现为 HttpClient
        REST_CLIENT = new RestTemplate(requestFactory);
    }

    public ChinaPayService(ChinaPayConfig chinaPayConfig) {
        this.chinaPayConfig = chinaPayConfig;
        initExchangeHandler(chinaPayConfig.getConfigPath());
    }

    private void initExchangeHandler(String configPath){
        SecssUtil secssUtil = new SecssUtil();
        boolean init = secssUtil.init(configPath);
        if(!init) {
            log.error("银联加密初始化失败");
        }
        EncryptService encryptService = new ChinaPayHttpEncryptServiceImpl(secssUtil);
        PerpetualExchangeHandler perpetualRequestHandler = new PerpetualExchangeHandler(REST_CLIENT);
        perpetualRequestHandler.setDataHandler(this::parseToMap);
        SignatureExchangeHandlerWrapper signatureRequestHandlerWrapper = new SignatureExchangeHandlerWrapper(perpetualRequestHandler, encryptService);

        exchangeHandler = new EncryptExchangeHandlerWrapper(signatureRequestHandlerWrapper, encryptService);
    }

    private Map<String, String> parseToMap(Object data){
        if(data == null){
            return MapUtil.newHashMap(0);
        }
        String respStr = data.toString();
        String[] ss = respStr.split("&");
        Map<String, String> map = new HashMap<>(ss.length);
        for (String str : ss) {
            if (StrUtil.isBlank(str)) {
                continue;
            }
            int index = str.indexOf("=");
            map.put(str.substring(0, index),str.substring(index+1));
        }
        return map;
    }

    public ChinaPayInvoiceApplyVO applyInvoice(ChinaPayInvoiceApplyForm form) {
        ChinaPayRequest req = new ChinaPayRequest();
        req.setMerNo(chinaPayConfig.getMerNo());
        req.setReqData(JSON.toJSONString(form));
        EncryptResponse resp = (EncryptResponse)exchangeHandler.post(chinaPayConfig.getBizUrl(), req);
        return JSON.parseObject(resp.getResponseBody(), ChinaPayInvoiceApplyVO.class);
    }

    public ChinaPayInvoiceQueryVO queryInvoice(ChinaPayInvoiceQueryForm form) {
        ChinaPayRequest req = new ChinaPayRequest();
        req.setMerNo(chinaPayConfig.getMerNo());
        req.setReqData(JSON.toJSONString(form));
        EncryptResponse resp = (EncryptResponse)exchangeHandler.post(chinaPayConfig.getQueryUrl(), req);
        return JSON.parseObject(resp.getResponseBody(), ChinaPayInvoiceQueryVO.class);
    }



}
