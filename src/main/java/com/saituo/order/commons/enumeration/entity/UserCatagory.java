package com.saituo.order.commons.enumeration.entity;

import com.saituo.order.commons.enumeration.ValueEnum;

/**
 * 用户类别枚举
 */
public enum UserCatagory implements ValueEnum<Integer> {

	/**
	 * 外部用户
	 */
	EXTERNAL(1, "外部用户"),
	/**
	 * 内部用户
	 */
	INTERNAL(2, "内部用户");

	// 值
	private Integer value;
	// 名称
	private String name;

	private UserCatagory(Integer value, String name) {
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
