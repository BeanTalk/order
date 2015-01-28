package com.saituo.order.web.user;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiresAuthentication
@RequestMapping("order/mine")
public class AssetController {

	@RequestMapping(value = "bean/bean_view", method = RequestMethod.GET)
	public void getBeanHistory(Model model) {
		
	}
}
