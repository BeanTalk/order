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
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.entity.order.Product;
import com.saituo.order.service.order.ProductService;
import com.saituo.order.service.order.StockCardService;
import com.saituo.order.service.stock.StockOrderService;
import com.saituo.order.service.user.AddressService;
import com.saituo.order.service.user.UserOrderService;

@Controller
@RequiresAuthentication
@RequestMapping("order/stock")
public class StockCardController {

	@Autowired
	private StockCardService stockCardService;

	@Autowired
	private StockOrderService stockOrderService;

	@Autowired
	private UserOrderService userOrderService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private ProductService productService;

	/**
	 * 在内勤出单中，点击备货按钮处理逻辑
	 * <p>
	 * 跳转到该客户订单下的产品订单列表
	 * 
	 * @param userOrderId
	 * @return
	 */
	@RequestMapping(value = "userOrder/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam String userOrderId) {

		List<Product> productList = userOrderService.getProductList(Long.parseLong(userOrderId));
		ModelAndView modelAndView = new ModelAndView("order/stock/stock_temp_list");
		modelAndView.addObject(productList);
		return modelAndView;
	}

	/**
	 * 获取所备货的产品
	 * <p>
	 * 跳转到该客户订单下的产品订单列表
	 * 
	 * @param userOrderId
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView getStockProductList() {
		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		ModelAndView modelAndView = new ModelAndView("order/stock/stock_product_list");
		modelAndView.addObject("customerOrderingList", stockCardService.getProductListFromBag(userId));
		modelAndView.addObject("addressList", addressService.queryList(userId));
		modelAndView.addObject("suuplys", stockCardService.getSupplyMap());
		return modelAndView;
	}

	@RequestMapping(value = "addProductToStock/{productId}", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> addProductToBag(@PathVariable("productId") String productId) {

		Map<String, String> mapData = Maps.newHashMap();
		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		boolean isAlreayAdded = stockCardService.isAddedIntoBuyCard(userId, productId);
		if (isAlreayAdded) {
			mapData.put("msg", "had");
			return mapData;
		}
		stockCardService.putProductIntoBag(userId, productId);
		mapData.put("msg", "sccuess");
		return mapData;
	}

	@RequestMapping(value = "get/productconut", method = RequestMethod.POST)
	public @ResponseBody Object getProductCountInBag() {
		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);
		return stockCardService.countProductInBagAboutBuyCard(userId);
	}

	@RequestMapping(value = "removeOneProductFromBag/{productId}", method = RequestMethod.GET)
	public String removeOneProductFromBag(@PathVariable("productId") String productId) {

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);

		stockCardService.removeProductListFromBuyCard(userId, productId);
		return "redirect:/order/stock/list";
	}

	@RequestMapping(value = "batchremove", method = RequestMethod.POST)
	public String removeBatchProductFromBag(@RequestParam List<String> productIds) {

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);

		if (productIds == null || productIds.size() == 0) {
			return "redirect:/order/stock/list";
		}
		String[] array = productIds.toArray(new String[productIds.size()]);
		stockCardService.removeProductListFromBuyCard(userId, array);
		return "redirect:/order/stock/list";
	}

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
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String stockSaveOrdering(@RequestParam Map<String, Object> filter, @RequestParam List<String> productIds,
			@RequestParam List<String> subscripts, @RequestParam List<String> discountPrices,
			@RequestParam List<String> supplys, Model model) {

		String userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				String.class);

		List<Product> products = productService.getProductInfoListByProductId(productIds);

		Map<String, String> productPriceMap = Maps.newHashMap();
		Map<String, String> productBrandMap = Maps.newHashMap();
		for (Product product : products) {
			productPriceMap.put(String.valueOf(product.getProductId()), String.valueOf(product.getCatalogFee()));
			productBrandMap.put(String.valueOf(product.getProductId()), String.valueOf(product.getBrandId()));
		}

		// 前台页面传的订购的产品订单串，格式：产品编号~产品品牌~备货价格～数量～供货商
		List<String> stockProductOrderList = new ArrayList<String>();
		for (int i = 0; i < productIds.size(); i++) {
			StringBuilder sb = new StringBuilder(120);
			String productId = productIds.get(i);
			sb.append(productId).append("~").append(productBrandMap.get(productId)).append("~")
					.append(discountPrices.get(i)).append("~").append(subscripts.get(i)).append("~")
					.append(supplys.get(i));
			stockProductOrderList.add(sb.toString());
		}
		filter.put("stockProductOrderList", stockProductOrderList);
		stockOrderService.doCreateStockOrder(filter);
		stockCardService.removeProductListFromBuyCard(userId, productIds.toArray(new String[productIds.size()]));
		return "redirect:/order/stock/list";
	}

}
