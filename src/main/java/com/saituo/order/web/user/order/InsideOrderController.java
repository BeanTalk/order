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
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);
		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("page", page);
		model.addAttribute("userName", SessionVariable.getCurrentSessionVariable().getUser().get("name"));

		String areaId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getAreaId(), String.class);
		model.addAttribute("offices", systemVariableService.getGroupIdAndNameCache(areaId));
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
			@RequestParam(required = false) List<Double> orderFees,
			@RequestParam(required = false) List<Double> disCountFees) {

		String productOrderId = (String) filter.get("productOrderId");
		// 产品订单编号~订购价格新~订购价格旧
		List<String> productOrderList = new ArrayList<String>();

		if (StringUtils.isNotEmpty(productOrderId)) {

			Double modifiedFee = Double.valueOf(String.valueOf(filter.get("modifiedFee")));
			Double disCountFee = Double.valueOf(String.valueOf(filter.get("disCountFee")));

			StringBuilder sb = new StringBuilder();
			sb.append(productOrderId).append("~").append(modifiedFee).append("~").append(disCountFee);
			productOrderList.add(sb.toString());

		} else {

			for (int i = 0; i < productOrderIds.size(); i++) {
				String productOrderIdTmp = productOrderIds.get(i);
				StringBuilder sb = new StringBuilder();
				sb.append(productOrderIdTmp).append("~").append(orderFees.get(i)).append("~")
						.append(disCountFees.get(i));
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
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);
		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("page", page);
		model.addAttribute("userName", SessionVariable.getCurrentSessionVariable().getUser().get("name"));

		String areaId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getAreaId(), String.class);
		model.addAttribute("offices", systemVariableService.getGroupIdAndNameCache(areaId));
		model.addAttribute("userIdAndNameMap", systemVariableService.getAllofUserIdAndNameByCache(areaId));
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
	 * 内勤查看接单状态的订单
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequiresPermissions("perms[order:list:load]")
	@RequestMapping(value = "load_view", method = RequestMethod.GET)
	public void getLoadViewOrder(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

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
			mapData.put("productStatusCd", 0);
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}

		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);
		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("productStates", VariableUtils.getVariables(ProductOrderState.class));
		model.addAttribute("page", page);
		String areaId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getAreaId(), String.class);
		model.addAttribute("offices", systemVariableService.getGroupIdAndNameCache(areaId));
		model.addAttribute("userIdAndNameMap", systemVariableService.getAllofUserIdAndNameByCache(areaId));
		model.addAttribute("salemens", accountService.findUserByAreaIdAndRole(areaId, "5"));
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
			@RequestParam(required = false) List<String> productOrderIds, @RequestParam String saleId) {

		Map<String, Object> mapData = Maps.newHashMap();
		List<String> list = Lists.newArrayList();

		for (String productOrderId : productOrderIds) {
			StringBuilder sb = new StringBuilder();
			sb.append(productOrderId).append("~").append(saleId);
			list.add(sb.toString());
			mapData.put("productOrderList", list);
			userOrderService.doProductOrderShipment(mapData);
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
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);
		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("productStates", VariableUtils.getVariables(ProductOrderState.class));
		model.addAttribute("page", page);
		String areaId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getAreaId(), String.class);
		model.addAttribute("offices", systemVariableService.getGroupIdAndNameCache(areaId));
		model.addAttribute("userIdAndNameMap", systemVariableService.getAllofUserIdAndNameByCache(areaId));
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

		String productOrderId = String.valueOf(filter.get("productOrderId"));

		if (StringUtils.isNotEmpty(productOrderId)) {
			List<String> list = Lists.newArrayList();
			list.add(productOrderId);
			filter.put("productOrderList", list);
		} else {
			filter.put("productOrderList", productOrderIds);
		}
		userOrderService.doProductOrderReceipt(filter);
		return "redirect:/order/list/inside/received_view";
	}

}
