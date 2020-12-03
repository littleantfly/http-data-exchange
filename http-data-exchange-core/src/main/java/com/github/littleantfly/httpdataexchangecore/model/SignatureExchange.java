package com.github.littleantfly.httpdataexchangecore.model;

/**
 * 加签、签对象
 * @author jim
 */
public interface SignatureExchange {

    /**
     * 设置签名
     * @param sign
     */
    void setExchangeSign(String sign);

    /**
     * 获取签名
     * @return
     */
    String getExchangeSign();

}
