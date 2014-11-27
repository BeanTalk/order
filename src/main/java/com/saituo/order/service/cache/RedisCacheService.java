package com.saituo.order.service.cache;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {

	/*********************************************************************************************************************
	 * 重置密码功能一系列Cache
	 *********************************************************************************************************************/

	private static final String RETRY_PASSWORD_CACHE_KEY = "retry_password_cache";

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 存储loginName对应的MD5密文(PIN),并设置30分钟有效;
	 * <p>
	 * 30分钟过期后，清空Cache对应的数据
	 * 
	 * @param loginName
	 * @param md5
	 */
	public void putPasswordPinToRedisCache(String loginName, String md5) {
		redisTemplate.boundHashOps(RETRY_PASSWORD_CACHE_KEY).put(loginName, md5);
		redisTemplate.expire(loginName, 30, TimeUnit.MINUTES);
	}

	/**
	 * 通过loginName获取MD5 PIN
	 * 
	 * @param loginName
	 * @return
	 */
	public String getPinFromRedisCache(String loginName) {
		return String.valueOf(redisTemplate.boundHashOps(RETRY_PASSWORD_CACHE_KEY).get(loginName));
	}

	/*********************************************************************************************************************
	 * 购物车对应的一系列Cache
	 *********************************************************************************************************************/

	private static final String ADD_BAG_FOR_BUY_CARD_CACHE_KEY = "addedbag";

	/**
	 * 加入购物车功能对应的Cache，格式为 KEY+userId : LIST(productId:num)
	 * 
	 * @param loginName
	 * @param productId
	 */
	public void putProductIntoBagAboutBuyCard(String userId, String productId, String num) {
		redisTemplate.boundHashOps(ADD_BAG_FOR_BUY_CARD_CACHE_KEY + userId).put(productId, num);
	}

	/**
	 * 统计该用户的购物车中的数量
	 * 
	 * @param userId
	 * @return
	 */
	public Long countProductInBagAboutBuyCard(String userId) {
		return redisTemplate.boundHashOps(ADD_BAG_FOR_BUY_CARD_CACHE_KEY + userId).size();
	}

	/**
	 * 获取该用户购物车中的数据
	 * 
	 * @param userId
	 * @return
	 */
	public Map<Object, Object> getProductIdAndBuyNumMapFromCache(String userId) {
		Map<Object, Object> mapData = redisTemplate.boundHashOps(ADD_BAG_FOR_BUY_CARD_CACHE_KEY + userId).entries();
		return mapData;
	}

	/**
	 * 是否已经添加到购物车中
	 * 
	 * @param userId
	 * @param productId
	 * @return
	 */
	public boolean isAddedIntoBuyCard(String userId, String productId) {
		return redisTemplate.boundHashOps(ADD_BAG_FOR_BUY_CARD_CACHE_KEY + userId).hasKey(productId);
	}

	/**
	 * 从购物车中删除该产品信息
	 * 
	 * @param userId
	 * @param productId
	 */
	public void removeProductListFromBuyCard(String userId, String... productIds) {
		redisTemplate.boundHashOps(ADD_BAG_FOR_BUY_CARD_CACHE_KEY + userId).delete(productIds);
	}
}
