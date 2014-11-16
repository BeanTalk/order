package com.saituo.order.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.dao.user.CustomerBeanDao;
import com.saituo.order.entity.user.CustomerBean;
import com.saituo.order.service.ServiceTestCaseSupport;

public class CustomerBeanTestUnit extends ServiceTestCaseSupport {

	@Autowired
	private CustomerBeanDao customerBeanDao;

	@Test
	public void addUser() {
		CustomerBean customerBean = new CustomerBean();
		customerBean.setBean(200);
		customerBean.setUserId("mine");
		customerBeanDao.insert(customerBean);
		System.out.println("id:" + customerBean.getId());
	}

	// @Test
	// public void searchUser() {
	// CustomerBean customerBean = new CustomerBean();
	// customerBean.setUserId("userId");
	// customerBeanDao.getBean(customerBean);
	// }

}
