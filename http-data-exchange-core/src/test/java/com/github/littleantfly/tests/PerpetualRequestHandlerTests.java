package com.github.littleantfly.tests;

import com.alibaba.fastjson.JSON;
import com.github.littleantfly.httpdataexchangecore.wrapper.PerpetualExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import com.github.littleantfly.tests.model.SimpleExchangeRequest;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


public class PerpetualRequestHandlerTests {


    @Test
    public void postNoArgsTest() {
        RestTemplate restTemplate = new RestTemplate();
        PerpetualExchangeHandler handler = new PerpetualExchangeHandler(restTemplate);
        ExchangeResponse resp = handler.post("http://localhost:8080/test", null);
        System.out.println(resp.getResponseBody());
    }


    @Test
    public void postHasArgsTest() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        PerpetualExchangeHandler handler = new PerpetualExchangeHandler(restTemplate, headers);

        SimpleExchangeRequest req = new SimpleExchangeRequest();
        req.setName("JIM");
        ExchangeResponse resp = handler.post("http://localhost:8080/test", req);
        System.out.println(resp.getResponseBody());
    }

    @Test
    public void simpleTest(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> params = new HashMap<>(1);
        params.put("name", "jim");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSON.toJSONString(params), httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/test", httpEntity, String.class);
        System.out.println(JSON.toJSONString(responseEntity));

    }

}
