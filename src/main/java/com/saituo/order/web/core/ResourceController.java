package com.saituo.order.web.core;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saituo.order.service.account.AccountService;

/**
 * 资源控制器
 */
@Controller
@RequestMapping("/account/resource")
public class ResourceController {

	@Autowired
	private AccountService accountService;

	/**
	 * 资源列表
	 * 
	 * @return 响应页面:WEB-INF/page/account/resource/list.html
	 */
	@RequestMapping("list")
	public List<Map<String, Object>> list() {
		return accountService.mergeMenus(accountService.getMenus());
	}
}
