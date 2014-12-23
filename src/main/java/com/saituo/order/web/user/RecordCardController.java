package com.saituo.order.web.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.service.order.ProductService;
import com.saituo.order.service.order.RecordCardService;
import com.saituo.order.service.user.AddressService;
import com.saituo.order.service.user.UserRecordService;

@Controller
@RequiresAuthentication
@RequestMapping("order/record")
public class RecordCardController {

	@Autowired
	private RecordCardService recordCardService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private UserRecordService userRecordService;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "addProductToBag/{productId}", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> addProductToBag(@PathVariable("productId") String productId) {

		Map<String, String> mapData = Maps.newHashMap();
		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		boolean isAlreayAdded = recordCardService.isAddedIntoBuyCard(userId, productId);
		if (isAlreayAdded) {
			mapData.put("msg", "had");
			return mapData;
		}
		recordCardService.putProductIntoBag(userId, productId);
		mapData.put("msg", "sccuess");
		return mapData;
	}

	@RequestMapping(value = "removeOneProductFromBag/{productId}", method = RequestMethod.GET)
	public String removeOneProductFromBag(@PathVariable("productId") String productId) {

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		recordCardService.removeProductListFromBuyCard(userId, productId);
		return "redirect:/order/record/list";
	}

	@RequestMapping(value = "batchremove", method = RequestMethod.POST)
	public String removeBatchProductFromBag(@RequestParam List<String> productIds, RedirectAttributes redirectAttributes) {

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);

		if (productIds == null || productIds.size() == 0) {
			return "redirect:/order/buycard/list";
		}
		String[] array = productIds.toArray(new String[productIds.size()]);
		recordCardService.removeProductListFromBuyCard(userId, array);
		return "redirect:/order/buycard/list";
	}

	@RequestMapping(value = "get/productconut", method = RequestMethod.POST)
	public @ResponseBody Object getProductCountInBag() {
		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		return recordCardService.countProductInBagAboutBuyCard(userId);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void getProductList(Model model) {
		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);

		model.addAttribute("customerOrderingList", recordCardService.getProductListFromBag(userId));
		model.addAttribute("addressList", addressService.queryList(userId));
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public void saveRecordInfo(@RequestParam Map<String, Object> filter, @RequestParam List<String> productIds,
			@RequestParam List<String> discountPrices, @RequestParam List<String> subscripts,
			@RequestParam List<String> supplys, Model model) {

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);

		List<String> productOrderList = new ArrayList<String>();
		for (int i = 0; i < productIds.size(); i++) {
			StringBuilder sb = new StringBuilder(120);
			sb.append(productIds.get(i)).append("~").append(discountPrices.get(i)).append("~")
					.append(subscripts.get(i)).append("~").append(supplys.get(i));
			productOrderList.add(sb.toString());
		}
		filter.put("productRecordList", productOrderList);
		// productRecordList : 产品编号~订购价格～数量~供应商id
		userRecordService.doCreateUserRecord(filter);
		recordCardService.removeProductListFromBuyCard(userId, productIds.toArray(new String[productIds.size()]));
		model.addAttribute("info", "保存成功");
	}

	// @RequestMapping(value = "record_view", method = RequestMethod.GET)
	// public String viewRecordInfo(@RequestParam Map<String, Object> filter,
	// Model model) {
	//
	// String userId =
	// VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
	// String.class);
	//
	// List<String> productOrderList = new ArrayList<String>();
	// for (int i = 0; i < productIds.size(); i++) {
	// StringBuilder sb = new StringBuilder(120);
	// sb.append(productIds.get(i)).append("~").append(discountPrices.get(i)).append("~")
	// .append(subscripts.get(i)).append("~").append(supplys.get(i));
	// productOrderList.add(sb.toString());
	// }
	// filter.put("productOrderList", productOrderList);
	// // productRecordList : 产品编号~订购价格～数量~供应商id
	// userRecordService.doCreateUserRecord(filter);
	// recordCardService.removeProductListFromBuyCard(userId,
	// productIds.toArray(new String[productIds.size()]));
	// return "redirect:/order/record/record_view";
	// }

}
