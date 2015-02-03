//package com.saituo.order.service.ehcache;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.saituo.order.service.ServiceTestCaseSupport;
//import com.saituo.order.service.cache.RedisCacheService;
//
//public class RedisCache extends ServiceTestCaseSupport {
//
//	@Autowired
//	private RedisCacheService redisCacheService;
//
//	// @Test
//	public void testput() {
//		redisCacheService.putProductIntoBagAboutBuyCard("userId1", "productId3", String.valueOf(6));
//	}
//
//	// @Test
//	public void testcount() {
//		Long num = redisCacheService.countProductInBagAboutBuyCard("userId1");
//		System.out.println(num);
//	}
//
//}
