package com.github.littleantfly.httpdataexchangedemo.aspect;

import com.enneagon.digital.exchange.protocol.chinaunion.utils.ChinaUnionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;


@Aspect
//@Configuration
public class ChinaUnionControllerAspect {

//    @Pointcut("@annotation(com.github.littleantfly.httpdataexchangedemo.annotation.ChinaUnionPack)")
    @Pointcut("execution(* com.github.littleantfly.httpdataexchangedemo.rest.controller.ChinaUnionRestController.*(..))")

    public void point(){}

    @Around("point()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        Object[] params = pjp.getArgs();
//        ChinaUnionRequest chinaUnionRequest = (ChinaUnionRequest)params[0];
//        params[0] = JSON.parseObject(chinaUnionRequest.getData(), QueryStationsInfoForm.class);
        Object result = pjp.proceed(params);


        return result;
    }

    @AfterReturning(value="point()", returning = "obj")
    public Object methodReturnValueEnhance(Object obj) throws Throwable {
        return ChinaUnionUtil.getResponse(obj);
    }

}
