package com.saituo.order.service.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.entity.user.Address;
import com.saituo.order.entity.user.ProductOrder;
import com.saituo.order.entity.user.UserOrder;
import com.saituo.order.service.ServiceTestCaseSupport;

public class UserOrderServiceTestUnit  extends ServiceTestCaseSupport{
	@Autowired
	private UserOrderService userOrderService;
	//@Test
	public void doCreateUserOrder() {
		Map<String, Object> filter =new HashMap<String, Object>();
		filter.put("addressId",2l);
		List<String> productOrderList= new ArrayList<String>();
		productOrderList.add("10010~100.0~10");
		productOrderList.add("10020~150.0~5");
		filter.put("productOrderList",productOrderList);
		userOrderService.doCreateUserOrder(filter);
	}
	
	//@Test
	public void getUserOrderList() {
		Map<String, Object> filter =new HashMap<String, Object>();
		filter.put("statusCd","1");
		filter.put("userId","901");
		filter.put("groupId","189");
		filter.put("userOrderId",3l);

		List<UserOrder>  list = userOrderService.getUserOrderList(filter);
		for (UserOrder userOrder : list) {
			System.out.println("return:"+userOrder.getAcceptDate());
			
		}
	}
	
	//@Test
	public void getDeatilOrderInfo() {
		Map<String, Object> filter =new HashMap<String, Object>();
		filter.put("userOrderId",3l);

		Map<String, Object>  map = userOrderService.getDeatilOrderInfo(filter);
		UserOrder userOrderReturn= (UserOrder)map.get("userOrderReturn");
		System.out.println("userOrderReturn:"+userOrderReturn.getAcceptDate());
		Address addressReturn = (Address)map.get("addressReturn");
		System.out.println("addressReturn:"+addressReturn.getOtherRequire());
		List<ProductOrder>   productOrderReturn=(List<ProductOrder>)map.get("productOrderReturn");
		System.out.println("productOrderReturn:"+productOrderReturn.size());
	}
	//@Test
	public void doUpdateProductOrderFee() {
		Map<String, Object> filter =new HashMap<String, Object>();
		List<String> productOrderList= new ArrayList<String>();
		productOrderList.add("1~50.0~70.0");
		productOrderList.add("2~60.0~65.0");
		filter.put("productOrderList",productOrderList);
		userOrderService.doUpdateProductOrderFee(filter);
	}
	//@Test
	public void doUpdateUserOrderStatus() {
		Map<String, Object> filter =new HashMap<String, Object>();
		filter.put("userOrderId",4l);
		userOrderService.doUpdateUserOrderStatus(filter);
	}
	
	//@Test
	public void doAuditProductOrder() {
		Map<String, Object> filter =new HashMap<String, Object>();
		filter.put("userOrderId",4l);
		//格式：产品订单编号~审批结果~驳回原因～处理意见
		List<String> productOrderList= new ArrayList<String>();
		productOrderList.add("5~3~4~asdasasd");
		productOrderList.add("6~3~~");
		filter.put("productOrderList",productOrderList);
		userOrderService.doAuditProductOrder(filter);
	}
	//@Test
	public void doUpdateUserOrderStatusFive() {
		Map<String, Object> filter =new HashMap<String, Object>();
		filter.put("userOrderId",3l);
		userOrderService.doUpdateUserOrderStatusFive(filter);
	}
	//@Test
	public void doUpdateUserOrderStatusCancel() {
		Map<String, Object> filter =new HashMap<String, Object>();
		filter.put("userOrderId",4l);
		userOrderService.doUpdateUserOrderStatusCancel(filter);
	}
	
	//@Test
	public void doModifyUserOrder() {
		Map<String, Object> filter =new HashMap<String, Object>();
		filter.put("userOrderId",4l);
		//格式：产品订单编号~ 数量
				List<String> productOrderModifyList= new ArrayList<String>();
				productOrderModifyList.add("5~129");
				filter.put("productOrderModifyList",productOrderModifyList);
				//格式：产品订单编号 
				List<String> productOrderDeleteList= new ArrayList<String>();
				productOrderDeleteList.add("6");
				filter.put("productOrderDeleteList",productOrderDeleteList);
		userOrderService.doModifyUserOrder(filter);
	}
	//@Test
	public void doUpdateUserOrderStatusAccept() {
		Map<String, Object> filter =new HashMap<String, Object>();
		filter.put("userOrderId",3l);
		userOrderService.doUpdateUserOrderStatusAccept(filter);
	}
	//@Test
	public void doProductOrderShipment() {
		Map<String, Object> filter =new HashMap<String, Object>();
		List<String> productOrderList= new ArrayList<String>();
		productOrderList.add("5~10010");
		filter.put("productOrderList",productOrderList);
		userOrderService.doProductOrderShipment(filter);
	}
	
}
