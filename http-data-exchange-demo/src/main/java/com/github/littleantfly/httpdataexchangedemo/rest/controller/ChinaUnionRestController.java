package com.github.littleantfly.httpdataexchangedemo.rest.controller;

import com.alibaba.fastjson.JSON;
import com.enneagon.digital.exchange.protocol.chinaunion.ChinaUnionRequest;
import com.enneagon.digital.exchange.protocol.chinaunion.ChinaUnionResponse;
import com.enneagon.digital.exchange.protocol.chinaunion.biz.QueryStationInfoVO;
import com.enneagon.digital.exchange.protocol.chinaunion.biz.QueryStationsInfoForm;
import com.enneagon.digital.exchange.protocol.chinaunion.utils.ChinaUnionUtil;
import com.github.littleantfly.httpdataexchangecore.annotation.AutoEncrypt;
import com.github.littleantfly.httpdataexchangedemo.service.impl.ChinaUnionSecurityImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author littl
 */
@RestController
@RequestMapping("/rest/china/union/")
@AutoEncrypt(ChinaUnionSecurityImpl.class)
public class ChinaUnionRestController {

    @PostMapping("query_stations_info")
    public ChinaUnionResponse queryStationsInfo(@Validated @RequestBody ChinaUnionRequest request) {
        QueryStationsInfoForm form = ChinaUnionUtil.getForm(request, QueryStationsInfoForm.class);
        System.out.println(JSON.toJSONString(form));
        QueryStationInfoVO vo = new QueryStationInfoVO();
        vo.setStationID("123456");
        vo.setStationName("jim");
        return ChinaUnionUtil.getResponse(vo);
    }
}
