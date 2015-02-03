package com.saituo.order.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.saituo.order.entity.user.UserGroupPointHis;

/**
 * 客户豆豆历史表维护
 */
public interface UserGroupPointHisDao {
	/**
	 * <p>
	 * Description: 新增数据
	 * </p>
	 * 
	 * @Method: insert
	 * @param UserGroupPointAccount
	 * @return void
	 * @throws
	 */
	public void insert(UserGroupPointHis userGroupPointHis);
	/**
	 * <p>
	 * Description: 查询数据:根据客户编码查询豆豆变更情况集合
	 * </p>
	 * 
	 * @Method: query
	 * @param UserGroupPointAccount
	 * @return UserGroupPointAccount
	 * @throws
	 */
	public List<UserGroupPointHis> queryList(@Param("userGroupPointHis") UserGroupPointHis userGroupPointHis,
			@Param("filter") Map<String, Object> filter);

	/**
	 * <p>
	 * Description: 查询数据:根据客户编码查询豆豆变更情况集合
	 * </p>
	 * 
	 * @Method: query
	 * @param UserGroupPointAccount
	 * @return UserGroupPointAccount
	 * @throws
	 */
	public int count(@Param("userGroupPointHis") UserGroupPointHis userGroupPointHis);
}
