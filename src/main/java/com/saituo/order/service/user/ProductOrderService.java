package com.saituo.order.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saituo.order.dao.user.ProductOrderDao;
import com.saituo.order.entity.user.ProductOrder;

@Service
@Transactional
public class ProductOrderService {

	@Autowired
	private ProductOrderDao productOrderDao;

	public List<ProductOrder> getProductOrderList(Map<String, ?> filter) {
		return productOrderDao.queryListByCondition(filter);
	}
	
	public Integer getProductOrderCount(Map<String, ?> filter) {
		return productOrderDao.queryCountByCondition(filter);
	}
}
