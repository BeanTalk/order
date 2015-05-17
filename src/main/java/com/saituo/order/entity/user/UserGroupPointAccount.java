package com.saituo.order.entity.user;

//客户组积分账户信息表
public class UserGroupPointAccount {

	// 客户组编号
	private String userGroupId;

	// 积分
	private Double pointBalance;

	// 余额
	private Double accountFee;

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public Double getPointBalance() {
		return pointBalance;
	}

	public void setPointBalance(Double pointBalance) {
		this.pointBalance = pointBalance;
	}

	public Double getAccountFee() {
		return accountFee;
	}

	public void setAccountFee(Double accountFee) {
		this.accountFee = accountFee;
	}

}
