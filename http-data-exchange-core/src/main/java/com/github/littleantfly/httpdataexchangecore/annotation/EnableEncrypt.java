package com.github.littleantfly.httpdataexchangecore.annotation;


import com.github.littleantfly.httpdataexchangecore.external.RequestBodyDecryptAdvice;
import com.github.littleantfly.httpdataexchangecore.external.ResponseBodyEncryptAdvice;
import com.github.littleantfly.httpdataexchangecore.utils.SpringContextUtil;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author littl
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RequestBodyDecryptAdvice.class, ResponseBodyEncryptAdvice.class, SpringContextUtil.class})
public @interface EnableEncrypt {
}
