package com.saituo.order.service.account;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.saituo.order.commons.page.Page;
import com.saituo.order.commons.page.PageRequest;
import com.saituo.order.entity.order.Product;
import com.saituo.order.service.ServiceTestCaseSupport;
import com.saituo.order.service.variable.ProductInfoSearcher;

public class SearcherTestUnit extends ServiceTestCaseSupport {

	// @Autowired
	// private ProductInfoWriter productInfoWriter;
	//
	// @Test
	// public void testinit() {
	// productInfoWriter.init();
	// }

	@Autowired
	private ProductInfoSearcher productInfoSearcher;

	@Test
	public void testread() {
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageNumber(1);
		pageRequest.setPageSize(10);
		Page<Product> list = productInfoSearcher.searchForPublic(pageRequest, "Peptide");
		System.out.println(list.getContent().size());
	}
}
