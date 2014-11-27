package com.saituo.order.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.dao.user.ProductOrderDao;
import com.saituo.order.entity.user.Address;
import com.saituo.order.entity.user.ProductOrder;
import com.saituo.order.service.ServiceTestCaseSupport;

public class ProductOrderDaoTestUnit  extends ServiceTestCaseSupport {

	@Autowired
	private ProductOrderDao productOrderDao;
	
	//@Test
	public void addProuductOrder() {
		ProductOrder productOrder=new ProductOrder();
		productOrder.setAreaId("910");
		productOrder.setAuditCd("0");
		productOrder.setIfValid("1");
		productOrder.setInvoiceStatus("1");
		productOrder.setOrderFee(1.0d);
		productOrder.setOrderNum(111l);
		productOrder.setProductId(111l);
		productOrder.setStatusCd("1");
		productOrder.setUserId("1122");
		productOrder.setUserOrderId(111l);
		productOrderDao.insert(productOrder);
		System.out.println("id:" + productOrder.getUserOrderId());
	}
	
	//@Test
	public void updateProuductOrder() {
		ProductOrder productOrder=new ProductOrder();
		productOrder.setAreaId("910");
		productOrder.setAuditCd("0");
		productOrder.setIfValid("1");
		productOrder.setInvoiceStatus("1");
		productOrder.setOrderFee(33.0d);
		productOrder.setOrderNum(10l);
		productOrder.setProductId(111l);
		productOrder.setStatusCd("1");
		productOrder.setUserId("1122");
		productOrder.setUserOrderId(111l);
		productOrder.setRegisterNumber(1l);
		productOrderDao.update(productOrder);
		System.out.println("id:" + productOrder.getUserOrderId());
	}

	//@Test
	public void deleteProuductOrder() {
		ProductOrder productOrder=new ProductOrder();
		productOrder.setRegisterNumber(3l);
		productOrderDao.delete(productOrder);
		System.out.println("id:" + productOrder.getUserOrderId());
	}
	//@Test
	public void queryProuductOrder() {
		ProductOrder productOrder=new ProductOrder();
		productOrder.setRegisterNumber(1l);
		ProductOrder returnproductOrder =productOrderDao.query(productOrder);
		System.out.println("id:" + returnproductOrder.getUserOrderId());
	}
	@Test
	public void  queryListAddress() {
		ProductOrder productOrder=new ProductOrder();
		productOrder.setUserOrderId(111l);
		List<ProductOrder>	list = productOrderDao.queryListByUserOrderId(productOrder);
		for (ProductOrder a : list) {
			System.out.println(a.getRegisterNumber());
		}
	}
}
