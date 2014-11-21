package com.saituo.order.dao.user;

import java.util.List;

import com.saituo.order.entity.user.Address;

/**
 * 地址信息表维护
 */
public interface AddressDao {
	/**
	 * <p>Description: 新增数据</p>
	 * @Method: insert
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void insert (Address address);
	
	/**
	 * <p>Description: 更新数据</p>
	 * @Method: update
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void update (Address address);
	
	/**
	 * <p>Description: 查询数据:根据地址ID查询</p>
	 * @Method: query
	 * @param UserGroupPointAccount
	 * @return UserGroupPointAccount 
	 * @throws 
	*/
	public Address query (Address address);
	
	/**
	 * <p>Description: 查询数据:根据客户ID查询地址信息集合</p>
	 * @Method: query
	 * @param UserGroupPointAccount
	 * @return UserGroupPointAccount 
	 * @throws 
	*/
	public List<Address> queryList (Address address);
	
	/**
	 * <p>Description: 删除数据</p>
	 * @Method: delete
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void delete(Address address);

}
