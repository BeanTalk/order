package com.saituo.order.service.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saituo.order.commons.page.Page;
import com.saituo.order.commons.page.PageRequest;
import com.saituo.order.dao.order.ProductDao;
import com.saituo.order.entity.order.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	public Page<Product> searchProduct(PageRequest pageRequest, String search) {

		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("search", search);

		long total = productDao.count(filter);
		filter.putAll(pageRequest.getMap());
		List<Product> content = productDao.searchProductByContext(filter);
		return new Page<Product>(pageRequest, content, total);
	}

}
