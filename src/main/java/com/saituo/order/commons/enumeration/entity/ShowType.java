package com.saituo.order.commons.enumeration.entity;

import com.saituo.order.commons.enumeration.ValueEnum;

public enum ShowType implements ValueEnum<Integer> {

	SHOW(1, "显示"), HIDDEN(0, "隐藏");

	ShowType(Integer value, String name) {
		this.name = name;
		this.value = value;
	}

	private String name;

	private Integer value;

	/**
	 * 获取资源类型名称
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取资源类型值
	 * 
	 * @return Integer
	 */
	public Integer getValue() {
		return value;
	}

}