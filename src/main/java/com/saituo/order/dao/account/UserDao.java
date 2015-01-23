package com.saituo.order.dao.account;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 用户数据访问
 */
public interface UserDao {

	/**
	 * 获取用户
	 * 
	 * @param id
	 *            用户主键 ID
	 * 
	 * @return 用户实体 Map
	 */
	public Map<String, Object> get(@Param("id") Integer id);

	/**
	 * 获取所有用户 包括已删除用户
	 * 
	 * @return
	 */
	public List<Map<String, String>> getAllofUser();

	/**
	 * 获取用户
	 * 
	 * @param usernameOrEmail
	 *            用户登录帐号或电子实体
	 * 
	 * @return 用户实体 Map
	 */
	public Map<String, Object> getByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

	/**
	 * 更新用户
	 * 
	 * @param entity
	 *            用户实体 Map
	 */
	public void update(@Param("entity") Map<String, Object> entity);

	/**
	 * 更新密码
	 * 
	 * @param id
	 *            用户主键 ID
	 * @param password
	 *            密码
	 */
	public void updatePassword(@Param("id") Integer id, @Param("password") String password);

	/**
	 * 统计用户
	 * 
	 * @param filter
	 *            查询条件
	 * 
	 * @return 统计用户总数
	 */
	public long count(@Param("filter") Map<String, Object> filter);

	/**
	 * 查询用户
	 * 
	 * @param filter
	 *            查询条件
	 * 
	 * @return 用户实体 Map集合
	 */
	public List<Map<String, Object>> find(@Param("filter") Map<String, Object> filter);

	/**
	 * 是否存在该用户
	 * 
	 * @param loginName
	 * @param email
	 * @return
	 */
	@Select("select count(*) from sys_user where email = #{email} and login_name = #{login_name}")
	public int isExistsUser(@Param(value = "login_name") String loginName, @Param(value = "email") String email);

	/**
	 * 重设password 通过loginName
	 * 
	 * @param loginName
	 * @param newpasswordHex
	 */
	@Update("update sys_user set password=#{newpasswordHex} where login_name = #{login_name}")
	public void resetPassword(@Param(value = "login_name") String loginName,
			@Param(value = "newpasswordHex") String newpasswordHex);

	/**
	 * 获取所有人员在该组织中的
	 * 
	 * @param officeId
	 * @return
	 */
	public List<Map<String, Object>> findAllofUserByOfficeId(@Param("filter") Map<String, String> filter);

	/**
	 * 获取所有人员在该地市中的
	 * 
	 * @param officeId
	 * @return
	 */
	public List<Map<String, Object>> findAllofUserByAreaId(@Param(value = "filter") Map<String, String> filter);

	/**
	 * 根据 AreaId以及roleSign获取用户列表
	 * 
	 * @param areaId
	 * @param areaId
	 * @return
	 */
	public List<Map<String, Object>> findUserByAreaIdAndRole(@Param(value = "areaId") Integer areaId,
			@Param(value = "roleSign") Integer roleSign);
}
