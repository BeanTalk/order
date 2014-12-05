package com.saituo.order.commons.enumeration.entity;

import com.saituo.order.commons.enumeration.ValueEnum;

/**
 * 发票状态枚举
 */
public enum BillStatus implements ValueEnum<Integer> {

	NO_INVOICE(1, "未开具发票"),

	INVOICED(2, "已开具发票"),

	SENDED(3, "已送发票");

	// 值
	private Integer value;
	// 名称
	private String name;

	private BillStatus(Integer value, String name) {
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
