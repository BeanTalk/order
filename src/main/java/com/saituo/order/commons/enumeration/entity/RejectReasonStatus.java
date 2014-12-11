package com.saituo.order.commons.enumeration.entity;

import com.saituo.order.commons.enumeration.ValueEnum;

/**
 * 审批状态枚举
 */
public enum RejectReasonStatus implements ValueEnum<Integer> {

	PRICE_REASON(1, "价格原因"),

	BRAND_REASON(2, "品牌原因"),

	SUPPLY_REASON(3, "存货原因"),

	OTHERS_REASON(3, "其他原因");

	// 值
	private Integer value;
	// 名称
	private String name;

	private RejectReasonStatus(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	/**
	 * 获取值
	 * 
	 * @return Integer
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * 获取名称
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

}
