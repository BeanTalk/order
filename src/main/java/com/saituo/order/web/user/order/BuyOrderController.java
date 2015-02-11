package com.saituo.order.web.user.order;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.commons.enumeration.entity.RoleSign;
import com.saituo.order.commons.enumeration.entity.StockStatus;
import com.saituo.order.commons.page.Page;
import com.saituo.order.commons.page.PageRequest;
import com.saituo.order.entity.stock.AgainAllot;
import com.saituo.order.entity.stock.StockProductOrder;
import com.saituo.order.service.account.AccountService;
import com.saituo.order.service.stock.StockOrderService;
import com.saituo.order.service.variable.SystemVariableService;

@Controller
@SessionAttributes(SessionVariable.DEFAULT_SESSION_KEY)
@RequestMapping("order/list/buy")
public class BuyOrderController {

	@Autowired
	private StockOrderService stockOrderService;

	@Autowired
	private SystemVariableService systemVariableService;

	@Autowired
	private AccountService accountService;

	/**
	 * 已采购
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView waitViewStockOrder(PageRequest pageRequest, @RequestParam Map<String, Object> filter) {

		filter.putAll(pageRequest.getMap());
		filter.put("statusCd", 2);
		Integer buyerId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				Integer.class);

		filter.put("buyerId", buyerId);
		Map<String, Object> mapData = stockOrderService.getbuyerQueryList(filter);

		List<StockProductOrder> stockProductOrderList = (List<StockProductOrder>) mapData.get("stockProductOrderList");
		long stockProductOrderCount = stockOrderService.getbuyerQueryCount(filter);

		Page<StockProductOrder> page = new Page<StockProductOrder>(pageRequest, stockProductOrderList,
				stockProductOrderCount);

		ModelAndView modelAndView = new ModelAndView("order/list/buy/waitbuy_view");
		modelAndView.addObject("areaIdAndName", systemVariableService.getAreaIdAndName());
		modelAndView.addObject("userIdAndName", systemVariableService.getUserIdAndName());
		modelAndView.addObject("supplyIdAndName", systemVariableService.getSupplyIdAndName());
		modelAndView.addObject("page", page);

		Integer areaId = SessionVariable.getCurrentSessionVariable().getAreaId();
		modelAndView
				.addObject("userInfoMap", accountService.findUserByAreaIdAndRole(areaId, RoleSign.BUYER.getValue()));
		modelAndView.addObject("supplierIds", systemVariableService.getSupplyIdAndName());
		modelAndView.addObject("buyers", accountService.findUserByAreaIdAndRole(null, RoleSign.BUYER.getValue()));
		modelAndView.addObject("status", VariableUtils.getVariables(StockStatus.class));
		modelAndView.addObject(filter);

		return modelAndView;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveStockNumber(@RequestParam(required = false) Map<String, Object> filter,
			@RequestParam(required = false) List<String> stockNumbers) {

		String stockNumber = VariableUtils.typeCast(filter.get("stockNumber"), String.class);

		List<String> stockNumberList = Lists.newArrayList();
		if (StringUtils.isNotEmpty(stockNumber)) {
			stockNumberList.add(stockNumber);
		} else {
			stockNumberList = stockNumbers;
		}

		filter.put("statusCd", 3);
		filter.put("stockProductOrderList", stockNumberList);
		stockOrderService.doUpdatePurchased(filter);
		return "redirect:/order/list/buy/list";
	}

	@RequestMapping(value = "reject", method = RequestMethod.POST)
	public String rejectStockNumber(@RequestParam(required = false) Map<String, Object> filter) {

		String stockNumber = VariableUtils.typeCast(filter.get("reject_stock_number"), String.class);
		String reason = VariableUtils.typeCast(filter.get("redistribution_note"), String.class);

		StringBuilder sb = new StringBuilder();
		sb.append(stockNumber).append("~").append(reason);

		List<String> stockNumberList = Lists.newArrayList();
		stockNumberList.add(sb.toString());

		filter.put("statusCd", 1);
		filter.put("stockProductOrderList", stockNumberList);
		stockOrderService.doUpdatePurchased(filter);

		return "redirect:/order/list/buy/list";
	}

	/**
	 * 待入库
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequestMapping(value = "storage_view", method = RequestMethod.GET)
	public ModelAndView storageViewStockOrder(PageRequest pageRequest, @RequestParam Map<String, Object> filter) {

		filter.put("statusCd", 3);
		filter.putAll(pageRequest.getMap());
		Integer buyerId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				Integer.class);

		filter.put("buyerId", buyerId);
		Map<String, Object> mapData = stockOrderService.getbuyerQueryList(filter);

		List<StockProductOrder> stockProductOrderList = (List<StockProductOrder>) mapData.get("stockProductOrderList");
		long stockProductOrderCount = stockOrderService.getbuyerQueryCount(filter);

		Page<StockProductOrder> page = new Page<StockProductOrder>(pageRequest, stockProductOrderList,
				stockProductOrderCount);

		ModelAndView modelAndView = new ModelAndView("order/list/buy/storage_view");
		modelAndView.addObject("areaIdAndName", systemVariableService.getAreaIdAndName());
		modelAndView.addObject("userIdAndName", systemVariableService.getUserIdAndName());
		modelAndView.addObject("supplyIdAndName", systemVariableService.getSupplyIdAndName());
		modelAndView.addObject("page", page);

		Integer areaId = SessionVariable.getCurrentSessionVariable().getAreaId();
		modelAndView
				.addObject("userInfoMap", accountService.findUserByAreaIdAndRole(areaId, RoleSign.BUYER.getValue()));
		modelAndView.addObject("supplierIds", systemVariableService.getSupplyIdAndName());
		modelAndView.addObject("buyers", accountService.findUserByAreaIdAndRole(null, RoleSign.BUYER.getValue()));
		modelAndView.addObject("status", VariableUtils.getVariables(StockStatus.class));
		modelAndView.addObject(filter);

		return modelAndView;
	}

	/**
	 * 待入库
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequestMapping(value = "storage", method = RequestMethod.POST)
	public String storageStockOrder(@RequestParam(required = false) Map<String, Object> filter,
			@RequestParam(required = false) List<String> stockNumbers) {

		String stockNumber = VariableUtils.typeCast(filter.get("stockNumber"), String.class);

		List<String> stockNumberList = Lists.newArrayList();
		if (StringUtils.isNotEmpty(stockNumber)) {
			stockNumberList.add(stockNumber);
		} else {
			stockNumberList = stockNumbers;
		}

		filter.put("stockProductOrderList", stockNumberList);
		stockOrderService.doUpdateStorage(filter);
		return "redirect:/order/list/buy/list";
	}

	/**
	 * 采购订单查询
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequestMapping(value = "query_view", method = RequestMethod.GET)
	public void queryStockOrder(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		filter.putAll(pageRequest.getMap());
		Integer buyerId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				Integer.class);
		filter.put("buyerId", buyerId);
		Map<String, Object> mapData = stockOrderService.getbuyerQueryList(filter);
		List<StockProductOrder> stockProductOrderList = (List<StockProductOrder>) mapData.get("stockProductOrderList");
		long stockProductOrderCount = stockOrderService.getbuyerQueryCount(filter);

		Page<StockProductOrder> page = new Page<StockProductOrder>(pageRequest, stockProductOrderList,
				stockProductOrderCount);

		model.addAttribute("status", VariableUtils.getVariables(StockStatus.class));
		model.addAttribute("areaIdAndName", systemVariableService.getAreaIdAndName());
		model.addAttribute("userIdAndName", systemVariableService.getUserIdAndName());
		model.addAttribute("supplyIdAndName", systemVariableService.getSupplyIdAndName());
		model.addAttribute("page", page);

		Integer areaId = SessionVariable.getCurrentSessionVariable().getAreaId();
		model.addAttribute("userInfoMap", accountService.findUserByAreaIdAndRole(areaId, RoleSign.BUYER.getValue()));
		model.addAttribute("supplierIds", systemVariableService.getSupplyIdAndName());
		model.addAttribute("buyers", accountService.findUserByAreaIdAndRole(null, RoleSign.BUYER.getValue()));
		model.addAllAttributes(filter);

	}

	/**
	 * 采购订单查询
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequestMapping(value = "distribute_view", method = RequestMethod.GET)
	public void distributeViewStockOrder(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		filter.putAll(pageRequest.getMap());
		Map<String, Object> mapData = stockOrderService.getManagerAllotTask(filter);
		long stockProductOrderCount = stockOrderService.getManagerCountTask(filter);
		List<StockProductOrder> stockProductOrderList = (List<StockProductOrder>) mapData.get("stockProductOrderList");

		Page<StockProductOrder> page = new Page<StockProductOrder>(pageRequest, stockProductOrderList,
				stockProductOrderCount);

		model.addAttribute("areaIdAndName", systemVariableService.getAreaIdAndName());
		model.addAttribute("userIdAndName", systemVariableService.getUserIdAndName());
		model.addAttribute("supplyIdAndName", systemVariableService.getSupplyIdAndName());
		model.addAttribute("page", page);

		Integer areaId = SessionVariable.getCurrentSessionVariable().getAreaId();
		model.addAttribute("userInfoMap", accountService.findUserByAreaIdAndRole(areaId, RoleSign.BUYER.getValue()));
		model.addAttribute("supplierIds", systemVariableService.getSupplyIdAndName());
		model.addAttribute("buyers", accountService.findUserByAreaIdAndRole(null, RoleSign.BUYER.getValue()));
		model.addAttribute("states", VariableUtils.getVariables(StockStatus.class));
		model.addAllAttributes(filter);
	}

	/**
	 * 采购订单查询
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequestMapping(value = "distribute", method = RequestMethod.POST)
	public String distributeStockOrder(@RequestParam Map<String, Object> filter, Model model) {

		String stockNumber = VariableUtils.typeCast(filter.get("dis_stock_number"), String.class);
		List<String> stockProductOrderList = Lists.newArrayList();
		stockProductOrderList.add(stockNumber);
		filter.put("stockProductOrderList", stockProductOrderList);
		stockOrderService.doManagerAllotTask(filter);
		return "redirect:/order/list/buy/distribute_view";
	}

	@RequestMapping(value = "getreason", method = RequestMethod.GET)
	public @ResponseBody List<AgainAllot> getReasonByStockNumber(@RequestParam Map<String, Object> filter) {
		String stockNumber = VariableUtils.typeCast(filter.get("stockNumber"), String.class);
		List<AgainAllot> againAllotList = stockOrderService.getAgainAllotQuery(stockNumber);
		for (AgainAllot againAllot : againAllotList) {
			againAllot.setAcceptPerson(systemVariableService.getUserIdAndName().get(againAllot.getAcceptPerson()));
		}
		return againAllotList;
	}

	/**
	 * 采购订单查询
	 * 
	 * @param filter
	 * @param productIds
	 * @param subscripts
	 * @return
	 */
	@RequestMapping(value = "managed_view", method = RequestMethod.GET)
	public void managedViewStockOrder(PageRequest pageRequest, @RequestParam Map<String, Object> filter, Model model) {

		filter.putAll(pageRequest.getMap());
		Map<String, Object> mapData = stockOrderService.getbuyerQueryList(filter);
		long stockProductOrderCount = stockOrderService.getbuyerQueryCount(filter);
		List<StockProductOrder> stockProductOrderList = (List<StockProductOrder>) mapData.get("stockProductOrderList");

		Page<StockProductOrder> page = new Page<StockProductOrder>(pageRequest, stockProductOrderList,
				stockProductOrderCount);

		model.addAttribute("areaIdAndName", systemVariableService.getAreaIdAndName());
		model.addAttribute("userIdAndName", systemVariableService.getUserIdAndName());
		model.addAttribute("supplyIdAndName", systemVariableService.getSupplyIdAndName());
		model.addAttribute("page", page);

		Integer areaId = SessionVariable.getCurrentSessionVariable().getAreaId();
		model.addAttribute("userInfoMap", accountService.findUserByAreaIdAndRole(areaId, RoleSign.BUYER.getValue()));
		model.addAttribute("supplierIds", systemVariableService.getSupplyIdAndName());
		model.addAttribute("buyers", accountService.findUserByAreaIdAndRole(null, RoleSign.BUYER.getValue()));
		model.addAttribute("status", VariableUtils.getVariables(StockStatus.class));
		model.addAllAttributes(filter);
	}

}
