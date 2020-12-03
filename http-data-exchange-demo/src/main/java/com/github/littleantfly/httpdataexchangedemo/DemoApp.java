package com.github.littleantfly.httpdataexchangedemo;

import com.github.littleantfly.httpdataexchangecore.annotation.EnableEncrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author littl
 */
@EnableEncrypt
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass =true)
public class DemoApp {

    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }
}
