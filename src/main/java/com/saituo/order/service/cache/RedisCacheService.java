package com.saituo.order.service.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private static final String CACHEKEY = "retrypassword";

	public void putPasswordPinToRedisCache(String loginName, String md5) {
		redisTemplate.boundHashOps(CACHEKEY).put(loginName, md5);
		redisTemplate.expire(loginName, 30, TimeUnit.MINUTES);
	}

	public String getPasswordFromRedisCache(String loginName) {
		return String.valueOf(redisTemplate.boundHashOps(CACHEKEY).get(loginName));
	}
}
