package com.saituo.order.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.saituo.order.entity.user.UserOrder;
/**
 * 客户订单信息表维护
 */
public interface UserOrderDao {
	/**
	 * <p>
	 * Description: 新增数据
	 * </p>
	 * 
	 * @Method: insert
	 * @param UserOrder
	 * @return void
	 * @throws
	 */
	public void insert(UserOrder userOrder);

	/**
	 * <p>
	 * Description: 更新数据
	 * </p>
	 * 
	 * @Method: update
	 * @param UserOrder
	 * @return void
	 * @throws
	 */
	public void update(UserOrder userOrder);

	/**
	 * <p>
	 * Description: 查询数据:根据客户订单编码查询
	 * </p>
	 * 
	 * @Method: query
	 * @param UserOrder
	 * @return UserOrder
	 * @throws
	 */
	public UserOrder query(UserOrder userOrder);

	/**
	 * <p>
	 * Description: 查询数据:根据客户编码查询
	 * </p>
	 * 
	 * @Method: query
	 * @param UserOrder
	 * @return UserOrder
	 * @throws
	 */
	public List<UserOrder> queryList(@Param(value = "userOrder") UserOrder userOrder,
			@Param(value = "filter") Map<String, Object> filter);

	/**
	 * <p>
	 * Description: 查询数据:根据客户编码查询
	 * </p>
	 * 
	 * @Method: query
	 * @param UserOrder
	 * @return UserOrder
	 * @throws
	 */
	public int count(@Param(value = "userOrder") UserOrder userOrder,
			@Param(value = "filter") Map<String, Object> filter);

	/**
	 * <p>
	 * Description: 删除数据
	 * </p>
	 * 
	 * @Method: delete
	 * @param UserGroupPointAccount
	 * @return void
	 * @throws
	 */
	public void delete(UserOrder userOrder);
}
