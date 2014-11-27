package com.saituo.order.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.dao.user.UserBeansDao;
import com.saituo.order.entity.user.UserBeans;
import com.saituo.order.service.ServiceTestCaseSupport;

public class UserBeansDaoTestUnit   extends ServiceTestCaseSupport{
	@Autowired
	private UserBeansDao userBeansDao;

	//@Test
	public void addUserBeans() {
		UserBeans userBeans=new UserBeans();
		userBeans.setBeansNum(100l);
		userBeans.setUserId("333");
		userBeansDao.insert(userBeans);
		System.out.println("id:" +userBeans.getUserId());
	}
	//@Test
	public void updateUserBeans() {
		UserBeans userBeans=new UserBeans();
		userBeans.setUserId("222");
		userBeans.setBeansNum(200l);
		userBeansDao.update(userBeans);
		System.out.println("id:" +userBeans.getBeansNum());
	}
	//@Test
	public void deleteUserBeans() {
		UserBeans userBeans=new UserBeans();
		userBeans.setUserId("111");
		userBeansDao.delete(userBeans);
	}
	@Test
	public void queryUserBeans() {
		UserBeans userBeans=new UserBeans();
		userBeans.setUserId("222");
		UserBeans result = userBeansDao.query(userBeans);
		System.out.println("id:" +result.getBeansNum());
	}
}
