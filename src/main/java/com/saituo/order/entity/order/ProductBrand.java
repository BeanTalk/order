package com.saituo.order.entity.order;

import java.util.Date;

public class ProductBrand {

	private Integer brandId;

	private String brandName;

	private Double buyDiscount;

	private Double weightDiscount;

	private Double limitDiscount;

	private String createBy;

	private Date createDate;

	private String updateBy;

	private Date updateDate;

	private String remarks;

	private String delFlag;

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Double getBuyDiscount() {
		return buyDiscount;
	}

	public void setBuyDiscount(Double buyDiscount) {
		this.buyDiscount = buyDiscount;
	}

	public Double getWeightDiscount() {
		return weightDiscount;
	}

	public void setWeightDiscount(Double weightDiscount) {
		this.weightDiscount = weightDiscount;
	}

	public Double getLimitDiscount() {
		return limitDiscount;
	}

	public void setLimitDiscount(Double limitDiscount) {
		this.limitDiscount = limitDiscount;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
