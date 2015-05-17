//package com.saituo.order.dao;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.saituo.order.dao.user.UserGroupPointAccountDao;
//import com.saituo.order.entity.user.UserGroupPointAccount;
//import com.saituo.order.service.ServiceTestCaseSupport;
//
//public class UsertGroupPointAccountTest  extends ServiceTestCaseSupport {
//
//	@Autowired
//	private UserGroupPointAccountDao userGroupPointAccountDao;
//
//	@Test
//	public void addUser() {
//		UserGroupPointAccount userGroupPointAccount=new UserGroupPointAccount();
//		userGroupPointAccount.setUserGroupId("1241");
//		userGroupPointAccount.setPointBalance(100D);
//		userGroupPointAccount.setAccountFee(110.32);
//		userGroupPointAccountDao.insert(userGroupPointAccount);
//		System.out.println("id:" +userGroupPointAccount.getUserGroupId());
//	}
//	
///*	@Test
//	public void updateUser() {
//		UserGroupPointAccount userGroupPointAccount=new UserGroupPointAccount();
//		userGroupPointAccount.setUserGroupId("10002221");
//		userGroupPointAccount.setPointBalance(200l);
//		userGroupPointAccount.setAccountFee(10.0);
//		userGroupPointAccountDao.update(userGroupPointAccount);
//		System.out.println("id:" +userGroupPointAccount.getUserGroupId());
//	}*/
//
///*	@Test
//	public void queryUser() {
//		UserGroupPointAccount userGroupPointAccount=new UserGroupPointAccount();
//		userGroupPointAccount.setUserGroupId("10002221");
//		String a = userGroupPointAccountDao.query(userGroupPointAccount);
//		System.out.println("getAccountFee:" +a);
//	}*/
//}
