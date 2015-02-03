package com.saituo.order.dao.account;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 组数据访问
 */
public interface GroupDao {

	/**
	 * 获取组
	 * 
	 * @param id
	 *            组主键 ID
	 * 
	 * @return 组实体 Map
	 */
	public List<Map<String, Object>> get(@Param("areaId") Integer areaId);

	/**
	 * 获取用户所在的组
	 * 
	 * @param userId
	 *            用户主键 ID
	 * 
	 * @return 组实体 Map 集合
	 */
	public Map<String, Object> getUserGroup(@Param("userId") Integer userId);

}
