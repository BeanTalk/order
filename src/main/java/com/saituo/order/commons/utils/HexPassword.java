package com.saituo.order.commons.utils;

import com.saituo.order.commons.security.Digests;
import com.saituo.order.commons.security.SecurityEncode;

public class HexPassword {

	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SecurityEncode.SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, SecurityEncode.HASH_INTERATIONS);
		return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
	}

	/**
	 * 验证密码
	 * 
	 * @param plainPassword
	 *            明文密码
	 * @param password
	 *            密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0, 16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, SecurityEncode.HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
	}
}
