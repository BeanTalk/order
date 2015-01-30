package com.saituo.order.commons.enumeration.entity;

import com.saituo.order.commons.enumeration.ValueEnum;

public enum HandlerResult implements ValueEnum<Integer> {

	SETTLE(1, "协商解决"), EXCHANGE(2, "换货"), RETURN(3, "退货"), CANCEL(4, "投诉取消");

	HandlerResult(Integer value, String name) {
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