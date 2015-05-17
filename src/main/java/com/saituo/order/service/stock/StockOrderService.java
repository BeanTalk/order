package com.saituo.order.service.stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saituo.order.commons.SessionVariable;
import com.saituo.order.commons.VariableUtils;
import com.saituo.order.dao.order.ProductDao;
import com.saituo.order.dao.stock.AgainAllotDao;
import com.saituo.order.dao.stock.BrandBuyerRelationDao;
import com.saituo.order.dao.stock.StockOrderDao;
import com.saituo.order.dao.stock.StockProductOrderDao;
import com.saituo.order.entity.order.Product;
import com.saituo.order.entity.stock.AgainAllot;
import com.saituo.order.entity.stock.BrandBuyerRelation;
import com.saituo.order.entity.stock.StockOrder;
import com.saituo.order.entity.stock.StockProductOrder;

@Service
public class StockOrderService {

	@Autowired
	private StockOrderDao stockOrderDao;

	@Autowired
	private StockProductOrderDao stockProductOrderDao;

	@Autowired
	private BrandBuyerRelationDao brandBuyerRelationDao;

	@Autowired
	private AgainAllotDao againAllotDao;

	@Autowired
	private ProductDao productDao;

	/**
	 * 备货订单提交方法
	 */
	public Map<String, String> doCreateStockOrder(Map<String, Object> filter) {
		Map<String, String> returnMap = new HashMap<String, String>();
		// 备货订单信息:
		Integer userId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				Integer.class);
		Integer userOrderId = VariableUtils.typeCast(filter.get("userOrderId"), Integer.class); // 客户订单编码
		Integer registerNumber = VariableUtils.typeCast(filter.get("registerNumber"), Integer.class); // 产品订单编码
		Integer areaId = SessionVariable.getCurrentSessionVariable().getAreaId();
		StockOrder stockOrder = new StockOrder();
		stockOrder.setAreaId(areaId);// 受理地市
		stockOrder.setStatusCd("1");// 状态:1.处理中；2.已完成；-1取消
		stockOrder.setUserId(userId);// 备货人员编码
		stockOrderDao.insert(stockOrder);
		// 备货订单编码
		Integer stockOrderId = stockOrder.getStockOrderId();

		// 产品订单项列表
		List<String> stockProductOrderList = (List<String>) filter.get("stockProductOrderList");
		// 前台页面传的订购的产品订单串，格式：产品编号~产品品牌~备货价格～数量
		String prodcutString[];

