package com.enneagon.digital.exchange.procotol.chinapay.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

@Slf4j
public class EncryptUtil {

	/**
	 * 哈希算法
	 * 
	 * @param input
	 *            摘要数据
	 * @param alg
	 *            算法名称(MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512)
	 * @return
	 */
	public static String encode(String input, String alg) {
		try {
			if(StrUtil.isEmpty(input)){
				log.info("摘要数据为空. 摘要数据=[" + input + "];");
				return null;
			}
			if(StrUtil.isEmpty(alg)){
				log.info("算法名称为空. 算法名称=[" + alg + "];");
				return null;
			}
			
//			log.info("[摘要数据"+alg+"加密] 算法名称=[" + alg + "];摘要数据=[" + input + "];");
			//创建加密对象
			MessageDigest digit = MessageDigest.getInstance(alg);
			//摘要加密
			digit.update(input.getBytes("UTF-8"));
			//得到加密字符串结果
			String str = byte2hex(digit.digest());
//			log.info("[摘要数据"+alg+"加密] 摘要加密结果=[" + str + "];");
			return str;
		} catch (Exception e) {
			log.info("[摘要数据"+alg+"加密] 摘要数据加密异常. error=[" + e.toString() + "];");
		}
		return null;
	}
	
	/**
	 * byte数组转为String
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();

		for (int i = 0; i < b.length; i++) {
			String stmp = Integer.toHexString(b[i] & 0xFF);
			if (stmp.length() == 1) {
				hs.append("0").append(stmp);
			} else {
				hs.append(stmp);
			}
		}
		return hs.toString();
	}
}
