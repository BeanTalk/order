package com.saituo.order.dao.user;
/**
 * 客户豆豆信息表维护
 */
import org.apache.ibatis.annotations.Select;

import com.saituo.order.entity.user.UserBean;

public interface UserBeanDao {
	/**
	 * <p>Description: 查询数据:根据客户ID查询</p>
	 * @Method: insert
	 * @param UserBean
	 * @return int 
	 * @throws 
	*/
	@Select("select beans_num from st_cust_beans where cust_id = #{userId}")
	public int getBean(UserBean customerBean);
	
	/**
	 * <p>Description: 新增数据</p>
	 * @Method: insert
	 * @param UserBean
	 * @return int 
	 * @throws 
	*/
	public int insert(UserBean customerBean);
	

	/**
	 * <p>Description: 更新数据</p>
	 * @Method: update
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void update (UserBean customerBean);
	
	/**
	 * <p>Description: 删除数据</p>
	 * @Method: delete
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void delete(UserBean customerBean);

}
