package com.saituo.order.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.dao.user.UserGroupPointAccountDao;
import com.saituo.order.entity.user.UserGroupPointAccount;
import com.saituo.order.service.ServiceTestCaseSupport;

public class UserGroupPointAccountDaoTestUnit extends ServiceTestCaseSupport{
	
	@Autowired
	private UserGroupPointAccountDao userGroupPointAccountDao;
	
	//@Test
	public void addUserGroupPointAccount() {
		UserGroupPointAccount userGroupPointAccount=new UserGroupPointAccount();
		userGroupPointAccount.setAccountFee(100.0);
		userGroupPointAccount.setPointBalance(100l);
		userGroupPointAccount.setUserGroupId("222");
		userGroupPointAccountDao.insert(userGroupPointAccount);
		System.out.println("id:" +userGroupPointAccount.getAccountFee());
	}
	//@Test
	public void updateUserGroupPointAccount() {
		UserGroupPointAccount userGroupPointAccount=new UserGroupPointAccount();
		userGroupPointAccount.setAccountFee(200.0);
		userGroupPointAccount.setPointBalance(200l);
		userGroupPointAccount.setUserGroupId("222");
		userGroupPointAccountDao.update(userGroupPointAccount);
		System.out.println("id:" +userGroupPointAccount.getAccountFee());
	}
	@Test
	public void queryUserGroupPointAccount() {
		UserGroupPointAccount userGroupPointAccount=new UserGroupPointAccount();
		userGroupPointAccount.setUserGroupId("222");
		UserGroupPointAccount returnUser=userGroupPointAccountDao.query(userGroupPointAccount);
		System.out.println("id:" +returnUser.getAccountFee());
	}
}
