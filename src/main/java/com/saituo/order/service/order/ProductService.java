package com.saituo.order.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saituo.order.commons.page.Page;
import com.saituo.order.commons.page.PageRequest;
import com.saituo.order.dao.order.ProductDao;
import com.saituo.order.entity.order.Product;
import com.saituo.order.service.variable.ProductInfoSearcher;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductInfoSearcher productInfoSearcher;

	public Page<Product> searchProduct(PageRequest pageRequest, String search) {
		return productInfoSearcher.searchForPublic(pageRequest, search);
	}

	public List<Product> getProductInfoListByProductId(List<String> productIds) {
		return productDao.getProductListByProductIds(productIds);
	}

	public Product getProductByProductId(Integer productId) {
		return productDao.getProductByProductId(productId);
	}
}