		StockProductOrder stockProductOrder = null;
		BrandBuyerRelation brandBuyerRelation = null;
		if (stockProductOrderList != null && stockProductOrderList.size() > 0) {
			for (String productRecordString : stockProductOrderList) {
				prodcutString = productRecordString.split("~");
				stockProductOrder = new StockProductOrder();
				brandBuyerRelation = new BrandBuyerRelation();
				brandBuyerRelation.setBrandId(VariableUtils.typeCast(prodcutString[1], Long.class));// 产品品牌
				brandBuyerRelation = brandBuyerRelationDao.query(brandBuyerRelation);// 根据产品品牌查询是否配置对应的采购人员
				// 如果产品品牌配置了对应的采购人员，则自动分配
				if (brandBuyerRelation != null && !brandBuyerRelation.getUserId().equals("")) {
					stockProductOrder.setBuyerUserId(brandBuyerRelation.getUserId()); // 采购人
					stockProductOrder.setIfAutoAllot("1");// 是否自动分配:0.否 1.是
					stockProductOrder.setStatusCd("2");// 状态:1.待采购 2.已分配 3.已采购
														// 4.已入库
				} else {
					stockProductOrder.setIfAutoAllot("0");// 是否自动分配:0.否 1.是
					stockProductOrder.setStatusCd("1");// 状态:1.待采购 2.已分配 3.已采购
														// 4.已入库
				}
				stockProductOrder.setStockOrderId(stockOrderId);// 备货订单编码
				stockProductOrder.setAreaId(areaId);// 受理地市
				stockProductOrder.setUserId(userId);// 备货人编码
				stockProductOrder.setProductId(VariableUtils.typeCast(prodcutString[0], Integer.class)); // 产品编码
				stockProductOrder.setBrandId(VariableUtils.typeCast(prodcutString[1], Integer.class));// 产品品牌
				stockProductOrder.setStockFee(VariableUtils.typeCast(prodcutString[2], Double.class));// 备货价
				// 判断是否时客户订单转备货单
				if (null == userOrderId && null == registerNumber) {
					stockProductOrder.setIfUserOderTransition("0");// 是否客户订单转备货单：0.否
																	// 1.是
				} else {
					stockProductOrder.setIfUserOderTransition("1");// 是否客户订单转备货单：0.否
																	// 1.是
					stockProductOrder.setRegisterNumber(registerNumber);// 产品订单编码
					stockProductOrder.setUserOrderId(userOrderId);// 客户订单编号
				}
				stockProductOrder.setOrderNum(VariableUtils.typeCast(prodcutString[3], Integer.class)); // 备货数量
				stockProductOrderDao.insert(stockProductOrder);
			}
		}
		// 返回备货订单编码
		returnMap.put("stockOrderId", VariableUtils.typeCast(stockOrderId, String.class));
		return returnMap;
	}

	/**
	 * 采购主管根据不同查询条件获取待分配备货订单列表 参数：产品品牌、产品id、备货产品订单编号、备货客户订单编号都是可填可不填的
	 */
	public int getManagerCountTask(Map<String, Object> filter) {
		StockProductOrder stockProductOrder = new StockProductOrder();
		// 产品品牌
		Integer brandId = null;
		if (filter.get("brandId") != null && !filter.get("brandId").equals("")) {
			brandId = Integer.valueOf((String) filter.get("brandId"));
			stockProductOrder.setBrandId(brandId);
		}
		// 产品
		Integer productId = null;
		if (filter.get("productId") != null && !filter.get("productId").equals("")) {
			productId = Integer.valueOf((String) filter.get("productId"));
			stockProductOrder.setProductId(productId);
		}
		// 备货产品订单编码
		Integer stockNumber = null;
		if (filter.get("stockNumber") != null && !filter.get("stockNumber").equals("")) {
			stockNumber = Integer.valueOf((String) filter.get("stockNumber"));
			stockProductOrder.setStockNumber(stockNumber);
		}
		// 备货订单编码
		Integer stockOrderId = null;
		if (filter.get("stockOrderId") != null && !filter.get("stockOrderId").equals("")) {
			stockOrderId = Integer.valueOf((String) filter.get("stockOrderId"));
			stockProductOrder.setStockOrderId(stockOrderId);
		}
		// 状态:1.待采购 2.已分配 3.已采购 4.已入库
		stockProductOrder.setStatusCd("1");
		return stockProductOrderDao.queryCountByStockOrderId(stockProductOrder);
	}

	/**
	 * 采购主管根据不同查询条件获取待分配备货订单列表 参数：产品品牌、产品id、备货产品订单编号、备货客户订单编号都是可填可不填的
	 */
	public Map<String, Object> getManagerAllotTask(Map<String, Object> filter) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		StockProductOrder stockProductOrder = new StockProductOrder();
		// 产品品牌
		Integer brandId = null;
		if (filter.get("brandId") != null && !filter.get("brandId").equals("")) {
			brandId = Integer.valueOf((String) filter.get("brandId"));
			stockProductOrder.setBrandId(brandId);
		}
		// 产品
		Integer productId = null;
		if (filter.get("productId") != null && !filter.get("productId").equals("")) {
			productId = Integer.valueOf((String) filter.get("productId"));
			stockProductOrder.setProductId(productId);
		}
		// 备货产品订单编码
		Integer stockNumber = null;
		if (filter.get("stockNumber") != null && !filter.get("stockNumber").equals("")) {
			stockNumber = Integer.valueOf((String) filter.get("stockNumber"));
			stockProductOrder.setStockNumber(stockNumber);
		}
		// 备货订单编码
		Integer stockOrderId = null;
		if (filter.get("stockOrderId") != null && !filter.get("stockOrderId").equals("")) {
			stockOrderId = Integer.valueOf((String) filter.get("stockOrderId"));
			stockProductOrder.setStockOrderId(stockOrderId);
		}
		// 状态:1.待采购 2.已分配 3.已采购 4.已入库
		stockProductOrder.setStatusCd("1");

		List<StockProductOrder> list = stockProductOrderDao.queryListByStockOrderId(stockProductOrder, filter);
		for (StockProductOrder stockProductOrderTemp : list) {
			Product product = productDao.getProductByProductId(stockProductOrderTemp.getProductId());
			stockProductOrderTemp.setProduct(product);
		}

		returnMap.put("stockProductOrderList", list);
		return returnMap;
	}

	/**
	 * 采购主管根据备货产品订单分配采购人
	 */
	public void doManagerAllotTask(Map<String, Object> filter) {

		Integer allotUserId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				Integer.class); // 分配人
		Integer buyerUserId = VariableUtils.typeCast(filter.get("buyerUserId"), Integer.class); // 采购人员
		// 备货 产品订单项列表
		List<String> stockProductOrderList = (List<String>) filter.get("stockProductOrderList");
		// 前台页面传的订购的产品订单串，格式：产品订单编号
		String prodcutString[];

		if (stockProductOrderList != null && stockProductOrderList.size() > 0) {
			StockProductOrder stockProductOrder = null;
			for (String productOrderString : stockProductOrderList) {
				prodcutString = productOrderString.split("~");
				stockProductOrder = new StockProductOrder();
				stockProductOrder.setStockNumber(VariableUtils.typeCast(prodcutString[0], Integer.class));// 备货产品订单编码
				stockProductOrder.setBuyerUserId(buyerUserId);// 采购人
				stockProductOrder.setAllotUserId(allotUserId); // 分配人
				stockProductOrder.setIfAutoAllot("0");// 是否自动分配:0.否 1.是
				stockProductOrder.setStatusCd("2");// 状态:1.待采购 2.已分配 3.已采购 4.已入库
				stockProductOrderDao.update(stockProductOrder);
			}
		}
	}

	/**
	 * 采购根据传入参数查询需处理的列表
	 */
	public Map<String, Object> getbuyerQueryList(Map<String, Object> filter) {

		Map<String, Object> returnMap = new HashMap<String, Object>();
		StockProductOrder stockProductOrder = new StockProductOrder();

		// 备货订单编码
		String statusCd = null;
		if (filter.get("statusCd") != null && !filter.get("statusCd").equals("")) {
			statusCd = String.valueOf(filter.get("statusCd"));
			// 状态:1.待采购 2.已分配 3.已采购 4.已入库
			stockProductOrder.setStatusCd(statusCd);
		}

		if (filter.get("buyerId") != null && !filter.get("buyerId").equals("")) {
			stockProductOrder.setBuyerUserId(VariableUtils.typeCast(filter.get("buyerId"), Integer.class));
		}

		List<StockProductOrder> list = stockProductOrderDao.queryListByStockOrderId(stockProductOrder, filter);
		for (StockProductOrder stockProductOrderTemp : list) {
			Product product = productDao.getProductByProductId((Integer) stockProductOrderTemp.getProductId());
			stockProductOrderTemp.setProduct(product);
		}
		returnMap.put("stockProductOrderList", list);
		return returnMap;
	}

	/**
	 * 采购根据传入参数查询需处理的列表
	 */
	public int getbuyerQueryCount(Map<String, Object> filter) {

		StockProductOrder stockProductOrder = new StockProductOrder();
		// 备货订单编码
		String statusCd = null;

		if (filter.get("statusCd") != null && !filter.get("statusCd").equals("")) {
			statusCd = String.valueOf(filter.get("statusCd"));
			// 状态:1.待采购 2.已分配 3.已采购 4.已入库
			stockProductOrder.setStatusCd(statusCd);
		}

		if (filter.get("buyerId") != null && !filter.get("buyerId").equals("")) {
			stockProductOrder.setBuyerUserId(VariableUtils.typeCast(filter.get("buyerId"), Integer.class));
		}

		return stockProductOrderDao.queryCountByStockOrderId(stockProductOrder);
	}

	/**
	 * 采购人员修改备货单状态为已采购或者置成重分配
	 */
	public void doUpdatePurchased(Map<String, Object> filter) {

		Integer buyerUserId = VariableUtils.typeCast(SessionVariable.getCurrentSessionVariable().getUser().get("id"),
				Integer.class);// 采购人
		String statusCd = VariableUtils.typeCast(filter.get("statusCd"), String.class); // 备货产品单状态
		// 备货 产品订单项列表
		List<String> stockProductOrderList = (List<String>) filter.get("stockProductOrderList");
		// 前台页面传的订购的产品订单串，格式：备货产品订单编码~供应商编码~重分配原因
		String prodcutString[];

		if (stockProductOrderList != null && stockProductOrderList.size() > 0) {
			StockProductOrder stockProductOrder = null;
			AgainAllot againAllot = null;
			for (String productOrderString : stockProductOrderList) {
				prodcutString = productOrderString.split("~");
				stockProductOrder = new StockProductOrder();
				stockProductOrder.setStockNumber(VariableUtils.typeCast(prodcutString[0], Integer.class));// 备货产品订单编码
				stockProductOrder.setStatusCd(statusCd);// 状态:1.待采购;2.已分配;3.已采购;4.已入库
				if (prodcutString[1] != null && prodcutString[1] != "") {
					stockProductOrder.setSupplierId(VariableUtils.typeCast(prodcutString[1], Integer.class)); // 供应商编码
				}
				// 如果状态为待采购，则证明采购人员点击重新分配
				if (statusCd.equals("1")) {
					stockProductOrder.setIfAutoAllot("0"); // 是否自动分配:0.否 1.是
					againAllot = new AgainAllot();
					againAllot.setAcceptPerson(String.valueOf(buyerUserId));// 采购人
					againAllot.setStockNumber(VariableUtils.typeCast(prodcutString[0], Integer.class));// 备货产品订单编码
					if (prodcutString.length > 1) {
						againAllot.setAgainAllotReason(VariableUtils.typeCast(prodcutString[2], String.class));// 重分配原因
					}
					againAllotDao.insert(againAllot);
				}
				stockProductOrderDao.update(stockProductOrder);
			}
		}
	}

	/**
	 * 采购人员修改备货单状态为已入库
	 */
	public void doUpdateStorage(Map<String, Object> filter) {

		// 备货 产品订单项列表
		List<String> stockProductOrderList = (List<String>) filter.get("stockProductOrderList");
		// 前台页面传的订购的产品订单串，格式：备货产品订单编码
		String prodcutString[];

		if (stockProductOrderList != null && stockProductOrderList.size() > 0) {
			StockProductOrder stockProductOrder = null;
			for (String productOrderString : stockProductOrderList) {
				prodcutString = productOrderString.split("~");
				stockProductOrder = new StockProductOrder();
				stockProductOrder.setStockNumber(VariableUtils.typeCast(prodcutString[0], Integer.class));// 备货产品订单编码
				stockProductOrder.setStatusCd("4");// 状态:1.待采购 2.已分配 3.已采购 4.已入库
				stockProductOrderDao.update(stockProductOrder);
			}
		}
		// 备货订单编码
		Integer stockOrderId = null;
		StockProductOrder stockProductOrder = new StockProductOrder();
		if (filter.get("stockOrderId") != null && !filter.get("stockOrderId").equals("")) {
			stockOrderId = Integer.valueOf((String) filter.get("stockOrderId"));
			stockProductOrder.setStockOrderId(stockOrderId);
		}
		List<StockProductOrder> list = stockProductOrderDao.queryAllListByStockOrderId(stockProductOrder);

		if (list != null && list.size() > 0) {
			boolean flag = true;
			// 判断是否全部入库
			for (StockProductOrder vo : list) {
				// 状态:1.待采购 2.已分配 3.已采购 4.已入库
				if (!vo.getStatusCd().equals("4")) {
					flag = false;
					break;
				}
			}
			// 如果flag=true 则代表所有备货单都已入库，更新备货客户订单信息状态为已完成
			StockOrder stockOrder = new StockOrder();
			if (flag) {
				// 状态:1.处理中；2.已完成；-1取消
				stockOrder.setStatusCd("2");
				// 更新客户订单的状态
				stockOrder.setStockOrderId(stockOrderId);
				stockOrderDao.update(stockOrder);
			}
		}
	}

	/**
	 * 获取重分配原因列表
	 * 
	 * @param stockNumber
	 * @return
	 */
	public List<AgainAllot> getAgainAllotQuery(String stockNumber) {
		return againAllotDao.query(stockNumber);
	}

}
