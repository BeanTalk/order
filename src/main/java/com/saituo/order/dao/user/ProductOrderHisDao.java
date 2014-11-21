package com.saituo.order.dao.user;

import java.util.List;

import com.saituo.order.entity.user.ProductOrderHis;
/**
 * 产品订单操作历史表维护
 */
public interface ProductOrderHisDao {
	/**
	 * <p>Description: 新增数据</p>
	 * @Method: insert
	 * @param ProductOrderHis
	 * @return void 
	 * @throws 
	*/
	public void insert (ProductOrderHis productOrderHis);
	/**
	 * <p>Description: 查询数据:根据产品订单编码查询产品订单操作历史</p>
	 * @Method: query
	 * @param ProductOrderHis
	 * @return List 
	 * @throws 
	*/
	public List<ProductOrderHis> queryList (ProductOrderHis productOrderHis);
}
