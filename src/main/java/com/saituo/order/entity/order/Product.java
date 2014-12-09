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

	// 规格
	private String specValue;

	// 单位
	private String unitValue;

	// 目录价
	private Double catalogFee;

	// 折扣价
	private Double discoutFee;

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

}
