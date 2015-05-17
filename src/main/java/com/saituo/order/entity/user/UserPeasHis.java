package com.saituo.order.entity.user;

public class UserPeasHis {

	// 序号
	private Long peasId;
	// 客户编码
	private String userId;
	//
	private Integer areaId;
	// 操作类型:1.累积积分2.使用积分
	private String peasType;
	// 本次使用或累计豆豆数
	private Long peasBalance;
	// 累积豆豆时是客户订购产品的订单编号；使用豆豆时是客户积分兑换时使用的订单编号
	private Long registerNumber;
	// 创建者
	private String acceptPerson;
	// 创建时间
	private String acceptDate;
	// 礼品ID
	private Long giftId;
	private Integer ifExchange;

	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Long getGiftId() {
		return giftId;
	}
	public void setGiftId(Long giftId) {
		this.giftId = giftId;
	}

	public Long getPeasId() {
		return peasId;
	}
	public void setPeasId(Long peasId) {
		this.peasId = peasId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPeasType() {
		return peasType;
	}
	public void setPeasType(String peasType) {
		this.peasType = peasType;
	}
	public Long getPeasBalance() {
		return peasBalance;
	}
	public void setPeasBalance(Long peasBalance) {
		this.peasBalance = peasBalance;
	}

	public Long getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(Long registerNumber) {
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
	public Integer getIfExchange() {
		return ifExchange;
	}
	public void setIfExchange(Integer ifExchange) {
		this.ifExchange = ifExchange;
	}

}
