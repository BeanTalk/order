package com.saituo.order.commons.enumeration.entity;

import com.saituo.order.commons.enumeration.ValueEnum;

/**
 * 用户订单状态枚举
 * 
 * 状态:1.保存订单;2.待审批;3.已驳回;4.审批通过;5.已下单;6.已接单;7.已完成;-1 已取消
 * 
 * @author maurice
 *
 */
public enum UserOrderingState implements ValueEnum<Integer> {

	/**
	 * 保存修价
	 */
	HOLD(1, "保存修价"),
	/**
	 * 待审批
	 */
	PENDING(2, "待审批"),
	/**
	 * 已驳回
	 */
	REJECTED(3, "已驳回"),
	/**
	 * 审批通过
	 */
	ACCEPTED(4, "审批通过"),
	/**
	 * 已下单
	 */
	HAVEORDERED(5, "已下单"),
	/**
	 * 已接单
	 */
	RECEIVED(6, "已接单"),
	/**
	 * 已完成
	 */
	COMPLETED(7, "已完成"),
	/**
	 * 已取消
	 */
	CANCELED(-1, "已取消");

	private Integer value; // 值

	private String name; // 名称

	private UserOrderingState(Integer value, String name) {
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