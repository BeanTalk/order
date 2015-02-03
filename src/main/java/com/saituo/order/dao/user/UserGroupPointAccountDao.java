package com.saituo.order.dao.user;
/**
 * 客户组积分账户信息表维护
 */
import com.saituo.order.entity.user.UserGroupPointAccount;

public interface UserGroupPointAccountDao {
	
	/**
	 * <p>Description: 新增数据</p>
	 * @Method: insert
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void insert (UserGroupPointAccount userGroupPointAccount);
	
	/**
	 * <p>Description: 更新数据</p>
	 * @Method: update
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void update (UserGroupPointAccount userGroupPointAccount);
	
	/**
	 * <p>Description: 查询数据:根据客户组ID查询</p>
	 * @Method: query
	 * @param UserGroupPointAccount
	 * @return UserGroupPointAccount 
	 * @throws 
	*/
	public UserGroupPointAccount query (UserGroupPointAccount userGroupPointAccount);
	
	/**
	 * <p>Description: 删除数据</p>
	 * @Method: delete
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void delete(UserGroupPointAccount userGroupPointAccount);
	
	/**
	 * <p>Description: 更新数据加积分</p>
	 * @Method: update
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void updateAdd (UserGroupPointAccount userGroupPointAccount);
	
	/**
	 * <p>Description: 更新数据减积分</p>
	 * @Method: update
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void updateReduction (UserGroupPointAccount userGroupPointAccount);

}
