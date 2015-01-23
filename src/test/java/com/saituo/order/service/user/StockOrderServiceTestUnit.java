package com.saituo.order.service.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.entity.stock.StockProductOrder;
import com.saituo.order.service.ServiceTestCaseSupport;
import com.saituo.order.service.stock.StockOrderService;

public class StockOrderServiceTestUnit extends ServiceTestCaseSupport {
	@Autowired
	private StockOrderService stockOrderService;

	//@Test
	public void doCreateStockOrder() {
		Map<String, Object> filter = new HashMap<String, Object>();
	    filter.put("userOrderId", 2l);
	    filter.put("registerNumber", 2002l);
		List<String> stockProductOrderList = new ArrayList<String>();
	//	格式：产品编号~产品品牌~备货价格～数量～供货商
		stockProductOrderList.add("10010~200~100.0~10~9001");
		stockProductOrderList.add("10020~300~150.0~5~9002");
		filter.put("stockProductOrderList", stockProductOrderList);
		stockOrderService.doCreateStockOrder(filter);
	}
	
	//@Test
	public void doManagerAllotTask() {
		Map<String, Object> filter = new HashMap<String, Object>();
	    filter.put("buyerUserId","999");
		List<String> stockProductOrderList = new ArrayList<String>();
	//	格式：备货产品订单项编号
		stockProductOrderList.add("1");
		stockProductOrderList.add("4");
		filter.put("stockProductOrderList", stockProductOrderList);
		stockOrderService.doManagerAllotTask(filter);
	}
	
	//@Test
	public void getManagerAllotTask() {
		Map<String, Object> filter = new HashMap<String, Object>();
	    filter.put("stockOrderId","5");
	    filter.put("stockNumber","2");
	    filter.put("brandId","1");
	    filter.put("productId","10020");

		 Map<String, Object>  map =stockOrderService.getManagerAllotTask(filter);	
		 List<StockProductOrder>  list =	 ( List<StockProductOrder>)map.get("stockProductOrderList");
		 System.out.println("size:"+list.size());
	}
	
	//@Test
		public void doUpdatePurchased() {
			Map<String, Object> filter = new HashMap<String, Object>();
		    filter.put("statusCd","1");// 状态:1.待采购 2.已分配 3.已采购 4.已入库
			List<String> stockProductOrderList = new ArrayList<String>();
				//备货产品订单编码~重分配原因
				stockProductOrderList.add("5~萨达萨达");
				stockProductOrderList.add("6 ");
				filter.put("stockProductOrderList", stockProductOrderList);
			   stockOrderService.doUpdatePurchased(filter);	
		}
		//@Test
		public void doUpdateStorage() {
			Map<String, Object> filter = new HashMap<String, Object>();
		    filter.put("stockOrderId","7");
			List<String> stockProductOrderList = new ArrayList<String>();
				//备货产品订单编码
				stockProductOrderList.add("5");
				stockProductOrderList.add("6 ");
				filter.put("stockProductOrderList", stockProductOrderList);
			   stockOrderService.doUpdateStorage(filter);	
		}
		
		@Test
		public void getbuyerQueryList() {
			Map<String, Object> filter = new HashMap<String, Object>();
		    filter.put("statusCd","2");
				Map<String, Object>  map=   stockOrderService.getbuyerQueryList(filter);	
				List<StockProductOrder> list =(List<StockProductOrder>)map.get("stockProductOrderList");
				 System.out.println("size:"+list.size());
		}
}
