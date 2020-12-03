package com.github.littleantfly.tests;

import com.alibaba.fastjson.JSON;
import com.github.littleantfly.httpdataexchangecore.ExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.encrypt.DefaultHttpEncryptServiceImpl;
import com.github.littleantfly.httpdataexchangecore.impl.EncryptExchangeHandlerWrapper;
import com.github.littleantfly.httpdataexchangecore.impl.PerpetualExchangeHandler;
import com.github.littleantfly.httpdataexchangecore.impl.SignatureExchangeHandlerWrapper;
import com.github.littleantfly.tests.model.EncryptRequestDemo;
import com.github.littleantfly.tests.model.SignatureRequestDemo;
import com.github.littleantfly.tests.model.UserForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class SignatureRequestHandlerWrapperTests {


    @Test
    public void postTest() {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        PerpetualExchangeHandler handler = new PerpetualExchangeHandler(restTemplate, httpHeaders);


        SignatureRequestDemo request = new SignatureRequestDemo();
        UserForm userForm = new UserForm();
        userForm.setName("JIM");

        request.setReqData(JSON.toJSONString(userForm));

        ExchangeHandler encryptRequestHandlerWrapper = new EncryptExchangeHandlerWrapper(handler, new DefaultHttpEncryptServiceImpl());

        SignatureExchangeHandlerWrapper signatureRequestHandlerWrapper = new SignatureExchangeHandlerWrapper(encryptRequestHandlerWrapper, new DefaultHttpEncryptServiceImpl());

        Object res = signatureRequestHandlerWrapper.post("http://localhost:8080/encrypt", request);

        log.info(JSON.toJSONString(res));
    }


}
