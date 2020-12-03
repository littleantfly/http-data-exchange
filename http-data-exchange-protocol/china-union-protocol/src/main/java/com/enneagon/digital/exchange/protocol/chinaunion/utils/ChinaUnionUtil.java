package com.enneagon.digital.exchange.protocol.chinaunion.utils;

import com.alibaba.fastjson.JSON;
import com.enneagon.digital.exchange.protocol.chinaunion.ChinaUnionRequest;
import com.enneagon.digital.exchange.protocol.chinaunion.ChinaUnionResponse;

public class ChinaUnionUtil {

    public static <T> T getForm(ChinaUnionRequest request, Class<T> clazz) {
        return JSON.parseObject(request.getData(), clazz);
    }

    public static ChinaUnionResponse getResponse(Object data) {
        ChinaUnionResponse response = new ChinaUnionResponse();
        if(data != null) {
            response.setData((data instanceof String)?data.toString():JSON.toJSONString(data));
        }
        response.setRet(0);
        response.setMsg("请求成功");
        return response;
    }
}
