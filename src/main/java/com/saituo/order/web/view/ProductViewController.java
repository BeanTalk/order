package com.saituo.order.web.view;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saituo.order.commons.page.Page;
import com.saituo.order.commons.page.PageRequest;
import com.saituo.order.entity.order.Product;
import com.saituo.order.service.order.ProductService;

/**
 * 产品查看控制器
 */
@Controller
@RequestMapping("views/search")
public class ProductViewController {

	@Autowired
	private ProductService productService;

	@RequestMapping("productlist")
	public Page<Product> productlist(PageRequest pageRequest, @RequestParam(required = true) String searchContext,
			Model model) {
		if (StringUtils.isEmpty(searchContext)) {
			return null;
		}
		model.addAttribute("searchContext", searchContext);
		return productService.searchProduct(pageRequest, searchContext);
	}

}
