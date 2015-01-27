package com.saituo.order.commons.enumeration.entity;

import com.saituo.order.commons.enumeration.ValueEnum;

public enum CompaintType implements ValueEnum<Integer> {

	QUALITY(1, "产品质量问题"), DOSE(2, "产品剂量问题"), PACKAGE(3, "产品包装问题"), LIVETIME(4, "产品保质期问题");

	CompaintType(Integer value, String name) {
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