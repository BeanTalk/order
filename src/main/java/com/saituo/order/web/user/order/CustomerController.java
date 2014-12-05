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
import com.saituo.order.service.user.AddressService;
import com.saituo.order.service.user.UserOrderService;
import com.saituo.order.service.variable.SystemVariableService;

@Controller
@SessionAttributes(SessionVariable.DEFAULT_SESSION_KEY)
@RequestMapping("order/list")
public class CustomerController {

	@Autowired
	private UserOrderService userOrderService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private SystemVariableService systemVariableService;

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
	@RequestMapping(value = "customer/save", method = RequestMethod.POST)
	public String customerSaveOrdering(@RequestParam Map<String, Object> filter, @RequestParam List<String> productIds,
			@RequestParam List<String> subscripts, Model model) {

		List<String> productOrderList = new ArrayList<String>();
		for (int i = 0; i < productIds.size(); i++) {
			StringBuilder sb = new StringBuilder(120);
			sb.append(productIds.get(i)).append("~").append(99.99).append("~").append(subscripts.get(0));
			productOrderList.add(sb.toString());
		}
		filter.put("productOrderList", productOrderList);
		userOrderService.doCreateUserOrder(filter);
		return "redirect:/order/list/customer/confirm_view";
	}

	/**
	 * 客户取消所选订单，目前不支持批量操作
	 * 
	 * @param filter
	 *            : productOrderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "customer/cancel", method = RequestMethod.POST)
	public String customerCancelOrdering(@RequestParam Map<String, Object> filter, Model model) {
		// XXX
		return "redirect:/order/list/customer/confirm_view";
	}

	/**
	 * 客户修改所选订单的产品数量，目前不支持批量操作
	 * 
	 * @param filter
	 *            : productOrderId, orderNum
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "customer/upgrade", method = RequestMethod.POST)
	public String customerUpgardeOrdering(@RequestParam Map<String, Object> filter, Model model) {
		// XXX
		return "redirect:/order/list/customer/confirm_view";
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
	@RequiresPermissions("perms[order:list:confirm]")
	@RequestMapping(value = "customer/confirm_view", method = RequestMethod.GET)
	public void customerQueryConfirmOrdering(PageRequest pageRequest, @RequestParam Map<String, Object> filter,
			Model model) {

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		filter.put("userId", userId);
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
	}

	/**
	 * 客户确认订单，在内勤修改完价格之后
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
	@RequestMapping(value = "customer/confirm", method = RequestMethod.POST)
	public String customerConfirmOrdering(@RequestParam Map<String, Object> filter,
			@RequestParam List<String> userOrders, Model model) {
		for (String userOrder : userOrders) {
			Map<String, Object> mapData = Maps.newHashMap();
			mapData.put("userOrderId", userOrder);
			userOrderService.doUpdateUserOrderStatus(mapData);
		}
		return "redirect:/order/list/customer/confirm_view";
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
	@RequiresPermissions("perms[order:list:approve]")
	@RequestMapping(value = "customer/approve_view", method = RequestMethod.GET)
	public void customerQueryApproveOrdering(PageRequest pageRequest, @RequestParam Map<String, Object> filter,
			Model model) {

		String groupId = SessionVariable.getCurrentSessionVariable().getGroupId();

		filter.putAll(pageRequest.getMap());
		// 客户订单状态必须为待审批状态
		filter.put("statusCd", UserOrderingState.PENDING.getValue());
		// 导师和PI可以看到该组下面所有学生提交的数据
		filter.put("groupId", groupId);

		List<UserOrder> userOrderList = userOrderService.getUserOrderList(filter);
		List<Map<String, Object>> userOrderAndDetailInfoResultList = Lists.newArrayList();
		int userOrderCount = userOrderService.getUserOrderCount(filter);

		for (UserOrder userOrder : userOrderList) {
			String userOrderId = String.valueOf(userOrder.getUserOrderId());
			Map<String, Object> mapData = Maps.newHashMap();
			mapData.put("userOrderId", userOrderId);
			mapData.put("userName", systemVariableService.getUserByOfficeIdData(groupId, userOrder.getUserId()));
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);
		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("page", page);
	}

	/**
	 * 导师审批
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
	@RequestMapping(value = "customer/approve", method = RequestMethod.POST)
	public String customerApproveOrdering(@RequestParam Map<String, Object> filter,
			@RequestParam(required = false) List<String> userOrderAndProductOrderIds, Model model) {

		Map<String, List<String>> mapData = Maps.newHashMap();

		for (String userOrderAndProductOrderId : userOrderAndProductOrderIds) {
			String userOrderId = StringUtils.substringBefore(userOrderAndProductOrderId, "_");
			String productOrderId = StringUtils.substringAfter(userOrderAndProductOrderId, "_");

			List<String> productOrderIdList = mapData.get(userOrderId);
			if (productOrderIdList == null) {
				productOrderIdList = Lists.newArrayList();
			}
			productOrderIdList.add(productOrderId);
			mapData.put(userOrderId, productOrderIdList);
		}

		String auditCd = String.valueOf(filter.get("auditCd"));

		for (Map.Entry<String, List<String>> entry : mapData.entrySet()) {
			String userOrderId = entry.getKey();
			List<String> productOrderIds = entry.getValue();

			List<String> productOrderInfoList = new ArrayList<String>();
			for (String productOrderId : productOrderIds) {
				StringBuilder sb = new StringBuilder();
				sb.append(productOrderId).append("~").append(auditCd).append("~~");
				productOrderInfoList.add(sb.toString());
			}
			filter.put("productOrderList", productOrderInfoList);
			filter.put("userOrderId", userOrderId);
			userOrderService.doAuditProductOrder(filter);
		}
		return "redirect:/order/list/customer/approve_view";
	}

	/**
	 * 审批未通过的订单查询
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
	@RequestMapping(value = "customer/reject", method = RequestMethod.GET)
	public String customerRejectOrdering(@RequestParam Map<String, Object> filter,
			@RequestParam List<String> productIds, @RequestParam List<String> subscripts, Model model) {

		List<String> productOrderList = new ArrayList<String>();
		for (int i = 0; i < productIds.size(); i++) {
			StringBuilder sb = new StringBuilder(120);
			sb.append(productIds.get(i)).append("~").append(99.99).append("~").append(subscripts.get(0));
			productOrderList.add(sb.toString());
		}
		filter.put("productOrderList", productOrderList);
		userOrderService.doAuditProductOrder(filter);
		return "redirect:/order/list/all_order";
	}

	/**
	 * 查看导师需要的下单的订单
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
	@RequiresPermissions("perms[order:list:book]")
	@RequestMapping(value = "customer/book_view", method = RequestMethod.GET)
	public void customerBookViewOrdering(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		String groupId = SessionVariable.getCurrentSessionVariable().getGroupId();
		filter.putAll(pageRequest.getMap());
		// 客户订单状态必须为待审批状态
		filter.put("statusCd", UserOrderingState.ACCEPTED.getValue());
		// 导师和PI可以看到该组下面所有学生提交的数据
		filter.put("groupId", groupId);

		List<UserOrder> userOrderList = userOrderService.getUserOrderList(filter);
		List<Map<String, Object>> userOrderAndDetailInfoResultList = Lists.newArrayList();
		int userOrderCount = userOrderService.getUserOrderCount(filter);

		for (UserOrder userOrder : userOrderList) {
			String userOrderId = String.valueOf(userOrder.getUserOrderId());
			Map<String, Object> mapData = Maps.newHashMap();
			mapData.put("userOrderId", userOrderId);
			mapData.put("userName", systemVariableService.getUserByOfficeIdData(groupId, userOrder.getUserId()));
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);
		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("page", page);
	}

	/**
	 * 导师下单
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
	@RequestMapping(value = "customer/book", method = RequestMethod.POST)
	public String customerBookOrdering(@RequestParam Map<String, Object> filter,
			@RequestParam List<String> userOrderIds, Model model) {

		for (String userOrderId : userOrderIds) {
			Map<String, Object> mapData = Maps.newHashMap();
			mapData.put("userOrderId", userOrderId);
			userOrderService.doUpdateUserOrderStatusFive(mapData);
		}
		return "redirect:/order/list/customer/book_view";
	}

	/**
	 * 根据所查订单状态，返回所有涉及到本人的订单信息
	 * 
	 * @param filter
	 * @param model
	 */
	@RequestMapping(value = "list/customer/orders", method = RequestMethod.GET)
	public void getOrderingListByStatus(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		filter.put("userId", userId);
		filter.putAll(pageRequest.getMap());

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

	}

