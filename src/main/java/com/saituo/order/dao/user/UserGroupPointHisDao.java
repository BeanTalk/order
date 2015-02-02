package com.saituo.order.dao.user;

import java.util.List;

import com.saituo.order.entity.user.UserGroupPointHis;

/**
 * 客户积分历史表维护
 */
public interface UserGroupPointHisDao {
	/**
	 * <p>Description: 新增数据</p>
	 * @Method: insert
	 * @param UserGroupPointHis
	 * @return void 
	 * @throws 
	*/
	public void insert (UserGroupPointHis userGroupPointHis);
	/**
	 * <p>Description: 查询数据:根据客户编码查询豆豆变更情况集合</p>
	 * @Method: query
	 * @param UserGroupPointHis
	 * @return List<UserGroupPointHis> 
	 * @throws 
	*/
	public List<UserGroupPointHis> queryList (UserGroupPointHis userGroupPointHis);
	
	/**
	 * <p>Description: 查询数据:根据产品订单编号+操作类型</p>
	 * @Method: query
	 * @param UserGroupPointHis
	 * @return UserGroupPointHis 
	 * @throws 
	*/
	public UserGroupPointHis queryByRegisterNumber (UserGroupPointHis userGroupPointHis);

}
