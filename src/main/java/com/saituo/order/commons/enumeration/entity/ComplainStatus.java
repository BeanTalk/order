package com.saituo.order.commons.enumeration.entity;

import com.saituo.order.commons.enumeration.ValueEnum;

public enum ComplainStatus implements ValueEnum<Integer> {

	NOT_FINISH(0, "未完成"), DOING(1, "处理中"), COMPLATED(2, "已完成"), CANCEL(-1, "已取消");

	ComplainStatus(Integer value, String name) {
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
