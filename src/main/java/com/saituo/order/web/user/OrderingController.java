package com.saituo.order.web.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.commons.enumeration.entity.UserOrderingState;
import com.saituo.order.entity.user.UserOrder;
import com.saituo.order.service.user.AddressService;
import com.saituo.order.service.user.UserOrderService;

@Controller
@SessionAttributes(SessionVariable.DEFAULT_SESSION_KEY)
@RequestMapping("order")
public class OrderingController {

	@Autowired
	private UserOrderService userOrderService;

	@Autowired
	private AddressService addressService;

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
	public String saveCustomerOrdering(@RequestParam Map<String, Object> filter, @RequestParam List<String> productIds,
			@RequestParam List<String> subscripts, Model model) {

		List<String> productOrderList = new ArrayList<String>();
		for (int i = 0; i < productIds.size(); i++) {
			StringBuilder sb = new StringBuilder(120);
			sb.append(productIds.get(i)).append("~").append(99.99).append("~").append(subscripts.get(0));
			productOrderList.add(sb.toString());
		}
		filter.put("productOrderList", productOrderList);
		userOrderService.doCreateUserOrder(filter);
		return "redirect:/order/list/all_order";
	}

	/**
	 * 查看所有的订单
	 * 
	 * @param filter
	 * @param model
	 */
	@RequestMapping(value = "list/all_order", method = RequestMethod.GET)
	public void getAllOrderingList(@RequestParam Map<String, Object> filter, Model model) {

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		filter.put("userId", userId);

		List<UserOrder> userOrderList = userOrderService.getUserOrderList(filter);
		List<Map<String, Object>> userOrderAndDetailInfoResultList = Lists.newArrayList();

		for (UserOrder userOrder : userOrderList) {
			String userOrderId = String.valueOf(userOrder.getUserOrderId());
			Map<String, Object> mapData = Maps.newHashMap();
			mapData.put("userOrderId", userOrderId);
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}
		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("userOrderAndDetailInfoResultList", userOrderAndDetailInfoResultList);
		model.addAttribute("userName", SessionVariable.getCurrentSessionVariable().getUser().get("name"));
	}
}
