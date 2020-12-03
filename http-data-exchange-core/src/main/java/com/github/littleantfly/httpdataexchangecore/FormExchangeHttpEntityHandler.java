package com.github.littleantfly.httpdataexchangecore;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author littl
 */
public class FormExchangeHttpEntityHandler extends  AbstractExchangeHttpEntityHandler<MultiValueMap<String, String>> {

    @Override
    protected MultiValueMap<String, String> parseToMap(ExchangeRequest req) {
        if(req == null) {
            return new LinkedMultiValueMap<>(0);
        }
        Map<String, String> map = JSON.parseObject(JSON.toJSONString(req), new TypeReference<Map<String, String>>(){});
        List<String> strList = new ArrayList<>(100);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            strList.add(entry.getKey() + ":" + JSON.toJSONString(Lists.newArrayList(entry.getValue())));
        }
        return JSON.parseObject("{"+ Joiner.on(",").join(strList)+"}", new TypeReference<LinkedMultiValueMap<String, String>>(){});
    }
}
