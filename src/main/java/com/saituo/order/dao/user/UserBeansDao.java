package com.saituo.order.dao.user;
/**
 * 客户豆豆信息表维护
 */
import com.saituo.order.entity.user.UserBeans;

public interface UserBeansDao {
	/**
	 * <p>Description: 查询数据:根据客户ID查询</p>
	 * @Method: insert
	 * @param UserBeans
	 * @return int 
	 * @throws 
	*/
	//@Select("select beans_num from st_cust_beans where cust_id = #{userId}")
	public UserBeans query(UserBeans userBeans);
	
	/**
	 * <p>Description: 新增数据</p>
	 * @Method: insert
	 * @param UserBeans
	 * @return int 
	 * @throws 
	*/
	public int insert(UserBeans userBeans);
	

	/**
	 * <p>Description: 更新数据</p>
	 * @Method: update
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void update (UserBeans userBeans);
	
	/**
	 * <p>Description: 删除数据</p>
	 * @Method: delete
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void delete(UserBeans userBeans);
	/**
	 * <p>Description: 更新数据:加</p>
	 * @Method: update
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void updateAdd (UserBeans userBeans);
	/**
	 * <p>Description: 更新数据:减</p>
	 * @Method: update
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void updateReduction (UserBeans userBeans);

}
