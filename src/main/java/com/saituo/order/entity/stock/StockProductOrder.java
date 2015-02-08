package com.saituo.order.entity.stock;

import com.saituo.order.entity.order.Product;

public class StockProductOrder {
	
	// 备货产品订单编码
	private Integer stockNumber;
	// 备货订单编码
	private Integer stockOrderId;
	// 受理地市
	private Integer areaId;
	// 备货人编码
	private Integer userId;
	// 产品编码
	private Integer productId;
	// 产品品牌编码
	private Integer brandId;
	// 备货价
	private Double stockFee;
	// 供应商编码
	private Integer supplierId;
	// 是否客户订单转备货单：0.否 1.是
	private String ifUserOderTransition;
	// 产品订单编码
	private Integer registerNumber;
	// 备货订单编码
	private Integer userOrderId;
	// 备货数量
	private Integer orderNum;
	// 状态:1.待采购 2.已分配 3.已采购 4.已入库
	private String statusCd;
	// 创建时间
	private String acceptDate;
	// 最后修改时间
	private String changeDate;
	// 是否自动分配:0.否 1.是
	private String ifAutoAllot;
	// 分配人
	private Integer allotUserId;
	// 采购人
	private Integer buyerUserId;
	// 产品项
	private Product product;

	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}
	public Integer getStockOrderId() {
		return stockOrderId;
	}
	public void setStockOrderId(Integer stockOrderId) {
		this.stockOrderId = stockOrderId;
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
	public Double getStockFee() {
		return stockFee;
	}
	public void setStockFee(Double stockFee) {
		this.stockFee = stockFee;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getIfUserOderTransition() {
		return ifUserOderTransition;
	}
	public void setIfUserOderTransition(String ifUserOderTransition) {
		this.ifUserOderTransition = ifUserOderTransition;
	}
	public Integer getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(Integer registerNumber) {
		this.registerNumber = registerNumber;
	}
	public Integer getUserOrderId() {
		return userOrderId;
	}
	public void setUserOrderId(Integer userOrderId) {
		this.userOrderId = userOrderId;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}
	public String getIfAutoAllot() {
		return ifAutoAllot;
	}
	public void setIfAutoAllot(String ifAutoAllot) {
		this.ifAutoAllot = ifAutoAllot;
	}

	public Integer getAllotUserId() {
		return allotUserId;
	}
	public void setAllotUserId(Integer allotUserId) {
		this.allotUserId = allotUserId;
	}
	public Integer getBuyerUserId() {
		return buyerUserId;
	}
	public void setBuyerUserId(Integer buyerUserId) {
		this.buyerUserId = buyerUserId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

}
