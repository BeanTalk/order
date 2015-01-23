package com.saituo.order.dao.stock;

import com.saituo.order.entity.stock.BrandBuyerRelation;

/**
 *品牌与采购人员关系表维护
 */
public interface BrandBuyerRelationDao {
	/**
	 * <p>
	 * Description: 查询数据:根据品牌查询采购人员信息
	 * </p>
	 * 
	 * @Method: query
	 * @param StockOrder
	 * @return StockOrder
	 * @throws
	 */
	public BrandBuyerRelation query(BrandBuyerRelation brandBuyerRelation);
}
