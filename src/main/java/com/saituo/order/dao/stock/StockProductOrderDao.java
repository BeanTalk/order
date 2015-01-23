package com.saituo.order.dao.stock;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.saituo.order.entity.stock.StockProductOrder;
/**
 * 备货产品订单信息表维护
 */
public interface StockProductOrderDao {

	/**
	 * <p>
	 * Description: 新增数据
	 * </p>
	 * 
	 * @Method: insert
	 * @param StockProductOrder
	 * @return void
	 * @throws
	 */
	public void insert(StockProductOrder stockProductOrder);

	/**
	 * <p>
	 * Description: 更新数据
	 * </p>
	 * 
	 * @Method: update
	 * @param StockProductOrder
	 * @return void
	 * @throws
	 */
	public void update(StockProductOrder stockProductOrder);

	/**
	 * <p>
	 * Description: 查询数据:根据备货产品订单编码查询
	 * </p>
	 * 
	 * @Method: query
	 * @param StockProductOrder
	 * @return StockProductOrder
	 * @throws
	 */
	public StockProductOrder query(StockProductOrder stockProductOrder);

	/**
	 * <p>
	 * Description: 查询数据:根据备货订单编码查询
	 * </p>
	 * 
	 * @Method: query
	 * @param StockProductOrder
	 * @return StockProductOrder
	 * @throws
	 */
	public List<StockProductOrder> queryListByStockOrderId(
			@Param(value = "stockProductOrder") StockProductOrder stockProductOrder,
			@Param(value = "filter") Map<String, Object> filter);

	/**
	 * <p>
	 * Description: 查询数据:根据备货订单编码查询
	 * </p>
	 * 
	 * @Method: query
	 * @param StockProductOrder
	 * @return StockProductOrder
	 * @throws
	 */
	public List<StockProductOrder> queryAllListByStockOrderId(StockProductOrder stockProductOrder);

	/**
	 * <p>
	 * Description: 查询数据:根据备货订单编码查询
	 * </p>
	 * 
	 * @Method: query
	 * @param StockProductOrder
	 * @return StockProductOrder
	 * @throws
	 */
	public int queryCountByStockOrderId(StockProductOrder stockProductOrder);

	/**
	 * <p>
	 * Description: 删除数据
	 * </p>
	 * 
	 * @Method: delete
	 * @param StockProductOrder
	 * @return void
	 * @throws
	 */
	public void delete(StockProductOrder stockProductOrder);

}
