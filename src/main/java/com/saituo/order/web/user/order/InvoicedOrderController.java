package com.saituo.order.web.user.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.saituo.order.commons.SessionVariable;
import com.saituo.order.service.user.UserOrderService;

@Controller
@SessionAttributes(SessionVariable.DEFAULT_SESSION_KEY)
@RequestMapping("order")
public class InvoicedOrderController {

	@Autowired
	private UserOrderService userOrderService;

	/**
	 * 财务开具发票
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequestMapping(value = "finance/invoiced", method = RequestMethod.POST)
	public String invoicedForOrder(@RequestParam Map<String, Object> filter, @RequestParam List<String> productIds,
			@RequestParam List<String> subscripts) {

		List<String> productOrderList = new ArrayList<String>();
		for (int i = 0; i < productIds.size(); i++) {
			StringBuilder sb = new StringBuilder(120);
			sb.append(productIds.get(i)).append("~").append(99.99).append("~").append(subscripts.get(0));
			productOrderList.add(sb.toString());
		}
		filter.put("productOrderList", productOrderList);
		userOrderService.doProductOrderInvoiced(filter);
		return "redirect:/order/list/all_order";
	}

	/**
	 * 财务已送发票待结款
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequestMapping(value = "finance/invoiced_nopay", method = RequestMethod.POST)
	public String invoicedNopayForOrder(@RequestParam Map<String, Object> filter,
			@RequestParam List<String> productIds, @RequestParam List<String> subscripts) {

		List<String> productOrderList = new ArrayList<String>();
		for (int i = 0; i < productIds.size(); i++) {
			StringBuilder sb = new StringBuilder(120);
			sb.append(productIds.get(i)).append("~").append(99.99).append("~").append(subscripts.get(0));
			productOrderList.add(sb.toString());
		}
		filter.put("productOrderList", productOrderList);
		userOrderService.doProductOrderAlreadySend(filter);
		return "redirect:/order/list/all_order";
	}

	/**
	 * 财务结款
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequestMapping(value = "finance/pay", method = RequestMethod.POST)
	public String payForOrder(@RequestParam Map<String, Object> filter, @RequestParam List<String> productIds,
			@RequestParam List<String> subscripts) {

		List<String> productOrderList = new ArrayList<String>();
		for (int i = 0; i < productIds.size(); i++) {
			StringBuilder sb = new StringBuilder(120);
			sb.append(productIds.get(i)).append("~").append(99.99).append("~").append(subscripts.get(0));
			productOrderList.add(sb.toString());
		}
		filter.put("productOrderList", productOrderList);
		userOrderService.doProductOrderReceivables(filter);
		return "redirect:/order/list/all_order";
	}
}
