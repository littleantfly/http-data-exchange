package com.enneagon.digital.exchange.protocol.chinaunion.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * HMacMD5 是从 MD5 哈希函数构造的一种键控哈希算法，被用作基于哈希的消息验证代码 (HMAC)。此 HMAC 进程将密钥与消息数据混合，
 * 使用哈希函数对混合结果进行哈希计算，将所得哈希值与该密钥混合，然后再次应用哈希函数。输出的哈希值长度为 128 位
 *
 * @author Shengbang.Jiang
 * @since 2019-01-02
 */
@Slf4j
public class HMacMD5Util {

    /**
     * 计算参数的md5信息
     *
     * @param str 待处理的字节数组
     * @return md5摘要信息
     */
    public static byte[] md5(byte[] str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str);
            return md.digest();
        } catch (NoSuchAlgorithmException nsae) {
            log.error(String.format("Data md5 exception: %s", new Object[]{nsae.getMessage()}));
        }

        return null;
    }

    /**
     * 将待加密数据data，通过密钥key，使用hmac-md5算法进行加密，然后返回加密结果。 参照rfc2104 HMAC算法介绍实现。
     *
     * @param key  密钥
     * @param data 待加密数据
     * @return 加密结果
     */
    public static byte[] getHmacMd5Bytes(byte[] key, byte[] data) {
        /**
         * HmacMd5 calculation formula: H(K XOR opad, H(K XOR ipad, text))
         * HmacMd5 计算公式：H(K XOR opad, H(K XOR ipad, text))
         * H代表hash算法，本类中使用MD5算法，K代表密钥，text代表要加密的数据 ipad为0x36，opad为0x5C。
         */
        int length = 64;
        byte[] ipad = new byte[length];
        byte[] opad = new byte[length];
        for (int i = 0; i < 64; i++) {
            ipad[i] = 0x36;
            opad[i] = 0x5C;
        }
        byte[] actualKey = key; // Actual key.
        byte[] keyArr = new byte[length]; // Key bytes of 64 bytes length

        /**
         * If key's length is longer than 64,then use hash to digest it and use
         * the result as actual key. 如果密钥长度，大于64字节，就使用哈希算法，计算其摘要，作为真正的密钥。
         */
        if (key.length > length) {
            actualKey = md5(key);
        }
        for (int i = 0; i < actualKey.length; i++) {
            keyArr[i] = actualKey[i];
        }

        /**
         * append zeros to K 如果密钥长度不足64字节，就使用0x00补齐到64字节。
         */
        if (actualKey.length < length) {
            for (int i = actualKey.length; i < keyArr.length; i++) {
                keyArr[i] = 0x00;
            }
        }

        /**
         * calc K XOR ipad 使用密钥和ipad进行异或运算。
         */
        byte[] kIpadXorResult = new byte[length];
        for (int i = 0; i < length; i++) {
            kIpadXorResult[i] = (byte) (keyArr[i] ^ ipad[i]);
        }

        /**
         * append "text" to the end of "K XOR ipad" 将待加密数据追加到K XOR ipad计算结果后面。
         */
        byte[] firstAppendResult = new byte[kIpadXorResult.length + data.length];
        for (int i = 0; i < kIpadXorResult.length; i++) {
            firstAppendResult[i] = kIpadXorResult[i];
        }
        for (int i = 0; i < data.length; i++) {
            firstAppendResult[i + keyArr.length] = data[i];
        }

        /**
         * calc H(K XOR ipad, text) 使用哈希算法计算上面结果的摘要。
         */
        byte[] firstHashResult = md5(firstAppendResult);

        /**
         * calc K XOR opad 使用密钥和opad进行异或运算。
         */
        byte[] kOpadXorResult = new byte[length];
        for (int i = 0; i < length; i++) {
            kOpadXorResult[i] = (byte) (keyArr[i] ^ opad[i]);
        }

        /**
         * append "H(K XOR ipad, text)" to the end of "K XOR opad" 将H(K XOR
         * ipad, text)结果追加到K XOR opad结果后面
         */
        byte[] secondAppendResult = new byte[kOpadXorResult.length + firstHashResult.length];
        for (int i = 0; i < kOpadXorResult.length; i++) {
            secondAppendResult[i] = kOpadXorResult[i];
        }
        for (int i = 0; i < firstHashResult.length; i++) {
            secondAppendResult[i + keyArr.length] = firstHashResult[i];
        }

        /**
         * H(K XOR opad, H(K XOR ipad, text)) 对上面的数据进行哈希运算。
         */
        return md5(secondAppendResult);
    }

    public static String byte2HexStr(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < b.length; n++) {
            String tmp = Integer.toHexString(b[n] & 0xFF);
            sb.append((tmp.length() == 1) ? "0" + tmp : tmp);
        }

        return sb.toString().toUpperCase().trim();
    }


}