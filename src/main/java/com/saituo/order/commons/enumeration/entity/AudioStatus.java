package com.saituo.order.commons.enumeration.entity;

import com.saituo.order.commons.enumeration.ValueEnum;

/**
 * 审批状态枚举
 */
public enum AudioStatus implements ValueEnum<Integer> {

	NO_DEAL(0, "未处理"), WAIT(1, "待审批"), REJECT(2, "已驳回"), PASS(3, "审批通过");

	// 值
	private Integer value;
	// 名称
	private String name;

	private AudioStatus(Integer value, String name) {
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
