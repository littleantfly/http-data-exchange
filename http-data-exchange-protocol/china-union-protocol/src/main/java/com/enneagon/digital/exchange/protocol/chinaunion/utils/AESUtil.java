package com.enneagon.digital.exchange.protocol.chinaunion.utils;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化
 *
 * @author Shengbang.Jiang
 * @since 2019-01-02
 */
@Slf4j
public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * This is the encrypt
     *
     * @param str 待加密的
     * @param key 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位
     * @param iv  使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return
     */
    public static String encrypt(String str, String key, String iv) {
        try {
            SecretKeySpec sKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), KEY_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
            return new BASE64Encoder().encode(encrypted).replaceAll("\r|\n", "");
        } catch (Exception e) {
            log.error(String.format("AESUtil data encrypt exception: %s", new Object[]{e.getMessage()}));
        }

        return null;
    }

    /**
     * This is the decrypt
     *
     * @param str 待解密的
     * @param key 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位
     * @param iv  使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return
     */
    public static String decrypt(String str, String key, String iv) {
        try {
            SecretKeySpec sKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), KEY_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, ivParameterSpec);
            byte[] bytes = new BASE64Decoder().decodeBuffer(str);
            byte[] bytesDecode = cipher.doFinal(bytes);
            return new String(bytesDecode, "UTF-8");
        } catch (Exception e) {
            log.error(String.format("AESUtil data decryption exception: %s", new Object[]{e.getMessage()}));
        }

        return null;
    }

}