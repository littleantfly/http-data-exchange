package com.enneagon.digital.exchange.protocol.chinaunion.tests;

import com.alibaba.fastjson.JSON;
import com.enneagon.digital.exchange.protocol.chinaunion.ChinaUnionService;
import com.enneagon.digital.exchange.protocol.chinaunion.biz.QueryStationInfoVO;
import com.enneagon.digital.exchange.protocol.chinaunion.biz.QueryStationsInfoForm;
import com.enneagon.digital.exchange.protocol.chinaunion.config.ChinaUnionConfig;
import org.junit.Before;
import org.junit.Test;

public class ChinaUnionServiceTests {

    private ChinaUnionService chinaUnionService;


    @Before
    public void init(){
        chinaUnionService = new ChinaUnionService(new ChinaUnionConfig(){
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
        });
    }


    @Test
    public void queryStationInfosTest(){
        QueryStationsInfoForm form = new QueryStationsInfoForm();
        form.setLastQueryTime("2020-07-20");
        form.setPageNo(1);
        QueryStationInfoVO vo = chinaUnionService.queryStationInfo(form);
        System.out.println(JSON.toJSONString(vo));
    }
}
