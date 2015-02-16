package com.saituo.order.web.user.order;

import java.util.ArrayList;
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
import com.saituo.order.commons.enumeration.entity.ProductOrderState;
import com.saituo.order.commons.enumeration.entity.RoleSign;
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
@RequestMapping("order/list/inside")
public class InsideOrderController {

	@Autowired
	private UserOrderService userOrderService;

	@Autowired
	private SystemVariableService systemVariableService;

	@Autowired
	private AccountService accountService;

	/**
	 * 内勤查看可议价订单
	 * <p>
	 * 1.当其拥有order:list:bargain 权限时，并且其为内部用户才能查看需要进行议价的订单。
	 * <p>
	 * 2.获取该账号的地市，获取该地市下,状态为保存修价的全部订单，进行批量处理。
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequiresPermissions(value = "perms[order:list:bargain]")
	@RequestMapping(value = "bargain_view", method = RequestMethod.GET)
	public void bargainViewOrderList(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		Integer areaId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getAreaId(), Integer.class);
		Integer userCatagory = VariableUtils.typeCast(
				SessionVariable.getCurrentSessionVariable().getUser().get("userCatagory"), Integer.class);
		if (userCatagory == UserCatagory.EXTERNAL.getValue()) {
			return;
		}
		filter.putAll(pageRequest.getMap());
		// 内勤议价，客户订单状态必须为保存状态
		filter.put("statusCd", UserOrderingState.HOLD.getValue());

		List<UserOrder> userOrderList = userOrderService.getUserOrderList(filter);
		List<Map<String, Object>> userOrderAndDetailInfoResultList = Lists.newArrayList();
		int userOrderCount = userOrderService.getUserOrderCount(filter);

		for (UserOrder userOrder : userOrderList) {
			String userOrderId = String.valueOf(userOrder.getUserOrderId());
			Map<String, Object> mapData = Maps.newHashMap();
			mapData.put("userOrderId", userOrderId);
			mapData.put(
					"userName",
					systemVariableService.getUserByAreaIdData(String.valueOf(areaId),
							String.valueOf(userOrder.getUserId())));
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);
		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("page", page);
		model.addAttribute("userName", SessionVariable.getCurrentSessionVariable().getUser().get("name"));
		model.addAttribute("offices", systemVariableService.getGroupNameByAreaIdAndGroupIdDataToShow(areaId));

		String groupId = VariableUtils.typeCast(filter.get("groupId"));
		model.addAttribute("groupId", groupId);
		model.addAttribute("userId", filter.get("userId"));
		model.addAttribute("userOrderId", filter.get("userOrderId"));
		model.addAttribute("startDate", filter.get("startDate"));
		model.addAttribute("endDate", filter.get("endDate"));
		if (StringUtils.isNotEmpty(groupId)) {
			model.addAttribute("userInfoMap", accountService.findUserByOfficeId(groupId));
		}
	}

	/**
	 * 内勤改价
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequestMapping(value = "bargain", method = RequestMethod.POST)
	public String bargainOrder(@RequestParam(required = false) Map<String, Object> filter,
			@RequestParam(required = false) List<String> productOrderIds,
			@RequestParam(required = false) List<Double> newcatalogFees,
			@RequestParam(required = false) List<Double> oldcatalogFees) {

		String productOrderId = (String) filter.get("productOrderId");
		// 产品订单编号~订购价格新~订购价格旧
		List<String> productOrderList = new ArrayList<String>();

		if (StringUtils.isNotEmpty(productOrderId)) {

			Double newOrderFee = Double.valueOf(String.valueOf(filter.get("newOrderFee")));
			Double oldOrderFee = Double.valueOf(String.valueOf(filter.get("oldOrderFee")));

			StringBuilder sb = new StringBuilder();
			sb.append(productOrderId).append("~").append(newOrderFee).append("~").append(oldOrderFee);
			productOrderList.add(sb.toString());

		} else {
			List<String> productIds = Lists.newArrayList();
			List<Double> newcatalogFeesList = Lists.newArrayList();
			List<Double> oldcatalogFeesList = Lists.newArrayList();

			for (int i = 0; i < productOrderIds.size(); i++) {
				String productStr = productOrderIds.get(i);
				String productId = StringUtils.substringBefore(productStr, "_");
				productIds.add(productId);
				int index = Integer.valueOf(StringUtils.substringAfter(productStr, "_"));
				newcatalogFeesList.add(newcatalogFees.get(index));
				oldcatalogFeesList.add(oldcatalogFees.get(index));
			}

			for (int i = 0; i < productIds.size(); i++) {
				String productOrderIdTmp = productIds.get(i);
				StringBuilder sb = new StringBuilder();
				sb.append(productOrderIdTmp).append("~").append(newcatalogFeesList.get(i)).append("~")
						.append(oldcatalogFeesList.get(i));
				productOrderList.add(sb.toString());
			}
		}

		filter.put("productOrderList", productOrderList);
		userOrderService.doUpdateProductOrderFee(filter);
		return "redirect:/order/list/inside/bargain_view";
	}

	/**
	 * 内勤接单
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequiresPermissions("perms[order:list:take]")
	@RequestMapping(value = "take_view", method = RequestMethod.GET)
	public void getTakeViewOrder(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		Integer areaId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getAreaId(), Integer.class);
		Integer userCatagory = VariableUtils.typeCast(
				SessionVariable.getCurrentSessionVariable().getUser().get("userCatagory"), Integer.class);
		if (userCatagory == UserCatagory.EXTERNAL.getValue()) {
			return;
		}
		filter.putAll(pageRequest.getMap());
		// 内勤接单，客户订单状态必须为已下单状态
		filter.put("statusCd", UserOrderingState.HAVEORDERED.getValue());

		List<UserOrder> userOrderList = userOrderService.getUserOrderList(filter);
		List<Map<String, Object>> userOrderAndDetailInfoResultList = Lists.newArrayList();
		int userOrderCount = userOrderService.getUserOrderCount(filter);

		for (UserOrder userOrder : userOrderList) {
			String userOrderId = String.valueOf(userOrder.getUserOrderId());
			Map<String, Object> mapData = Maps.newHashMap();
			mapData.put("userOrderId", userOrderId);
			mapData.put(
					"userName",
					systemVariableService.getUserByAreaIdData(String.valueOf(areaId),
							String.valueOf(userOrder.getUserId())));
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);
		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("page", page);
		model.addAttribute("userName", SessionVariable.getCurrentSessionVariable().getUser().get("name"));
		model.addAttribute("offices", systemVariableService.getGroupNameByAreaIdAndGroupIdDataToShow(areaId));

		String groupId = VariableUtils.typeCast(filter.get("groupId"));
		model.addAttribute("groupId", groupId);
		model.addAttribute("userId", filter.get("userId"));
		model.addAttribute("startDate", filter.get("startDate"));
		model.addAttribute("endDate", filter.get("endDate"));
		model.addAttribute("userOrderId", filter.get("userOrderId"));
		if (StringUtils.isNotEmpty(groupId)) {
			model.addAttribute("userInfoMap", accountService.findUserByOfficeId(groupId));
		}
	}

	/**
	 * 内勤接单
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequiresPermissions("perms[order:list:take]")
	@RequestMapping(value = "take", method = RequestMethod.POST)
	public String takeOrder(@RequestParam Map<String, Object> filter, @RequestParam List<String> userOrderIds) {

		Map<String, Object> userOrderMap = Maps.newHashMap();
		for (String userOrderId : userOrderIds) {
			userOrderMap.put("userOrderId", userOrderId);
			userOrderService.doUpdateUserOrderStatusAccept(userOrderMap);
		}
		return "redirect:/order/list/inside/take_view";
	}

	/**
	 * 内勤查看出单状态的订单
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequiresPermissions("perms[order:list:load]")
	@RequestMapping(value = "load_view", method = RequestMethod.GET)
	public void getLoadViewOrder(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		Integer areaId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getAreaId(), Integer.class);
		Integer userCatagory = VariableUtils.typeCast(
				SessionVariable.getCurrentSessionVariable().getUser().get("userCatagory"), Integer.class);
		if (userCatagory == UserCatagory.EXTERNAL.getValue()) {
			return;
		}

		filter.putAll(pageRequest.getMap());
		// 内勤出单，客户订单状态必须为已接单状态
		filter.put("statusCd", UserOrderingState.RECEIVED.getValue());

		List<UserOrder> userOrderList = userOrderService.getUserOrderList(filter);
		List<Map<String, Object>> userOrderAndDetailInfoResultList = Lists.newArrayList();
		int userOrderCount = userOrderService.getUserOrderCount(filter);

		for (UserOrder userOrder : userOrderList) {
			String userOrderId = String.valueOf(userOrder.getUserOrderId());
			Map<String, Object> mapData = Maps.newHashMap();
			mapData.put("userOrderId", userOrderId);
			mapData.put("productStatusCd", ProductOrderState.UNSETTLED.getValue());
			mapData.put(
					"userName",
					systemVariableService.getUserByAreaIdData(String.valueOf(areaId),
							String.valueOf(userOrder.getUserId())));
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}

		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);
		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("productStates", VariableUtils.getVariables(ProductOrderState.class));
		model.addAttribute("page", page);
		model.addAttribute("offices", systemVariableService.getGroupNameByAreaIdAndGroupIdDataToShow(areaId));
		model.addAttribute("salemens", accountService.findUserByAreaIdAndRole(areaId, RoleSign.SALE.getValue()));

		String groupId = VariableUtils.typeCast(filter.get("groupId"));
		model.addAttribute("groupId", groupId);
		model.addAttribute("userId", filter.get("userId"));
		model.addAttribute("startDate", filter.get("startDate"));
		model.addAttribute("endDate", filter.get("endDate"));
		model.addAttribute("userOrderId", filter.get("userOrderId"));
		if (StringUtils.isNotEmpty(groupId)) {
			model.addAttribute("userInfoMap", accountService.findUserByOfficeId(groupId));
		}
	}

	/**
	 * 内勤出单
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequestMapping(value = "load", method = RequestMethod.POST)
	public String loadOrder(@RequestParam Map<String, Object> filter,
			@RequestParam(required = false) List<String> productOrderIds, @RequestParam(required = false) String saleId) {

		Map<String, Object> mapData = Maps.newHashMap();
		List<String> list = Lists.newArrayList();

		String productOrderId = VariableUtils.typeCast(filter.get("productOrderId"));
		if (StringUtils.isNotEmpty(productOrderId)) {
			String saleManId = VariableUtils.typeCast(filter.get("saleManId"));
			StringBuilder sb = new StringBuilder();
			sb.append(productOrderId).append("~").append(saleManId);
			list.add(sb.toString());
			mapData.put("productOrderList", list);
			userOrderService.doProductOrderShipment(mapData);

		} else if (productOrderIds != null) {
			for (String productOrderIdTemp : productOrderIds) {
				StringBuilder sb = new StringBuilder();
				sb.append(productOrderIdTemp).append("~").append(saleId);
				list.add(sb.toString());
				mapData.put("productOrderList", list);
				userOrderService.doProductOrderShipment(mapData);
			}
		}

		return "redirect:/order/list/inside/load_view";
	}

	/**
	 * 内勤查看出单状态的订单
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequiresPermissions("perms[order:list:received]")
	@RequestMapping(value = "received_view", method = RequestMethod.GET)
	public void getReceivedViewOrder(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		Integer areaId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getAreaId());
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
			mapData.put("productStatusCd", ProductOrderState.DEALED.getValue());
			mapData.put(
					"userName",
					systemVariableService.getUserByAreaIdData(String.valueOf(areaId),
							String.valueOf(userOrder.getUserId())));
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);
		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("productStates", VariableUtils.getVariables(ProductOrderState.class));
		model.addAttribute("page", page);
		model.addAttribute("offices", systemVariableService.getGroupNameByAreaIdAndGroupIdDataToShow(areaId));

		String groupId = VariableUtils.typeCast(filter.get("groupId"));
		model.addAttribute("groupId", groupId);
		model.addAttribute("startDate", filter.get("startDate"));
		model.addAttribute("endDate", filter.get("endDate"));
		model.addAttribute("userId", filter.get("userId"));
		model.addAttribute("userOrderId", filter.get("userOrderId"));
		if (StringUtils.isNotEmpty(groupId)) {
			model.addAttribute("userInfoMap", accountService.findUserByOfficeId(groupId));
		}
	}

	/**
	 * 内勤确认用户收货
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequiresPermissions("perms[order:list:received]")
	@RequestMapping(value = "received", method = RequestMethod.POST)
	public String receivedOrder(@RequestParam Map<String, Object> filter,
			@RequestParam(required = false) List<String> productOrderIds) {

		String productOrderId = VariableUtils.typeCast(filter.get("productOrderId"), String.class);

		if (StringUtils.isNotEmpty(productOrderId)) {
			List<String> list = Lists.newArrayList();
			list.add(productOrderId);
			filter.put("productOrderList", list);
		} else {
			if (productOrderIds == null) {
				return "redirect:/order/list/inside/received_view";
			}
			filter.put("productOrderList", productOrderIds);
		}
		userOrderService.doProductOrderReceipt(filter);
		return "redirect:/order/list/inside/received_view";
	}

	/**
	 * 内勤退货
	 * 
	 * @param filter
	 * @param productIds
	 * @return
	 */
	@RequestMapping(value = "return", method = RequestMethod.POST)
	public String returnOrder(@RequestParam Map<String, Object> filter) {

		String productOrderId = VariableUtils.typeCast(filter.get("productOrderId"), String.class);
		List<String> list = Lists.newArrayList();
		list.add(productOrderId);
		filter.put("productOrderList", list);

		userOrderService.doProductOrderReturn(filter);
		return "redirect:/order/list/all_order";
	}

}
