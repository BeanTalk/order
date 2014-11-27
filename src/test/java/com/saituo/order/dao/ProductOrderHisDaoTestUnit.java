package com.saituo.order.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.dao.user.ProductOrderHisDao;
import com.saituo.order.entity.user.ProductOrderHis;
import com.saituo.order.service.ServiceTestCaseSupport;

public class ProductOrderHisDaoTestUnit extends ServiceTestCaseSupport {
	@Autowired
	private ProductOrderHisDao productOrderHisDao;
	//@Test
	public void addProductOrderHis() {
		ProductOrderHis productOrderHis=new ProductOrderHis();
		productOrderHis.setAcceptPerson("admin");
		productOrderHis.setOrderResult("2");
		productOrderHis.setRegisterNumber(111l);
		productOrderHisDao.insert(productOrderHis);
		System.out.println("id:" +productOrderHis.getId());
		
	}
	@Test
	public void  queryListProductOrderHis() {
		ProductOrderHis productOrderHis=new ProductOrderHis();
		productOrderHis.setRegisterNumber(111l);
		List<ProductOrderHis>	list = productOrderHisDao.queryList(productOrderHis);
		for (ProductOrderHis a : list) {
			System.out.println(a.getAcceptPerson());
		}
	}
}
