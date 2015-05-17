package com.saituo.order.commons.enumeration.entity;

import com.saituo.order.commons.enumeration.ValueEnum;

/**
 * 用户类别枚举
 */
public enum RoleSign implements ValueEnum<Integer> {

	STUDENT(1, "学生"),

	TEACHER(2, "导师"),

	PI(3, "PI"),

	BUYER(4, "采购"),

	SALE(5, "销售"),

	FINANCE(6, "财务"),

	COMPANY_ADMIN(7, "公司级管理员"),

	SYS_ADMIN(8, "系统管理员"),

	INSIDE(9, "内勤"),
	
	NEWHIRE(10, "技术支持"),
	
	BUYER_ADMIN(12, "采购主管");

	// 值
	private Integer value;
	// 名称
	private String name;

	private RoleSign(Integer value, String name) {
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
