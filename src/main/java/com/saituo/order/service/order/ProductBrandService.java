package com.saituo.order.service.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.saituo.order.dao.order.ProductBrandDao;
import com.saituo.order.entity.order.ProductBrand;

@Service
public class ProductBrandService {

	@Autowired
	private ProductBrandDao productBrandDao;

	public Map<String, String> getProductBrandLists(Map<String, Object> filter) {
		List<ProductBrand> productBrandList = productBrandDao.getProductBrandList(filter);

		Map<String, String> resultMap = Maps.newHashMap();
		for (ProductBrand productBrand : productBrandList) {
			resultMap.put(String.valueOf(productBrand.getBrandId()), productBrand.getBrandName());
		}
		return resultMap;
	}

	public Map<String, String> getProductBrandAllList() {
		List<ProductBrand> productBrandList = productBrandDao.getProductBrandAllList();

		Map<String, String> resultMap = Maps.newHashMap();
		for (ProductBrand productBrand : productBrandList) {
			resultMap.put(String.valueOf(productBrand.getBrandId()), productBrand.getBrandName());
		}
		return resultMap;
	}
}
