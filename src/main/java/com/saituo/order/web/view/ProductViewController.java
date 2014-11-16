package com.saituo.order.web.view;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saituo.order.commons.page.PageRequest;

/**
 * 产品查看控制器
 */
@Controller
@RequestMapping("views")
public class ProductViewController {

	@RequestMapping("order")
	public String list(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null || subject.isAuthenticated()) {
			return "redirect:/logout";
		}
		return "login";
	}

}
