package com.saituo.order.dao.account;

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
	public Map<String, Object> get(@Param("id") String id);

	/**
	 * 获取用户所在的组
	 * 
	 * @param userId
	 *            用户主键 ID
	 * 
	 * @return 组实体 Map 集合
	 */
	Map<String, Object> getUserGroup(@Param("userId") String userId);

}
