package com.saituo.order.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 产品查看控制器
 */
@Controller
@RequestMapping("views")
public class ProductViewController {

	@RequestMapping("product")
	public void list(@RequestParam(required = false) String searchContext, Model model) {
		if (searchContext != null) {
			model.addAttribute("result", "searchContext : " + searchContext);
		}
	}

}
