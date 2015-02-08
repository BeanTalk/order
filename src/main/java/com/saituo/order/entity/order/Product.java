package com.saituo.order.entity.order;

public class Product {

	// 产品编码
	private Integer productId;

	// 产品名称
	private String productName;

	// 货号
	private String productNum;

	// 产品品牌编码
	private String brandId;

	private String brandName;

	// 规格
	private String specValue;

	// 单位
	private String unitValue;

	// 目录价
	private Double catalogFee;

	// 折扣价
	private Double discoutFee;

	// 加权折扣
	private Double weightDiscount;

	// 采购折扣
	private Double buyDiscount;

	// 最高限价折扣
	private Double limitDiscount;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getSpecValue() {
		return specValue;
	}

	public void setSpecValue(String specValue) {
		this.specValue = specValue;
	}

	public String getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(String unitValue) {
		this.unitValue = unitValue;
	}

	public Double getCatalogFee() {
		return catalogFee;
	}

	public void setCatalogFee(Double catalogFee) {
		this.catalogFee = catalogFee;
	}

	public Double getDiscoutFee() {
		return discoutFee;
	}

	public void setDiscoutFee(Double discoutFee) {
		this.discoutFee = discoutFee;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Double getWeightDiscount() {
		return weightDiscount;
	}

	public void setWeightDiscount(Double weightDiscount) {
		this.weightDiscount = weightDiscount;
	}

	public Double getBuyDiscount() {
		return buyDiscount;
	}

	public void setBuyDiscount(Double buyDiscount) {
		this.buyDiscount = buyDiscount;
	}

	public Double getLimitDiscount() {
		return limitDiscount;
	}

	public void setLimitDiscount(Double limitDiscount) {
		this.limitDiscount = limitDiscount;
	}

}
