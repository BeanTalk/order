package com.saituo.order.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.dao.user.CustomerBeanDao;
import com.saituo.order.entity.user.CustomerBean;
import com.saituo.order.service.ServiceTestCaseSupport;

public class CustomerBeanTestUnit extends ServiceTestCaseSupport {

	@Autowired
	private CustomerBeanDao customerBeanDao;

	//@Test
	public void addUser() {
		CustomerBean customerBean = new CustomerBean();
		customerBean.setBean(200);
		customerBean.setUserId("gg");
		customerBeanDao.insert(customerBean);
		System.out.println("id:" + customerBean.getId());
	}

	@Test
	public void getCustomerBean() {

		List<CustomerBean> customerBeanList = customerBeanDao.getCustomerBean("gg");
		for (CustomerBean customerBean : customerBeanList) {
			System.out.println(customerBean.getUserId());
		}
	}

	//@Test
	public void modifiedCustomerBean() {

		CustomerBean customerBean = new CustomerBean();
		customerBean.setBean(50);
		customerBean.setUserId("gg");

		int value = customerBeanDao.modifiedCustomerBean(customerBean);
		System.out.println("value:" + value);
	}
	
	//@Test
	public void removedCustomerBean() {
		int value = customerBeanDao.removedCustomerBean("gg");
		System.out.println("value:" + value);
	}

}
