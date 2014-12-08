package com.saituo.order.web.user.order;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.saituo.order.commons.enumeration.entity.BillStatus;
import com.saituo.order.commons.enumeration.entity.ProductOrderState;
import com.saituo.order.commons.enumeration.entity.UserCatagory;
import com.saituo.order.commons.enumeration.entity.UserOrderingState;
import com.saituo.order.commons.page.Page;
import com.saituo.order.commons.page.PageRequest;
import com.saituo.order.entity.user.UserOrder;
import com.saituo.order.service.account.AccountService;
import com.saituo.order.service.user.UserOrderService;
import com.saituo.order.service.variable.SystemVariableService;

@Controller
@SessionAttributes(SessionVariable.DEFAULT_SESSION_KEY)
@RequestMapping("order/list/finance")
public class InvoicedOrderController {

	@Autowired
	private UserOrderService userOrderService;

	@Autowired
	private SystemVariableService systemVariableService;

	@Autowired
	private AccountService accountService;

	/**
	 * 财务查看未开具发票状态的订单
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequiresPermissions("perms[order:list:invoiced]")
	@RequestMapping(value = "invoiced_view", method = RequestMethod.GET)
	public void getinvoicedViewForProductsOrder(PageRequest pageRequest, @RequestParam Map<String, Object> filter,
			Model model) {

		String areaId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getAreaId());
		Integer userCatagory = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser()
				.get("userCatagory"));

		if (userCatagory == UserCatagory.EXTERNAL.getValue()) {
			return;
		}
		filter.putAll(pageRequest.getMap());
		// 客户收货，客户订单状态必须为已接单状态
		filter.put("statusCd", UserOrderingState.RECEIVED.getValue());

		List<UserOrder> userOrderList = userOrderService.getUserOrderList(filter);
		List<Map<String, Object>> userOrderAndDetailInfoResultList = Lists.newArrayList();
		int userOrderCount = userOrderService.getUserOrderCount(filter);

		for (UserOrder userOrder : userOrderList) {
			String userOrderId = String.valueOf(userOrder.getUserOrderId());
			Map<String, Object> mapData = Maps.newHashMap();
			mapData.put("userOrderId", userOrderId);
			mapData.put("userName", systemVariableService.getUserByAreaIdData(areaId, userOrder.getUserId()));
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);

		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("productStates", VariableUtils.getVariables(ProductOrderState.class));
		model.addAttribute("billstates", VariableUtils.getVariables(BillStatus.class));
		model.addAttribute("offices", systemVariableService.getGroupByAreaIdData(areaId));
		model.addAttribute("salemens", accountService.findUserByAreaIdAndRole(areaId, "5"));
		model.addAttribute("page", page);

		// 查询条件
		String groupId = VariableUtils.typeCast(filter.get("groupId"));
		model.addAttribute("groupId", groupId);
		model.addAttribute("userId", filter.get("userId"));
		model.addAttribute("userOrderId", filter.get("userOrderId"));
		if (StringUtils.isNotEmpty(groupId)) {
			model.addAttribute("userInfoMap", accountService.findUserByOfficeId(groupId));
		}
	}

	/**
	 * 财务开具发票
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequestMapping(value = "invoiced", method = RequestMethod.POST)
	public String invoicedForProductOrder(@RequestParam Map<String, Object> filter,
			@RequestParam(required = false) List<String> productOrderIds) {

		String productOrderId = (String) filter.get("productOrderId");
		String saleId = String.valueOf(filter.get("saleId"));

		if (StringUtils.isNotEmpty(productOrderId)) {
			List<String> list = Lists.newArrayList();
			list.add(productOrderId + "~" + saleId);
			filter.put("productOrderList", list);
		} else {
			List<String> list = Lists.newArrayList();
			for (String productOrderIdTemp : productOrderIds) {
				list.add(productOrderIdTemp + "~" + saleId);
			}
			filter.put("productOrderList", list);
		}
		userOrderService.doProductOrderInvoiced(filter);
		return "redirect:/order/list/finance/invoiced_view";
	}

	/**
	 * 财务查看开完发票未结款的订单
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequiresPermissions("perms[order:list:invoiced_nopay]")
	@RequestMapping(value = "invoiced_nopay_view", method = RequestMethod.GET)
	public void getInvoicedNoPayViewForProductOrder(PageRequest pageRequest, @RequestParam Map<String, Object> filter,
			Model model) {

		String areaId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getAreaId(), String.class);
		Integer userCatagory = VariableUtils.typeCast(
				SessionVariable.getCurrentSessionVariable().getUser().get("userCatagory"), Integer.class);
		if (userCatagory == UserCatagory.EXTERNAL.getValue()) {
			return;
		}
		filter.putAll(pageRequest.getMap());
		// 客户收货，客户订单状态必须为已接单状态
		filter.put("statusCd", UserOrderingState.RECEIVED.getValue());

		List<UserOrder> userOrderList = userOrderService.getUserOrderList(filter);
		List<Map<String, Object>> userOrderAndDetailInfoResultList = Lists.newArrayList();
		int userOrderCount = userOrderService.getUserOrderCount(filter);

		for (UserOrder userOrder : userOrderList) {
			String userOrderId = String.valueOf(userOrder.getUserOrderId());
			Map<String, Object> mapData = Maps.newHashMap();
			mapData.put("userOrderId", userOrderId);
			mapData.put("userName", systemVariableService.getUserByAreaIdData(areaId, userOrder.getUserId()));
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);

		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("productStates", VariableUtils.getVariables(ProductOrderState.class));
		model.addAttribute("billstates", VariableUtils.getVariables(BillStatus.class));
		model.addAttribute("offices", systemVariableService.getGroupByAreaIdData(areaId));
		model.addAttribute("page", page);

		// 查询条件
		String groupId = VariableUtils.typeCast(filter.get("groupId"));
		model.addAttribute("groupId", groupId);
		model.addAttribute("userId", filter.get("userId"));
		model.addAttribute("userOrderId", filter.get("userOrderId"));
		if (StringUtils.isNotEmpty(groupId)) {
			model.addAttribute("userInfoMap", accountService.findUserByOfficeId(groupId));
		}
	}

	/**
	 * 财务已送发票待结款
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequestMapping(value = "invoiced_nopay", method = RequestMethod.POST)
	public String invoicedNopayForOrder(@RequestParam Map<String, Object> filter,
			@RequestParam(required = false) List<String> productOrderIds) {

		String productOrderId = (String) filter.get("productOrderId");
		if (StringUtils.isNotEmpty(productOrderId)) {
			List<String> list = Lists.newArrayList();
			list.add(productOrderId);
			filter.put("productOrderList", list);
		} else {
			filter.put("productOrderList", productOrderIds);
		}
		userOrderService.doProductOrderAlreadySend(filter);
		return "redirect:/order/list/finance/invoiced_nopay_view";
	}

	/**
	 * 财务查看已送发票未结款的订单
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequiresPermissions("perms[order:list:pay]")
	@RequestMapping(value = "pay_view", method = RequestMethod.GET)
	public void getPayViewForProductOrder(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		Integer userCatagory = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser()
				.get("userCatagory"));
		String areaId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getAreaId());

		if (userCatagory == UserCatagory.EXTERNAL.getValue()) {
			return;
		}

		filter.putAll(pageRequest.getMap());
		// 客户收货，客户订单状态必须为已接单状态
		filter.put("statusCd", UserOrderingState.RECEIVED.getValue());

		List<UserOrder> userOrderList = userOrderService.getUserOrderList(filter);
		List<Map<String, Object>> userOrderAndDetailInfoResultList = Lists.newArrayList();
		int userOrderCount = userOrderService.getUserOrderCount(filter);

		for (UserOrder userOrder : userOrderList) {
			String userOrderId = String.valueOf(userOrder.getUserOrderId());
			Map<String, Object> mapData = Maps.newHashMap();
			mapData.put("userOrderId", userOrderId);
			mapData.put("userName", systemVariableService.getUserByAreaIdData(areaId, userOrder.getUserId()));
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);

		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("productStates", VariableUtils.getVariables(ProductOrderState.class));
		model.addAttribute("billstates", VariableUtils.getVariables(BillStatus.class));
		model.addAttribute("offices", systemVariableService.getGroupByAreaIdData(areaId));
		model.addAttribute("page", page);

		// 查询条件
		String groupId = VariableUtils.typeCast(filter.get("groupId"));
		model.addAttribute("groupId", groupId);
		model.addAttribute("userId", filter.get("userId"));
		model.addAttribute("userOrderId", filter.get("userOrderId"));
		if (StringUtils.isNotEmpty(groupId)) {
			model.addAttribute("userInfoMap", accountService.findUserByOfficeId(groupId));
		}
	}

	/**
	 * 财务结款
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequiresPermissions("perms[order:list:pay]")
	@RequestMapping(value = "pay", method = RequestMethod.POST)
	public String payForOrder(@RequestParam Map<String, Object> filter,
			@RequestParam(required = false) List<String> userOrderIdAndProductOrderIds) {

		String userOrderAndProductOrderId = (String) filter.get("userOrderAndProductOrderId");

		if (StringUtils.isNotEmpty(userOrderAndProductOrderId)) {

			String userOrderId = StringUtils.substringBefore(userOrderAndProductOrderId, "_");
			String productOrderId = StringUtils.substringAfter(userOrderAndProductOrderId, "_");
			List<String> list = Lists.newArrayList();
			list.add(productOrderId);
			filter.put("productOrderList", list);
			filter.put("userOrderId", userOrderId);
			userOrderService.doProductOrderReceivables(filter);

		} else {

			Map<String, List<String>> mapData = Maps.newHashMap();
			for (String userOrderAndProductOrderIdTemp : userOrderIdAndProductOrderIds) {

				String userOrderId = StringUtils.substringBefore(userOrderAndProductOrderIdTemp, "_");
				String productOrderId = StringUtils.substringAfter(userOrderAndProductOrderIdTemp, "_");

				List<String> list = mapData.get(userOrderId);
				if (list == null || list.size() == 0) {
					list = Lists.newArrayList();
				}
				list.add(productOrderId);
				mapData.put(userOrderId, list);
			}

			for (Map.Entry<String, List<String>> entry : mapData.entrySet()) {
				String userOrderId = entry.getKey();
				List<String> productOrderIdList = entry.getValue();
				filter.put("userOrderId", userOrderId);
				filter.put("productOrderList", productOrderIdList);
				userOrderService.doProductOrderReceivables(filter);
			}
		}
		return "redirect:/order/list/finance/pay_view";
	}
}
