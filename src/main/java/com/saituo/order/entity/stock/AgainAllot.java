package com.saituo.order.entity.stock;

public class AgainAllot {

	// 序号
	private Integer id;
	// 备货产品订单编码
	private Integer stockNumber;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}
	public String getAgainAllotReason() {
		return againAllotReason;
	}
	public void setAgainAllotReason(String againAllotReason) {
		this.againAllotReason = againAllotReason;
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
	// 重分配原因
	private String againAllotReason;
	// 创建者
	private String acceptPerson;
	// 创建时间
	private String acceptDate;

}
