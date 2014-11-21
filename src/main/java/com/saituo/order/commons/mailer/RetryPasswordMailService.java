package com.saituo.order.commons.mailer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.saituo.order.commons.utils.Encodes;
import com.saituo.order.service.cache.RedisCacheService;

import freemarker.template.TemplateException;

@Service
public class RetryPasswordMailService {

	private static final String SUBJECT_FOR_RETRYPASSWD = "请重新设置密码";

	@Value("${mail.send.from}")
	private String sendMailFrom;

	@Value("${mail.send.template}")
	private String sendMailTemplate;

	@Autowired
	private RedisCacheService redisCacheService;

	@Autowired
	private JavaMailService javaMailService;

	public void sendEmailForRetryPassword(String loginname, String email) {

		String md5 = generatePin(loginname);
		Map<String, String> contextMapData = new HashMap<String, String>();
		contextMapData.put("md5", md5);
		contextMapData.put("loginname", Encodes.encodeBase64(loginname.getBytes()));
		try {
			javaMailService.sendByTemplate(email, sendMailFrom, SUBJECT_FOR_RETRYPASSWD, sendMailTemplate, null,
					contextMapData);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			redisCacheService.putPasswordPinToRedisCache(loginname, md5);
		}
	}

	/**
	 * PIN 为UUID与当前userId的MD5加密结果
	 */
	public String generatePin(String loginName) {
		Md5Hash md5Hash = new Md5Hash(UUID.randomUUID().toString() + loginName);
		return md5Hash.toHex();
	}
}
