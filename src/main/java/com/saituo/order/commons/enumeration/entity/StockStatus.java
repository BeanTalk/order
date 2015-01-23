package com.saituo.order.commons.enumeration.entity;

import com.saituo.order.commons.enumeration.ValueEnum;

/**
 * 审批状态枚举
 * 
 * 状态:1.待采购 2.已分配 3.已采购 4.已入库
 */
public enum StockStatus implements ValueEnum<Integer> {

	WAIT(1, "待采购"), DISTRIBUTED(2, "已分配"), BUY(3, "已采购"), STORAGE(4, "已入库");

	// 值
	private Integer value;
	// 名称
	private String name;

	private StockStatus(Integer value, String name) {
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
