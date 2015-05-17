package com.saituo.order.web.view;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.page.Page;
import com.saituo.order.commons.page.PageRequest;
import com.saituo.order.commons.utils.MathUtils;
import com.saituo.order.entity.order.Product;
import com.saituo.order.service.order.ProductService;

/**
 * 产品查看控制器
 */
@Controller
@RequestMapping("views/search")
public class ProductViewController {

	@Value("${register.office.id}")
	private String officeId;

	@Value("${register.role.id}")
	private String roleId;

	@Autowired
	private ProductService productService;

	@RequestMapping("productlist")
	public Page<Product> productlist(PageRequest pageRequest, @RequestParam(required = true) String searchContext,
			Model model) {
		if (StringUtils.isEmpty(searchContext)) {
			return null;
		}
		model.addAttribute("searchContext", searchContext);
		model.addAttribute("newRegisterOfficeId", officeId);
		Page<Product> pages = productService.searchProduct(pageRequest, searchContext);

		// 当前用户为已登录用户时,将显示折扣价格
		Subject subject = SecurityUtils.getSubject();
		if (subject != null && subject.isAuthenticated()) {
			for (Product product : pages.getContent()) {

				// 当为内部用户时，显示的是采购折扣*目录价
				if (SessionVariable.getCurrentSessionVariable().getIsInternalUser()) {
					product.setCatalogFee(MathUtils.getDoublePoint(product.getCatalogFee() * product.getBuyDiscount()));
				} else {
					// 当其为无组织用户时，则为新注册用户,显示目录价
					if (SessionVariable.getCurrentSessionVariable().getGroupId() == Integer.valueOf(officeId)) {
						product.setCatalogFee(MathUtils.getDoublePoint(product.getCatalogFee()));
					} else {
						product.setCatalogFee(MathUtils.getDoublePoint(product.getCatalogFee()
								* product.getWeightDiscount()));
					}
				}
			}
		}
		return pages;
	}
}
