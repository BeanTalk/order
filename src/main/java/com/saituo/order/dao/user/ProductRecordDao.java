package com.saituo.order.dao.user;

import java.util.List;

import com.saituo.order.entity.user.ProductRecord;

/**
 *记录产品订单信息表表维护
 */
public interface ProductRecordDao {
	/**
	 * <p>Description: 新增数据</p>
	 * @Method: insert
	 * @param ProductRecord
	 * @return void 
	 * @throws 
	*/
	public void insert (ProductRecord productRecord);
	
	/**
	 * <p>Description: 查询数据:根据客户订单编码查询</p>
	 * @Method: query
	 * @param ProductRecord
	 * @return List<ProductRecord> 
	 * @throws 
	*/
	public List<ProductRecord> queryListByUserRecordId (ProductRecord productRecord);
}
