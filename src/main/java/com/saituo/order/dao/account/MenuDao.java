package com.saituo.order.dao.account;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 资源数据访问
 */
public interface MenuDao {

	/**
	 * 获取资源
	 * 
	 * @param id
	 *            资源主键 ID
	 * 
	 * @return 资源实体 Map
	 */
	public Map<String, Object> get(@Param("id") String id);

	/**
	 * 获取所有资源
	 * 
	 * @param ignore
	 *            忽略的 ID 值
	 * 
	 * @return 资源实体集合
	 */
	public List<Map<String, Object>> getAll(@Param("ignore") String... ignore);

	/**
	 * 获取相关联的子资源
	 * 
	 * @param id
	 *            资源主键 ID
	 * 
	 * @return 资源实体 Map 集合
	 */
	public List<Map<String, Object>> getChildren(@Param("id") String id);

	/**
	 * 获取用户资源
	 * 
	 * @param userId
	 *            用户主键 ID
	 * 
	 * @return 资源实体 Map 集合
	 */
	public List<Map<String, Object>> getUserMenus(@Param("userId") String userId);

	/**
	 * 统计资源数量
	 * 
	 * @return 资源总数量
	 */
	public long count();
}
