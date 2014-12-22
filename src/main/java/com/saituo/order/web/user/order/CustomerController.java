package com.saituo.order.web.user.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.saituo.order.entity.order.Product;
import com.saituo.order.entity.user.Audit;
import com.saituo.order.entity.user.AuditHis;
import com.saituo.order.entity.user.UserOrder;
import com.saituo.order.service.account.AccountService;
import com.saituo.order.service.order.BuyCardService;
import com.saituo.order.service.order.ProductBrandService;
import com.saituo.order.service.order.ProductService;
import com.saituo.order.service.user.AddressService;
import com.saituo.order.service.user.AuditHisService;
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
	@RequiresPermissions("perms[order:list:confirm]")
	@RequestMapping(value = "customer/save", method = RequestMethod.POST)
	public String customerSaveOrdering(@RequestParam Map<String, Object> filter, @RequestParam List<String> productIds,
			@RequestParam List<String> subscripts, Model model) {

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		List<String> productOrderList = new ArrayList<String>();
		List<Product> products = productService.getProductInfoListByProductId(productIds);

		Map<String, String> productPriceMap = Maps.newHashMap();
		for (Product product : products) {
			productPriceMap.put(String.valueOf(product.getProductId()), String.valueOf(product.getCatalogFee()));
		}

		for (int i = 0; i < productIds.size(); i++) {
			StringBuilder sb = new StringBuilder(120);
			String productId = productIds.get(i);
			sb.append(productId).append("~").append(productPriceMap.get(productId)).append("~")
					.append(subscripts.get(0));
			productOrderList.add(sb.toString());
		}
		filter.put("productOrderList", productOrderList);
		userOrderService.doCreateUserOrder(filter);
		buyCardService.removeProductListFromBuyCard(userId, productIds.toArray(new String[productIds.size()]));
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
		model.addAttribute("startDate", filter.get("startDate"));
		model.addAttribute("endDate", filter.get("endDate"));
		model.addAttribute("userOrderId", filter.get("userOrderId"));
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
	@RequiresPermissions("perms[order:list:confirm]")
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
	@RequiresPermissions("perms[order:list:upgrade]")
	@RequestMapping(value = "customer/upgrade_view", method = RequestMethod.GET)
	public void customerQueryUpardeOrdering(PageRequest pageRequest, @RequestParam Map<String, Object> filter,
			Model model) {

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		filter.put("userId", userId);
		filter.putAll(pageRequest.getMap());
		// 内勤议价，客户订单状态必须为待审批状态
		List<Integer> mutilStatusCdList = Lists.newArrayList();
		mutilStatusCdList.add(UserOrderingState.REJECTED.getValue());
		mutilStatusCdList.add(UserOrderingState.HOLD.getValue());
		filter.put("multiStatusCd", mutilStatusCdList);

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
		model.addAttribute("startDate", filter.get("startDate"));
		model.addAttribute("endDate", filter.get("endDate"));
		model.addAttribute("userOrderId", filter.get("userOrderId"));
	}

	@RequiresPermissions("perms[order:list:upgrade]")
	@RequestMapping(value = "customer/get_rejectinfo", method = RequestMethod.GET)
	public @ResponseBody AuditHis customerGetRejectInfo(@RequestParam Map<String, Object> filter) {
		String productOrderId = VariableUtils.typeCast(filter.get("productOrderId"), String.class);
		return auditHisService.getAuditHisByProductOrderId(productOrderId);
	}

	/**
	 * 客户修改产品订单
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
	@RequiresPermissions(value = {"perms[order:list:confirm]", "perms[order:list:upgrade]"}, logical = Logical.OR)
	@RequestMapping(value = "customer/upgrade", method = RequestMethod.POST)
	public String customerUpgardProductOrdering(@RequestParam Map<String, Object> filter, Model model) {

		String userOrderAndProductOrderId = (String) filter.get("userOrderAndProductOrderId");
		// 该方法两个地方使用,一个是confirm_view 一个是upgrade_view 使用.
		// confirm_view 时传入if_userOrder: false; 为不需要更新userOrder得状态
		// upgrade_view 时传入if_userOrder: true; 需要更新userOrder得状态
		Boolean ifUpdateUserOrder = VariableUtils.typeCast(filter.get("if_userOrder"), Boolean.class);

		if (ifUpdateUserOrder) {
			String userOrderId = StringUtils.substringBefore(userOrderAndProductOrderId, "_");
			filter.put("userOrderId", userOrderId);
		}

		String productOrderId = StringUtils.substringAfter(userOrderAndProductOrderId, "_");
		String orderNum = (String) filter.get("orderNum");
		List<String> list = Lists.newArrayList();
		list.add(productOrderId + "~" + orderNum);
		filter.put("productOrderModifyList", list);

		userOrderService.doModifyUserOrder(filter);

		model.addAttribute("userName",
				VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("name")));

		String redirectUrl = "";
		// 跳转到upgrade_view页面
		if (ifUpdateUserOrder) {
			redirectUrl = "redirect:/order/list/customer/upgrade_view";
			// confirm_view
		} else {
			redirectUrl = "redirect:/order/list/customer/confirm_view";
		}
		return redirectUrl;
	}

	/**
	 * 客户取消产品订单
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
	@RequiresPermissions(value = {"perms[order:list:confirm]", "perms[order:list:upgrade]"}, logical = Logical.OR)
	@RequestMapping(value = "customer/cancel", method = RequestMethod.POST)
	public String customerCancelProductOrdering(@RequestParam Map<String, Object> filter, Model model) {

		String userOrderAndProductOrderId = (String) filter.get("userOrderAndProductOrderId");

		Boolean ifUpdateUserOrder = VariableUtils.typeCast(filter.get("if_userOrder"), Boolean.class);

		if (ifUpdateUserOrder) {
			String userOrderId = StringUtils.substringBefore(userOrderAndProductOrderId, "_");
			filter.put("userOrderId", userOrderId);
		}
		String productOrderId = StringUtils.substringAfter(userOrderAndProductOrderId, "_");
		List<String> list = Lists.newArrayList();
		list.add(productOrderId);
		filter.put("productOrderDeleteList", list);
		userOrderService.doModifyUserOrder(filter);
		model.addAttribute("userName",
				VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("name")));

		String redirectUrl = "";
		// 跳转到upgrade_view页面
		if (ifUpdateUserOrder) {
			redirectUrl = "redirect:/order/list/customer/upgrade_view";
			// confirm_view
		} else {
			redirectUrl = "redirect:/order/list/customer/confirm_view";
		}
		return redirectUrl;
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
	@RequiresPermissions("perms[order:list:userordercancel]")
	@RequestMapping(value = "customer/userordercancel_view", method = RequestMethod.GET)
	public void customerQueryApprovingOrdering(PageRequest pageRequest, @RequestParam Map<String, Object> filter,
			Model model) {

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		filter.put("userId", userId);
		filter.putAll(pageRequest.getMap());
		// 待审批、已驳回、已审批、已下单 可以取消订单
		List<Integer> mutilStatusCdList = Lists.newArrayList();
		mutilStatusCdList.add(UserOrderingState.PENDING.getValue());
		mutilStatusCdList.add(UserOrderingState.HOLD.getValue());
		mutilStatusCdList.add(UserOrderingState.REJECTED.getValue());
		mutilStatusCdList.add(UserOrderingState.HAVEORDERED.getValue());
		filter.put("multiStatusCd", mutilStatusCdList);

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
		model.addAttribute("startDate", filter.get("startDate"));
		model.addAttribute("endDate", filter.get("endDate"));
		model.addAttribute("userOrderId", filter.get("userOrderId"));
	}

	/**
	 * 客户取消产品订单
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
	@RequiresPermissions("perms[order:list:userordercancel]")
	@RequestMapping(value = "customer/userordercancel", method = RequestMethod.POST)
	public String customerCancelUserOrdering(@RequestParam Map<String, Object> filter,
			@RequestParam List<String> userOrderIds, Model model) {

		for (String userOrderId : userOrderIds) {
			Map<String, Object> mapData = Maps.newHashMap();
			mapData.put("userOrderId", userOrderId);
			userOrderService.doUpdateUserOrderStatusCancel(mapData);
		}
		return "redirect:/order/list/customer/userordercancel_view";
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

		Integer groupId = SessionVariable.getCurrentSessionVariable().getGroupId();

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
			mapData.put(
					"userName",
					systemVariableService.getUserByOfficeIdData(String.valueOf(groupId),
							String.valueOf(userOrder.getUserId())));
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);
		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("page", page);
		model.addAttribute("userId", filter.get("userId"));
		model.addAttribute("startDate", filter.get("startDate"));
		model.addAttribute("endDate", filter.get("endDate"));
		model.addAttribute("userOrderId", filter.get("userOrderId"));
		model.addAttribute("userInfoMap", accountService.findUserByOfficeId(String.valueOf(groupId)));
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
	@RequiresPermissions("perms[order:list:approve]")
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

			List<Audit> auditInfoList = new ArrayList<Audit>();
			for (String productOrderId : productOrderIds) {
				Audit audit = new Audit();
				audit.setProductOrderId(productOrderId);
				audit.setAuditStatus(auditCd);
				audit.setTurnDownNote("");
				audit.setTurnDownReason("");
				auditInfoList.add(audit);
			}
			filter.put("auditInfoList", auditInfoList);
			filter.put("userOrderId", userOrderId);
			userOrderService.doAuditProductOrder(filter);
		}
		return "redirect:/order/list/customer/approve_view";
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
	@RequiresPermissions("perms[order:list:approve]")
	@RequestMapping(value = "customer/reject", method = RequestMethod.POST)
	public String customerRejectOrdering(@RequestParam Map<String, Object> filter, Model model) {

		String turnDownReason = VariableUtils.typeCast(filter.get("turn_down_reason"));
		String turnDownNote = VariableUtils.typeCast(filter.get("turn_down_note"));
		String userAndProductOrderid = VariableUtils.typeCast(filter.get("reject_userAndProductOrderid"));

		String userOrderId = StringUtils.substringBefore(userAndProductOrderid, "_");
		String productOrderId = StringUtils.substringAfter(userAndProductOrderid, "_");

		List<Audit> auditInfoList = Lists.newArrayList();
		Audit audit = new Audit();
		audit.setAuditStatus("2"); // 已驳回
		audit.setProductOrderId(productOrderId);
		audit.setTurnDownNote(turnDownNote);
		audit.setTurnDownReason(turnDownReason);
		auditInfoList.add(audit);

		filter.put("auditInfoList", auditInfoList);
		filter.put("userOrderId", userOrderId);
		userOrderService.doAuditProductOrder(filter);

		return "redirect:/order/list/customer/approve_view";
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

		Integer groupId = SessionVariable.getCurrentSessionVariable().getGroupId();
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
			mapData.put(
					"userName",
					systemVariableService.getUserByOfficeIdData(String.valueOf(groupId),
							String.valueOf(userOrder.getUserId())));
			userOrderAndDetailInfoResultList.add(userOrderService.getDeatilOrderInfo(mapData));
		}
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageRequest, userOrderAndDetailInfoResultList,
				userOrderCount);
		model.addAttribute("states", VariableUtils.getVariables(UserOrderingState.class));
		model.addAttribute("page", page);
		model.addAttribute("userId", filter.get("userId"));
		model.addAttribute("startDate", filter.get("startDate"));
		model.addAttribute("endDate", filter.get("endDate"));
		model.addAttribute("userOrderId", filter.get("userOrderId"));
		model.addAttribute("userInfoMap", accountService.findUserByOfficeId(String.valueOf(groupId)));
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
	@RequiresPermissions("perms[order:list:book]")
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
	 * 查看所有的订单
	 * 
	 * @param filter
	 * @param model
	 */
	@RequestMapping(value = "all_order", method = RequestMethod.GET)
	public void getAllOrderingList(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		Integer areaId = SessionVariable.getCurrentSessionVariable().getAreaId();
		filter.putAll(pageRequest.getMap());

		// 根据用户的角色与类别，来区分用户能看到的订单
		filter = watchOrderListPipline(filter, model);

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
		model.addAttribute("statusCd", filter.get("statusCd"));
		model.addAttribute("productStates", VariableUtils.getVariables(ProductOrderState.class));
		model.addAttribute("page", page);
		model.addAttribute("startDate", filter.get("startDate"));
		model.addAttribute("endDate", filter.get("endDate"));
	}

	/**
	 * 根据用户的角色与类别，来区分用户能看到的订单
	 * 
	 * @param filter
	 * @return
	 */
	private Map<String, Object> watchOrderListPipline(Map<String, Object> filter, Model model) {

		Integer useCatagory = VariableUtils.typeCast(
				SessionVariable.getCurrentSessionVariable().getUser().get("userCatagory"), Integer.class);

		// 外部用户
		if (UserCatagory.EXTERNAL.getValue().equals(useCatagory)) {
			List<String> roleSign = SessionVariable.getCurrentSessionVariable().getRoleList();
			// 当其为学生时,只能看到本人的订单
			if (roleSign.contains(String.valueOf(RoleSign.STUDENT.getValue()))) {
				String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
						String.class);
				filter.put("userId", userId);
				model.addAttribute("role", "student");
			}
			// 当其为老师和PI时，能看到该组下面的所有的订单
			if (roleSign.contains(String.valueOf(RoleSign.TEACHER.getValue())) || roleSign.contains(String.valueOf(RoleSign.PI.getValue()))) {
				filter.put("groupId", String.valueOf(SessionVariable.getCurrentSessionVariable().getGroupId()));
				model.addAttribute("userInfoMap", accountService.findUserByOfficeId(String.valueOf(SessionVariable
						.getCurrentSessionVariable().getGroupId())));
				model.addAttribute("role", "teacher");
			}
		// 内部用户
		} else {
			model.addAttribute("userInfoMap", accountService.findUserByOfficeId(String.valueOf(SessionVariable
					.getCurrentSessionVariable().getGroupId())));
			model.addAttribute("role", "internal");
		}

		model.addAttribute("userOrderId", filter.get("userOrderId"));
		model.addAttribute("offices",
				systemVariableService.getGroupNameByAreaIdAndGroupIdData(SessionVariable.getCurrentSessionVariable().getAreaId()));
		model.addAttribute("groupId", VariableUtils.typeCast(filter.get("groupId")));
		// 内部用户可以看到本地市的所有的订单，在UserOrderService 中已经默认设置
		return filter;
	}
}
