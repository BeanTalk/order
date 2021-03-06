package com.saituo.order.dao.account;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

/**
 * 获取地域信息
 */
public interface AreaDao {

	/**
	 * 获取地域信息
	 * 
	 * @param id
	 * @return
	 */
	@Select("select name from sys_area where id= #{areaId} and del_flag=0 ")
	public String getAreaNameById(String areaId);

	@Select("select id, name from sys_area where del_flag=0 ")
	public List<Map<String, String>> getAreaIdAndName();
}
