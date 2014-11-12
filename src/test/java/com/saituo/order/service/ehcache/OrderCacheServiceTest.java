package com.saituo.order.service.ehcache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.service.ServiceTestCaseSupport;

public class OrderCacheServiceTest extends ServiceTestCaseSupport {

	@Autowired
	private OrderCacheService orderCacheService;

	@Test
	public void testput() {
		orderCacheService.put("key2", "value2");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String value = (String) orderCacheService.get("key2");
		System.out.println("value:" + value);
	}

}
