package com.github.littleantfly.tests;

import com.alibaba.fastjson.JSON;
import com.github.littleantfly.httpdataexchangecore.encrypt.DefaultHttpEncryptServiceImpl;
import com.github.littleantfly.httpdataexchangecore.impl.EncryptExchangeHandlerWrapper;
import com.github.littleantfly.httpdataexchangecore.impl.PerpetualExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.model.ExchangeResponse;
import com.github.littleantfly.tests.model.EncryptRequestDemo;
import com.github.littleantfly.tests.model.UserForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class EncryptRequestHandlerWrapperTests {


    @Test
    public void postTest() {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        PerpetualExchangeHandler handler = new PerpetualExchangeHandler(restTemplate, httpHeaders);
        EncryptExchangeHandlerWrapper encryptRequestHandlerWrapper = new EncryptExchangeHandlerWrapper(handler, new DefaultHttpEncryptServiceImpl());

        EncryptRequestDemo request = new EncryptRequestDemo();
        UserForm userForm = new UserForm();
        userForm.setName("Jim");
        request.setReqData(JSON.toJSONString(userForm));
        ExchangeResponse res = encryptRequestHandlerWrapper.post("http://localhost:8080/encrypt", request);
        log.info(JSON.toJSONString(res));
    }
}
