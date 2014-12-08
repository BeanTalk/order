package com.saituo.order.service.variable;

import org.apache.lucene.search.IndexSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInfoSearcher {

	@Autowired(required=false)
	private IndexSearcher indexSearcher;
	
	public void searcher(String queryStr){
		indexSearcher.
		
	}
}
