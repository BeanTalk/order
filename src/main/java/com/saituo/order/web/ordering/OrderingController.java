package com.saituo.order.web.ordering;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("order/list")
public class OrderingController {

	@RequiresPermissions("perms[order:list:all]")
	@RequestMapping("all_order")
	public void list(@RequestParam(required = false) String searchContext, Model model) {
		model.addAttribute("result", "searchContext");
	}
}
