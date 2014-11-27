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
	public Map<String, Object> get(@Param("id") String id);

	/**
	 * 获取用户所在的组
	 * 
	 * @param userId
	 *            用户主键 ID
	 * 
	 * @return 组实体 Map 集合
	 */
	List<Map<String, Object>> getUserGroups(@Param("userId") String userId);

	/**
	 * 查询组
	 * 
	 * @param filter
	 *            查询条件
	 * 
	 * @return 组实体 Map集合
	 */
	public List<Map<String, Object>> find(@Param("filter") Map<String, Object> filter);
}
