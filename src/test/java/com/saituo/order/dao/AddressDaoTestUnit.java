package com.saituo.order.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.dao.user.AddressDao;
import com.saituo.order.entity.user.Address;
import com.saituo.order.service.ServiceTestCaseSupport;

public class AddressDaoTestUnit extends ServiceTestCaseSupport{

	@Autowired
	private AddressDao addressDao;
	@Test
	public void addAddress() {
		Address address=new Address();
		address.setContactPhone("13304110411");
		address.setInvoiceCaput("萨达萨达");
		address.setInvoiceRequire("发票要求");
		address.setOtherRequire("其他要求");
		address.setReceiptAddress("地址");
		address.setReceiptPerson("tester");
		address.setUserId("333");
		addressDao.insert(address);
		System.out.println("id:" + address.getAddressId());
	}
	
	//@Test
	public void updateAddress() {
		Address address=new Address();
		address.setAddressId(1l);
		address.setContactPhone("13304220422");
		address.setInvoiceCaput("test");
		address.setInvoiceRequire("fapiaoyaoqiu");
		address.setOtherRequire("qitayaoqiu");
		address.setReceiptAddress("dizhi");
		address.setReceiptPerson("tester");
		addressDao.update(address);
		System.out.println("id:" + address.getAddressId());
	}
	
	//@Test
	public void deleteAddress() {
		Address address=new Address();
		address.setAddressId(3l);
		addressDao.delete(address);
		System.out.println("id:" + address.getAddressId());
	}
	
	//@Test
	public void queryAddress() {
		Address address=new Address();
		address.setAddressId(1l);
		Address	address1 = addressDao.query(address);
		System.out.println("id:" + address1.getUserId());
	}
	//@Test
	public void  queryListAddress() {
		Address address=new Address();
		address.setUserId("333");
		List<Address>	address1 = addressDao.queryList(address);
		for (Address a : address1) {
			System.out.println(a.getAddressId());
		}
	}

}
