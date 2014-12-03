package com.saituo.order.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.saituo.order.entity.order.Product;

/**
 * 产品订单管理
 * 
 * @author marchine
 *
 */
public interface ProductDao {

	public List<Product> searchProductByContext(@Param(value = "filter") Map<String, ?> filter);

	public int count(@Param(value = "filter") Map<String, ?> filter);

	public List<Product> getProductListByProductIds(@Param(value = "productIds") List<Integer> productIds);

	public Product getProductByProductId(@Param(value = "productId") Integer productId);

}
