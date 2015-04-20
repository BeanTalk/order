package com.saituo.order.entity.user;

public class ProductOrderHis {
	
	//序号
	private Integer  id;
	//产品订单编码
	private Integer  registerNumber;
	//类型结果：1.审批状态 2产品订单项状态 3发票状态
	private String orderResult;
	//创建者
	private String acceptPerson;
	//创建时间
	private String acceptDate;

	public Integer getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(Integer registerNumber) {
		this.registerNumber = registerNumber;
	}
	public String getOrderResult() {
		return orderResult;
	}
	public void setOrderResult(String orderResult) {
		this.orderResult = orderResult;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
