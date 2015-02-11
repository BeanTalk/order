package com.saituo.order.service.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saituo.order.dao.user.ProductRecordDao;
import com.saituo.order.entity.user.ProductRecord;

@Service
public class ProductRecordService {

	@Autowired
	private ProductRecordDao productRecordDao;

	public List<ProductRecord> getProductBrandLists(Map<String, Object> filter) {
		ProductRecord productRecord = new ProductRecord();
		productRecord.setUserRecordId(Integer.valueOf(String.valueOf(filter.get("userRecordId"))));
		return productRecordDao.queryListByUserRecordId(productRecord);
	}
}
