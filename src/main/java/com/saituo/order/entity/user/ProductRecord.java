package com.saituo.order.entity.user;

import com.saituo.order.entity.order.Product;

public class ProductRecord {
	// 产品订单编码
	private Integer recordNumber;
	// 客户订单编码
	private Integer userRecordId;
	// 受理地市
	private Integer areaId;
	// 客户编码
	private Integer userId;
	// 产品编码
	private Integer productId;
	// 订购价
	private Double orderFee;
	// 订购数量
	private Integer orderNum;
	// 供货商ID
	private Integer supplierId;

	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	// 创建时间
	private String acceptDate;
	public Integer getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(Integer recordNumber) {
		this.recordNumber = recordNumber;
	}
	public Integer getUserRecordId() {
		return userRecordId;
	}
	public void setUserRecordId(Integer userRecordId) {
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
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Double getOrderFee() {
		return orderFee;
	}
	public void setOrderFee(Double orderFee) {
		this.orderFee = orderFee;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}

	private Product product;

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

}
