package com.saituo.order.service.account;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
		productInfoSearcher.searcher("12657S", 1);
	}
}
