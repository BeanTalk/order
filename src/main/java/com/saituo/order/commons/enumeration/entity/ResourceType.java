package com.saituo.order.commons.enumeration.entity;

import com.saituo.order.commons.enumeration.ValueEnum;

/**
 * 资源类型枚举
 */
public enum ResourceType implements ValueEnum<Integer> {
	
	/**
	 * 菜单类型，该类型为用户可以见的
	 */
	MENU(1,"菜单类型"),
	/**
	 * 安全类型，该类型为shiro拦截的并且用户不可见的
	 */
	SECURITY(2,"安全类型");
	
	ResourceType(Integer value,String name) {
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
