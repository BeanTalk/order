package com.saituo.order.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.entity.user.Address;
import com.saituo.order.service.ServiceTestCaseSupport;

public class AddressServiceTestUnit extends ServiceTestCaseSupport {
	@Autowired
	private AddressService addressService;
	//@Test
	public void insert() {
		Map<String, Object> filter =new HashMap<String, Object>();
		filter.put("receiptAddress","111" );
		filter.put("receiptPerson","sss" );
		filter.put("contactPhone","133311112222" );
		filter.put("invoiceCaput", "asasd");
		filter.put("invoiceRequire", "asasdasasd");
		filter.put("otherRequire","aaaa" );
		//SessionVariable.getCurrentSessionVariable().getUser().put("id", "admin");
		addressService.insert(filter);
	}

	//@Test
	public void update() {
		Map<String, Object> filter =new HashMap<String, Object>();
		filter.put("receiptAddress","222" );
		filter.put("receiptPerson","aaa" );
		filter.put("contactPhone","1890222" );
		filter.put("invoiceCaput", "bbbb");
		filter.put("invoiceRequire", "xxx");
		filter.put("otherRequire","eeeee" );
		filter.put("addressId",1l);
		addressService.update(filter);
	}
	//@Test
	public void delete() {
		Map<String, Object> filter =new HashMap<String, Object>();
		filter.put("addressId",2l);
		addressService.delete(filter);
	}
	//@Test
	public void query() {
		Map<String, Object> filter =new HashMap<String, Object>();
			filter.put("addressId",3l);
			Address address= addressService.query(filter);
			System.out.println("return:"+address.getReceiptPerson());
		}
	@Test
	public void queryList() {
		Map<String, Object> filter =new HashMap<String, Object>();
		filter.put("userId","123");
		List<Address> list= addressService.queryList(filter);
		for (Address a : list) {
			System.out.println("return:"+ a.getAddressId());
		}
		
	}
}
