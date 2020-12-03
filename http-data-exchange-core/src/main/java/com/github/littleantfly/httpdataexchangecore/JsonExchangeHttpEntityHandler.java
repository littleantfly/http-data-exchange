package com.github.littleantfly.httpdataexchangecore;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author littl
 */
public class JsonExchangeHttpEntityHandler extends  AbstractExchangeHttpEntityHandler<Map<String, String>> {

    @Override
    protected Map<String, String> parseToMap(ExchangeRequest req) {
        if(req == null) {
            return new HashMap<>(0);
        }
        return JSON.parseObject(JSON.toJSONString(req), new TypeReference<Map<String, String>>(){});
    }
}
