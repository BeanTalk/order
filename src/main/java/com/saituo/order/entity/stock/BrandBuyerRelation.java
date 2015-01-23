package com.saituo.order.entity.stock;

//品牌与采购人员关系表
public class BrandBuyerRelation {

	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	// 品牌ID
	private Long brandId;
	// 客户编码
	private Integer userId;

}
