package com.saituo.order.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.saituo.order.entity.order.Product;

public interface ProductDao {

	public List<Product> searchProductByContext(@Param(value = "filter") Map<String, ?> filter);

	public int count(@Param(value = "filter") Map<String, ?> filter);

	public List<Product> getProductListByProductIds(@Param(value = "productIds") List<Integer> productIds);

}
