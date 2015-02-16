package com.saituo.order.commons.enumeration.entity;

import com.saituo.order.commons.enumeration.ValueEnum;

/**
 * 用户订单状态枚举
 * 
 * 状态::0未处理;1.已出单；2.已收货；3.已结款；-1.已取消； －2.已退货
 * 
 * @author maurice
 *
 */
public enum ProductOrderState implements ValueEnum<Integer> {

	/**
	 * 未处理
	 */
	UNSETTLED(0, "未处理"),
	/**
	 * 已出单
	 */
	DEALED(1, "已出单"),
	/**
	 * 已收货
	 */
	RECEVIED(2, "已收货"),
	/**
	 * 已结款
	 */
	PAID(3, "已结款"),
	/**
	 * 已取消
	 */
	CANCELED(-1, "已取消"),

	/**
	 * 已退货
	 */
	BACKED(-2, "已退货");

	private Integer value; // 值

	private String name; // 名称

	private ProductOrderState(Integer value, String name) {
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