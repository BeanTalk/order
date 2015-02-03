package com.saituo.order.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.saituo.order.entity.user.OrderComplaint;

public interface OrderComplaintDao {

	/**
	 * <p>
	 * Description: 新增数据
	 * </p>
	 * 
	 * @Method: insert
	 * @param OrderComplaint
	 * @return void
	 * @throws
	 */
	public void insert(OrderComplaint orderComplaint);
	/**
	 * <p>
	 * Description: 更新数据
	 * </p>
	 * 
	 * @Method: insert
	 * @param OrderComplaint
	 * @return void
	 * @throws
	 */
	public void update(OrderComplaint orderComplaint);

	/**
	 * <p>
	 * Description: 查询数据:根据客户编码查询
	 * </p>
	 * 
	 * @Method: query
	 * @param UserRecord
	 * @return UserRecord
	 * @throws
	 */
	public int count(@Param(value = "orderComplaint")OrderComplaint orderComplaint,
			@Param(value = "filter") Map<String, Object> filter);
	
	/**
	 * <p>
	 * Description: 查询数据:地市、投诉状态、客户编号、投诉类型进行查询
	 * </p>
	 * 
	 * @Method: query
	 * @param UserRecord
	 * @return  List<UserRecord> 
	 * @throws
	 */
	public List<OrderComplaint> queryList(@Param(value = "orderComplaint") OrderComplaint orderComplaint,
			@Param(value = "filter") Map<String, Object> filter);
}
