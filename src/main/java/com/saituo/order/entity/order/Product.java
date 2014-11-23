package com.saituo.order.entity.order;

public class Product {

	private Integer productId;

	private String productName;

	private String productNum;

	private String brandId;

	private String specValue;

	private String unitValue;

	private Double catalogFee;

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

}
