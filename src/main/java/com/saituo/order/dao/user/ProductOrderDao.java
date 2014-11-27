package com.saituo.order.dao.user;

import java.util.List;

import com.saituo.order.entity.user.ProductOrder;


/**
 * 产品订单信息表维护
 */
public interface ProductOrderDao {

	/**
	 * <p>Description: 新增数据</p>
	 * @Method: insert
	 * @param UserOrder
	 * @return void 
	 * @throws 
	*/
	public void insert (ProductOrder productOrder);
	
	/**
	 * <p>Description: 更新数据</p>
	 * @Method: update
	 * @param UserOrder
	 * @return void 
	 * @throws 
	*/
	public void update (ProductOrder productOrder);
	
	/**
	 * <p>Description: 查询数据:根据客户产品订单编码查询</p>
	 * @Method: query
	 * @param UserOrder
	 * @return UserOrder 
	 * @throws 
	*/
	public ProductOrder query (ProductOrder productOrder);
	
	/**
	 * <p>Description: 查询数据:根据客户订单编码查询</p>
	 * @Method: query
	 * @param UserOrder
	 * @return UserOrder 
	 * @throws 
	*/
	public List<ProductOrder> queryListByUserOrderId (ProductOrder productOrder);
	
	/**
	 * <p>Description: 删除数据</p>
	 * @Method: delete
	 * @param UserGroupPointAccount
	 * @return void 
	 * @throws 
	*/
	public void delete(ProductOrder productOrder);

}
