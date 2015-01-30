package com.saituo.order.web.user.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.commons.enumeration.entity.CompaintType;
import com.saituo.order.commons.enumeration.entity.ComplainStatus;
import com.saituo.order.commons.enumeration.entity.HandlerResult;
import com.saituo.order.commons.page.Page;
import com.saituo.order.commons.page.PageRequest;
import com.saituo.order.entity.order.Product;
import com.saituo.order.entity.user.OrderComplaint;
import com.saituo.order.service.account.AccountService;
import com.saituo.order.service.order.BuyCardService;
import com.saituo.order.service.order.ProductBrandService;
import com.saituo.order.service.order.ProductService;
import com.saituo.order.service.user.AddressService;
import com.saituo.order.service.user.AuditHisService;
import com.saituo.order.service.user.OrderComplainService;
import com.saituo.order.service.user.UserOrderService;
import com.saituo.order.service.variable.SystemVariableService;

@Controller
@SessionAttributes(SessionVariable.DEFAULT_SESSION_KEY)
@RequestMapping("order/list")
public class SupportController {

	@Autowired
	private UserOrderService userOrderService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private AuditHisService auditHisService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductBrandService productBrandService;

	@Autowired
	private BuyCardService buyCardService;

	@Autowired
	private SystemVariableService systemVariableService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private OrderComplainService orderComplainService;

	@RequestMapping(value = "support/complain_process", method = RequestMethod.POST)
	public String customerComplainForOrdering(@RequestParam Map<String, Object> filter) {

		filter.put("id", VariableUtils.typeCast(filter.get("complain_id"), String.class));
		orderComplainService.doUpdateOrderComplaint(filter);
		return "redirect:/order/list/support/complain_view";
	}

	/**
	 * 客户查看内勤修改完价格的订单
	 * 
	 * @param items
	 *            :productId
	 * @param subscripts
	 *            :订购数量
	 * @param addressId
	 *            :地址Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "support/complain_view", method = RequestMethod.GET)
	public void customerComplainViewForOrdering(PageRequest pageRequest, @RequestParam Map<String, Object> filter,
			Model model) {

		filter.putAll(pageRequest.getMap());
		List<OrderComplaint> orderComplainList = orderComplainService.getOrderComplaintList(filter);
		for (OrderComplaint orderComplaint : orderComplainList) {
			Product product = productService.getProductByProductId(VariableUtils.typeCast(
					orderComplaint.getProductId(), Integer.class));
			orderComplaint.setProduct(product);
		}

		int orderComplainCount = orderComplainService.getOrderComplaintCount(filter);
		Page<OrderComplaint> page = new Page<OrderComplaint>(pageRequest, orderComplainList, orderComplainCount);

		model.addAttribute("complainType", VariableUtils.getVariables(CompaintType.class));
		model.addAttribute("handlerResults", VariableUtils.getVariables(HandlerResult.class));
		model.addAttribute("complainStatus", VariableUtils.getVariables(ComplainStatus.class));
		model.addAttribute("page", page);
		model.addAllAttributes(filter);
	}

}
