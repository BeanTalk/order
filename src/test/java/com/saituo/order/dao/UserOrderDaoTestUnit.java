package com.saituo.order.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.dao.user.UserOrderDao;
import com.saituo.order.entity.user.UserOrder;
import com.saituo.order.service.ServiceTestCaseSupport;

public class UserOrderDaoTestUnit extends ServiceTestCaseSupport {
	@Autowired
	private UserOrderDao userOrderDao;
	//@Test
	public void addUserOrder() {
		UserOrder userOrder=new UserOrder();
		userOrder.setAddressId(1l);
		userOrder.setAreaId("189");
		userOrder.setGroupId("1");
		userOrder.setStatusCd("1");
		userOrder.setUserId("111");
		userOrderDao.insert(userOrder);
		System.out.println("id:" +userOrder.getUserOrderId());
	}
	//@Test
	public void updateUserOrder() {
		UserOrder userOrder=new UserOrder();
		//userOrder.setAddressId(1l);
		userOrder.setUserOrderId(2l);
		userOrder.setStatusCd("3");
		userOrderDao.update(userOrder);
		System.out.println("id:" +userOrder.getUserOrderId());
	}
	//@Test
	public void deleteUserOrder() {
		UserOrder userOrder=new UserOrder();
		userOrder.setUserOrderId(3l);
		userOrderDao.delete(userOrder);
		System.out.println("id:" +userOrder.getUserOrderId());
	}
	//@Test
	public void queryUserOrder() {
		UserOrder userOrder=new UserOrder();
		userOrder.setUserOrderId(2l);
		UserOrder result=	userOrderDao.query(userOrder);
		System.out.println("id:" +result.getAcceptDate());
	}
	@Test
	public void queryListUserOrder() {
		UserOrder userOrder=new UserOrder();
		userOrder.setUserId("111");
		List<UserOrder> list=	userOrderDao.queryList(userOrder);
		for (UserOrder a : list) {
			System.out.println("id:" +a.getAcceptDate());
		}
		
	}
}
