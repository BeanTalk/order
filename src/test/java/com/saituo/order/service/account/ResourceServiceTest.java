package com.saituo.order.service.account;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.service.ServiceTestCaseSupport;

/**
 * 资源业务逻辑测试类
 *
 */
public class ResourceServiceTest extends ServiceTestCaseSupport {

	@Autowired
	private AccountService accountService;

	@Test
	public void testMegerResources() {
		List<Map<String, Object>> list = accountService.getMenus();
		List<Map<String, Object>> filter = accountService.mergeMenus(list);
		System.out.println(filter.get(0).size());
	}

}