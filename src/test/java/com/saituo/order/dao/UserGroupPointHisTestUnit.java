package com.saituo.order.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.dao.user.UserGroupPointHisDao;
import com.saituo.order.entity.user.ProductOrderHis;
import com.saituo.order.entity.user.UserGroupPointHis;
import com.saituo.order.service.ServiceTestCaseSupport;

public class UserGroupPointHisTestUnit  extends ServiceTestCaseSupport{
	@Autowired
	private UserGroupPointHisDao userGroupPointHisDao;
	
	@Test
	public void addUserGroupPointAccount() {
		UserGroupPointHis userGroupPointHis=new UserGroupPointHis();
		userGroupPointHis.setAcceptPerson("admin1");
		userGroupPointHis.setGroupId("222");
		userGroupPointHis.setPointBalance(110l);
		userGroupPointHis.setPointType("1");
		userGroupPointHis.setRegisterNumber(111l);
		userGroupPointHisDao.insert(userGroupPointHis);
		System.out.println("id:" +userGroupPointHis.getPointId());
	}
	
	@Test
	public void  queryListProductOrderHis() {
		UserGroupPointHis userGroupPointHis=new UserGroupPointHis();
		userGroupPointHis.setGroupId("222");
		List<UserGroupPointHis>	list = userGroupPointHisDao.queryList(userGroupPointHis);
		for (UserGroupPointHis a : list) {
			System.out.println(a.getPointId());
		}
	}

}
