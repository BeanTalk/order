package com.saituo.order.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.dao.user.UserPeasHisDao;
import com.saituo.order.entity.user.UserGroupPointHis;
import com.saituo.order.entity.user.UserPeasHis;
import com.saituo.order.service.ServiceTestCaseSupport;

public class UserPeasHisDaoTestUnit extends ServiceTestCaseSupport{
	@Autowired
	private UserPeasHisDao userPeasHisDao;
	@Test
	public void addUserPeasHis() {
		UserPeasHis userPeasHis=new UserPeasHis();
		userPeasHis.setAcceptPerson("admin");
		userPeasHis.setPeasBalance(100l);
		userPeasHis.setPeasType("1");
		userPeasHis.setRegisterNumber(111l);
		userPeasHis.setUserId("11");
		userPeasHisDao.insert(userPeasHis);
		System.out.println("id:" +userPeasHis.getPeasId());
	}

	@Test
	public void  queryListUserPeasHis() {
		UserPeasHis userPeasHis=new UserPeasHis();
		userPeasHis.setUserId("11");
		List<UserPeasHis>	list =userPeasHisDao.queryList(userPeasHis);
		for (UserPeasHis a : list) {
			System.out.println(a.getPeasId());
		}
	}
	
}
