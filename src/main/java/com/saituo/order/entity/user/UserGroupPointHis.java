package com.saituo.order.entity.user;

public class UserGroupPointHis {
	// 序号
	private Long pointId;
	// 客户组别编码
	private String groupId;
	// 操作类型:1.使用积分 2.累积积分
	private String pointType;
	// 本次使用或累计豆豆数
	private Double pointBalance;
	// 累积积分时是客户订购产品的订单编号；使用积分时是客户积分兑换时使用的订单编号
	private Integer registerNumber;
	// 创建者
	private String acceptPerson;
	// 创建时间
	private String acceptDate;
	// 客户订单编号
	private Long userOrderId;
	
	public Long getPointId() {
		return pointId;
	}
	public void setPointId(Long pointId) {
		this.pointId = pointId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getPointType() {
		return pointType;
	}
	public void setPointType(String pointType) {
		this.pointType = pointType;
	}
	public Double getPointBalance() {
		return pointBalance;
	}
	public void setPointBalance(Double pointBalance) {
		this.pointBalance = pointBalance;
	}
	public Integer getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(Integer registerNumber) {
		this.registerNumber = registerNumber;
	}
	public String getAcceptPerson() {
		return acceptPerson;
	}
	public void setAcceptPerson(String acceptPerson) {
		this.acceptPerson = acceptPerson;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	public Long getUserOrderId() {
		return userOrderId;
	}
	public void setUserOrderId(Long userOrderId) {
		this.userOrderId = userOrderId;
	}

}
