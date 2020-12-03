package com.github.littleantfly.httpdataexchangecore;

/**
 * HTTP响应的原始报文处理接口
 * @author littl
 */
public interface DataHandler {

    Object handler(Object data);
}
