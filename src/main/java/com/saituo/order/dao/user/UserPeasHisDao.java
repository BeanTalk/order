package com.saituo.order.dao.user;

import java.util.List;

import com.saituo.order.entity.user.UserPeasHis;

/**
 * 客户豆豆历史表维护
 */
public interface UserPeasHisDao {
	/**
	 * <p>Description: 新增数据</p>
	 * @Method: insert
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void insert (UserPeasHis userPeasHis);
	/**
	 * <p>Description: 查询数据:根据客户编码查询豆豆变更情况集合</p>
	 * @Method: query
	 * @param UserGroupPointAccount
	 * @return UserGroupPointAccount 
	 * @throws 
	*/
	public List<UserPeasHis> queryList (UserPeasHis userPeasHis);
	
}
