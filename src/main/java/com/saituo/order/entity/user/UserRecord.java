package com.saituo.order.entity.user;

public class UserRecord {
	// 客户记录编码
	private Long userRecordId;
	// 客户编码
	private Integer userId;
	// 客户名称
	private String userName;
	// 受理地市
	private Integer areaId;

	public Long getUserRecordId() {
		return userRecordId;
	}
	public void setUserRecordId(Long userRecordId) {
		this.userRecordId = userRecordId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}

	// 客户组别编码
	private Integer groupId;
	// 地址编码
	private Long addressId;
	// 创建时间
	private String acceptDate;

}