	/**
	 * 查看所有的订单
	 * 
	 * @param filter
	 * @param model
	 */
	@RequestMapping(value = "all_order", method = RequestMethod.GET)
	public void getAllOrderingList(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		String areaId = SessionVariable.getCurrentSessionVariable().getAreaId();
		filter.putAll(pageRequest.getMap());

		// 根据用户的角色与类别，来区分用户能看到的订单
		filter = watchOrderListPipline(filter);

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
		model.addAttribute("page", page);
	}

	/**
	 * 根据用户的角色与类别，来区分用户能看到的订单
	 * 
	 * @param filter
	 * @return
	 */
	private Map<String, Object> watchOrderListPipline(Map<String, Object> filter) {

		Integer useCatagory = VariableUtils.typeCast(
				SessionVariable.getCurrentSessionVariable().getUser().get("userCatagory"), Integer.class);

		// 外部用户
		if (useCatagory == UserCatagory.EXTERNAL.getValue()) {
			List<String> roleSign = SessionVariable.getCurrentSessionVariable().getRoleList();
			// 当其为学生时,只能看到本人的订单
			if (roleSign.contains(RoleSign.STUDENT.getValue())) {
				String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
						String.class);
				filter.put("userId", userId);
			}
			// 当其为老师和PI时，能看到该组下面的所有的订单
			if (roleSign.contains(RoleSign.TEACHER.getValue()) || roleSign.contains(RoleSign.PI.getValue())) {
				filter.put("groupId", SessionVariable.getCurrentSessionVariable().getGroupId());
			}
		}

		// 内部用户可以看到本地市的所有的订单，在UserOrderService 中已经默认设置
		return filter;
	}
}
