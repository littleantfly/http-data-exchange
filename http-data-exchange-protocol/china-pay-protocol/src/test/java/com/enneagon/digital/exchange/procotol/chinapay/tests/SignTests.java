package com.enneagon.digital.exchange.procotol.chinapay.tests;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.chinapay.secss.SecssUtil;
import com.enneagon.digital.exchange.procotol.chinapay.ChinaPayHttpEncryptServiceImpl;
import com.enneagon.digital.exchange.procotol.chinapay.ChinaPayResponse;
import com.github.littleantfly.httpdataexchangecore.encrypt.EncryptService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SignTests {


    @Test
    public void signTest(){

        SecssUtil secssUtil = new SecssUtil();
        boolean init = secssUtil.init("/data/jars/files/yc-security.properties");
        if(!init) {
            System.out.println("银联加密初始化失败");
        }

        EncryptService encryptService = new ChinaPayHttpEncryptServiceImpl(secssUtil);

        String resp = "merNo=573221908078001&signature=qLobhICBrUXsMHmTRGK8vk/TlNfSM/eY+JwRIWwZ44WbPrPUqt65vbE/14B0DeTsg2G1ZVCqO+2G10G+fsrDXpY66WBb7hZnzmHBZa6reQMgLfqKNvAaQnEVC+eD9/DyIU5ZrAmKyw05rZvt1WCYEcJg7g/tFTftzGnMKCp7kZI=&respData=eyJidXllclRlbGVwaG9uZSI6IjIyIiwiYW1vdW50IjoiMTAiLCJidXllckJhbmsiOiIzMyIsIm1lck9yZGVyRGF0ZSI6IjIwMTkwOTE5Iiwib3JkZXJJZCI6IjExNzQ1NzgzNDgxMDc1ODM0OTAiLCJidXllclRheENvZGUiOiI5MTMxMDExMjA4ODU5NzUwOVciLCJyZW1hcmsiOiIxMSIsImJ1eWVyTmFtZSI6IjUyMTczIiwiYnV5ZXJBZGRyZXNzIjoiMjIiLCJtZXJObyI6IjU3MzIyMTkwODA3ODAwMSIsImJ1eWVyQWNjb3VudCI6IjUyMTczIiwicmVzcE1zZyI6IuWVhuaIt+mqjOetvuWksei0pSIsImJ1c2lUeXBlIjoiMTAyOCIsIm9yZGVyRGF0ZSI6IjIwMTkwOTE5IiwicmVzcENvZGUiOiIxMDAxMDAwMSIsIm1lck9yZGVySWQiOiIxMTc0NTc4MzQ5NjA0OTcwNDk3In0=";

        Map<String, String> respMap = paserStrtoMap(resp);
        ChinaPayResponse response = JSON.parseObject(JSON.toJSONString(respMap), ChinaPayResponse.class);


        boolean flag = encryptService.verifySignature(response);

        log.info("sign: {}", flag);
    }



    public static Map<String, String> paserStrtoMap(String respStr) {
        Map<String, String> data = new HashMap<String, String>();
        if (StrUtil.isNotBlank(respStr)) {
            String[] strs = respStr.split("&");
            for (String str : strs) {
                if (StrUtil.isBlank(str)) {
                    continue;
                }
                int index = str.indexOf("=");
                data.put(str.substring(0, index),str.substring(index+1));
            }
        }
        return data;
    }
}
