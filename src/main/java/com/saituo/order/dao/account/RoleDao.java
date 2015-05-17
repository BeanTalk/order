package com.saituo.order.dao.account;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface RoleDao {

	@Select("select role_sign from sys_role r, sys_user u, sys_user_role ur "
			+ "where r.id = ur.role_id and u.id = ur.user_id and r.del_flag = 0 and u.del_flag = 0 "
			+ "and u.id = #{userId}")
	public List<String> getUserRole(@Param("userId") Integer userId);

	@Insert("insert into sys_user_role values(#{userId}, #{roleId})")
	public void registerUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

}
