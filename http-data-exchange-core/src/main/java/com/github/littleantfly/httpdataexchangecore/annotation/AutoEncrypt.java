package com.github.littleantfly.httpdataexchangecore.annotation;

import com.github.littleantfly.httpdataexchangecore.external.HttpSecurityContext;

import java.lang.annotation.*;

/**
 * ResponseEncrypt
 *
 * @author Jim
 * @version 1.0
 * @date 2019/7/20 13:55
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface AutoEncrypt {

    Class<? extends HttpSecurityContext> value();

    String name() default "";
}
