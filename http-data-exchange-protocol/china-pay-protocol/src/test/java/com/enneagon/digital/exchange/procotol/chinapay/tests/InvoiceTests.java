package com.enneagon.digital.exchange.procotol.chinapay.tests;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.chinapay.secss.SecssUtil;
import com.enneagon.digital.exchange.procotol.chinapay.ChinaPayHttpEncryptServiceImpl;
import com.enneagon.digital.exchange.procotol.chinapay.ChinaPayRequest;
import com.enneagon.digital.exchange.procotol.chinapay.ChinaPayService;
import com.enneagon.digital.exchange.procotol.chinapay.biz.*;
import com.enneagon.digital.exchange.procotol.chinapay.config.ChinaPayConfig;
import com.enneagon.digital.exchange.procotol.chinapay.utils.Base64Util;
import com.github.littleantfly.httpdataexchangecore.ExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.encrypt.EncryptService;
import com.github.littleantfly.httpdataexchangecore.impl.EncryptExchangeHandlerWrapper;
import com.github.littleantfly.httpdataexchangecore.impl.PerpetualExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.impl.SignatureExchangeHandlerWrapper;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Slf4j
public class InvoiceTests {




    private ChinaPayInvoiceApplyForm form = new ChinaPayInvoiceApplyForm();


    private String url = "https://localhost/business.htm";

    @Before
    public void init(){
        form.setMerNo("000091908029266");
        form.setOrderId("123999000114");
        form.setOrderDate("20200715");
        form.setMerOrderId(form.getOrderId());
        form.setMerOrderDate(form.getOrderDate());
        form.setBuyerName("Jim");
        form.setAmount("7540");
//        form.setBuyerTaxCode("123456asd");
//        form.setBuyerAddress("上海市嘉定区");
//        form.setBuyerTelephone("18521084140");
//        form.setBuyerBank("中国银行");
//        form.setBuyerAccount("1223434634");
//        form.setRemark("AAA");
//        form.setNotifyMobileNo("18521084140");
//        form.setNotifyEMail("172259412@qq.com");

//        1|0||杰卡斯西拉加本纳干红375ml|74.0|0|1|瓶|1030305000000000000|375ml|0|1|出口退税
        GoodDetail gd = GoodDetail.builder()
                .index(1).attribute("0").name("杰卡斯西拉加本纳干红375ml")
                .priceIncludingTax(74.0).taxRate(0)
                .quantity(1d).unit("瓶").sn("1030305000000000000")
                .build();

        //500长度
        form.setGoodsDetail(new String(Base64Util.encode(JSON.toJSONString(Lists.newArrayList(gd)).getBytes())));
    }


    @Test
    public void invoiceTest() {
        SecssUtil secssUtil = new SecssUtil();
        boolean init = secssUtil.init("/data/jars/files/yc-security.properties");
        if(!init) {
            System.out.println("银联加密初始化失败");
        }
        EncryptService encryptService = new ChinaPayHttpEncryptServiceImpl(secssUtil);

        PerpetualExchangeHandler perpetualRequestHandler = new PerpetualExchangeHandler(new RestTemplate());
        perpetualRequestHandler.setDataHandler(data -> {
            String respStr = data.toString();
            Map<String, String> map = new HashMap<>();
            if (StrUtil.isNotBlank(respStr)) {
                String[] strs = respStr.split("&");
                for (String str : strs) {
                    if (StrUtil.isBlank(str)) {
                        continue;
                    }
                    int index = str.indexOf("=");
                    map.put(str.substring(0, index),str.substring(index+1));
                }
            }
            return map;
        });

        SignatureExchangeHandlerWrapper signatureRequestHandlerWrapper = new SignatureExchangeHandlerWrapper(perpetualRequestHandler, encryptService);
        ExchangeHandler exchangeHandler = new EncryptExchangeHandlerWrapper(signatureRequestHandlerWrapper, encryptService);
        ChinaPayRequest req = new ChinaPayRequest();
        req.setMerNo("000091908029266");
        req.setReqData(JSON.toJSONString(form));


        ExchangeResponse resp = exchangeHandler.post(url, req);
        log.info(JSON.toJSONString(resp));

    }

    @Test
    public void chinaPayServiceTest(){
        ChinaPayConfig chinaPayConfig = new ChinaPayConfig();
        chinaPayConfig.setBizUrl("https://localhost/business.htm");
        chinaPayConfig.setQueryUrl("https://localhost/query.htm");
        chinaPayConfig.setConfigPath("/data/jars/files/yc-security.properties");
        chinaPayConfig.setMerNo("000091908029266");
        ChinaPayService chinaPayService = new ChinaPayService(chinaPayConfig);
        ChinaPayInvoiceApplyVO invoiceVO = chinaPayService.applyInvoice(form);
        log.info(JSON.toJSONString(invoiceVO));


//        ChinaPayInvoiceQueryForm queryForm = new ChinaPayInvoiceQueryForm();
//        queryForm.setMerNo(chinaPayConfig.getMerNo());
//        queryForm.setMerOrderDate(form.getMerOrderDate());
//        queryForm.setMerOrderId(form.getMerOrderId());
//        ChinaPayInvoiceQueryVO queryVO = chinaPayService.queryInvoice(queryForm);
//        log.info(JSON.toJSONString(queryVO));
    }
}
