package com.saituo.order.dao.stock;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.saituo.order.entity.stock.StockOrder;

/**
 * 备货订单信息表维护
 */
public interface StockOrderDao {

	/**
	 * <p>
	 * Description: 新增数据
	 * </p>
	 * 
	 * @Method: insert
	 * @param StockOrder
	 * @return void
	 * @throws
	 */
	public void insert(StockOrder stockOrder);

	/**
	 * <p>
	 * Description: 更新数据
	 * </p>
	 * 
	 * @Method: update
	 * @param StockOrder
	 * @return void
	 * @throws
	 */
	public void update(StockOrder stockOrder);

	/**
	 * <p>
	 * Description: 查询数据:根据备货客户订单编码查询
	 * </p>
	 * 
	 * @Method: query
	 * @param StockOrder
	 * @return StockOrder
	 * @throws
	 */
	public StockOrder query(StockOrder stockOrder);

	/**
	 * <p>
	 * Description: 查询数据:根据客户编码查询
	 * </p>
	 * 
	 * @Method: query
	 * @param StockOrder
	 * @return  List<StockOrder>
	 * @throws
	 */
	public List<StockOrder> queryList(@Param(value = "stockOrder") StockOrder stockOrder, @Param(value = "filter") Map<String, Object> filter);

	/**
	 * <p>
	 * Description: 查询数据:根据客户编码查询
	 * </p>
	 * 
	 * @Method: query
	 * @param StockOrder
	 * @return StockOrder
	 * @throws
	 */
	public int count(StockOrder stockOrder);

	/**
	 * <p>
	 * Description: 删除数据
	 * </p>
	 * 
	 * @Method: delete
	 * @param StockOrder
	 * @return void
	 * @throws
	 */
	public void delete(StockOrder stockOrder);


}
