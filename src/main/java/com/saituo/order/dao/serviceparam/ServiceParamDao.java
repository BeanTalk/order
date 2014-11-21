package com.saituo.order.dao.serviceparam;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 参数信息管理
 */
public interface ServiceParamDao {
	/**
	 * 获取产品品牌表列表
	 */
	public List<Map<String, String>> getProductBrandList();
	
	/**
	 * 获取产品信息
	 * 
	 * @param id
	 *            产品编码 ID
	 * 
	 * @return 组实体 Map
	 */
	public Map<String, Object> getProduct(@Param("productId") Long productId);
	
	/**
	 * 获取供应商列表
	 */
	public List<Map<String, String>> getSupplierList();
	
	/**
	 * 获取供应商
	 */
	public List<Map<String, String>> getSupplier(@Param("supplierId") Long supplierId);

}
