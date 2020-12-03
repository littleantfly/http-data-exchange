package com.github.littleantfly.httpdataexchangedemo.rest.controller;

import com.github.littleantfly.httpdataexchangecore.annotation.AutoEncrypt;
import com.github.littleantfly.httpdataexchangedemo.form.EncryptRequestForm;
import com.github.littleantfly.httpdataexchangedemo.service.impl.DefaultSecurityServiceImpl;
import com.github.littleantfly.httpdataexchangedemo.vo.EncryptResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author littl
 */
@Slf4j
@AutoEncrypt(DefaultSecurityServiceImpl.class)
@RestController
@RequestMapping("/encrypt/demo/")
public class EncryptDemoRestController {



    @RequestMapping("test")
    public Object test(@RequestBody EncryptRequestForm form){
        EncryptResponseVO responseVO = new EncryptResponseVO();
        responseVO.setRespData("test");
        return responseVO;
    }
}
