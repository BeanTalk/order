package com.saituo.order.commons.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class HexPassword {

	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	// public static String entryptPassword(String plainPassword) {
	// byte[] salt = Digests.generateSalt(SecurityEncode.SALT_SIZE);
	// byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt,
	// SecurityEncode.HASH_INTERATIONS);
	// return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
	// }

	public static String entryptPassword(String plainPassword) {
		return DigestUtils.md5Hex(plainPassword);
	}
}
