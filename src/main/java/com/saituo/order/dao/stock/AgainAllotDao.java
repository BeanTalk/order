package com.saituo.order.dao.stock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.saituo.order.entity.stock.AgainAllot;

/**
 * 备货产品重分配表维护
 */
public interface AgainAllotDao {
	/**
	 * <p>
	 * Description: 新增数据
	 * </p>
	 * 
	 * @Method: insert
	 * @param AgainAllot
	 * @return void
	 * @throws
	 */
	public void insert(AgainAllot againAllot);
	/**
	 * <p>
	 * Description: 查询数据:根据备货订单编码查询
	 * </p>
	 * 
	 * @Method: query
	 * @param AgainAllot
	 * @return AgainAllot
	 * @throws
	 */
	public List<AgainAllot> query(@Param("stockNumber") String stockNumber);
}
