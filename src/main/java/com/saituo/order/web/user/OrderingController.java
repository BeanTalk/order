package com.saituo.order.web.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.saituo.order.service.user.UserOrderService;

@Controller
@RequestMapping("order")
public class OrderingController {

	@Autowired
	private UserOrderService userOrderService;

	/**
	 * 购物车中保存订单
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
	@RequestMapping(value = "customerOrder/save", method = RequestMethod.POST)
	public String saveCustomerOrdering(@RequestParam Map<String, Object> filter, Model model) {
		userOrderService.doCreateUserOrder(filter);
		return "";
	}
	
	@RequestMapping(value = "list/all_order", method = RequestMethod.GET)
	public void getAllOrderingList(@RequestParam Map<String, Object> filter, Model model) {
		model.addAttribute("result", "result");
	}
}
