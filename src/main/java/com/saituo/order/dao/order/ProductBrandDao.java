package com.saituo.order.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.saituo.order.entity.order.ProductBrand;

public interface ProductBrandDao {

	public List<ProductBrand> getProductBrandList(@Param(value = "filter") Map<String, ?> filter);

}
