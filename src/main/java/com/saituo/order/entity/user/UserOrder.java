package com.saituo.order.entity.user;

public class UserOrder {

<<<<<<< HEAD
	//客户订单编码
	private Long userOrderId;
	//客户编码
	private String userId;
	//受理地市
	private String areaId;
	//地址编码
	private Long addressId;
	//创建时间
	private String acceptDate;
	//状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1 已取消
	private String statusCd;
	public Long getUserOrderId() {
		return userOrderId;
	}
	public void setUserOrderId(Long userOrderId) {
		this.userOrderId = userOrderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
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
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	
	
=======
	// 客户订单编码
	private Long userOrderId;
	// 客户编码
	private String userId;
	// 受理地市
	private String areaId;
	// 客户组别编码
	private String groupId;
	// 地址编码
	private Long addressId;
	// 创建时间
	private String acceptDate;
	// 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1 已取消
	private String statusCd;

	public Long getUserOrderId() {
		return userOrderId;
	}

	public void setUserOrderId(Long userOrderId) {
		this.userOrderId = userOrderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
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

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

>>>>>>> liurong-base
}
