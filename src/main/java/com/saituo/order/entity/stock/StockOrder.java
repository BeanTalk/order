package com.saituo.order.entity.stock;

public class StockOrder {
	// 备货订单编码
	private Integer stockOrderId;
	// 客户编码
	private Integer userId;
	// 受理地市
	private Integer areaId;
	// 创建时间
	private String acceptDate;
	// 状态:1.处理中；2.已完成；-1取消
	private String statusCd;
	public Integer getStockOrderId() {
		return stockOrderId;
	}
	public void setStockOrderId(Integer stockOrderId) {
		this.stockOrderId = stockOrderId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
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
}