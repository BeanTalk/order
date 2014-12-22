package com.saituo.order.entity.user;


public class ProductRecord {
	// 产品订单编码
	private Long recordNumber;
	// 客户订单编码
	private Long userRecordId;
	// 受理地市
	private Integer areaId;
	// 客户编码
	private Integer userId;
	// 产品编码
	private Long productId;
	// 订购价
	private Double orderFee;
	// 订购数量
	private Long orderNum;
	//供货商ID
	private Long supplierId;
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	// 创建时间
	private String acceptDate;
	public Long getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(Long recordNumber) {
		this.recordNumber = recordNumber;
	}
	public Long getUserRecordId() {
		return userRecordId;
	}
	public void setUserRecordId(Long userRecordId) {
		this.userRecordId = userRecordId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Double getOrderFee() {
		return orderFee;
	}
	public void setOrderFee(Double orderFee) {
		this.orderFee = orderFee;
	}
	public Long getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	
	

}
