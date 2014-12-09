package com.saituo.order.service.variable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.saituo.order.dao.order.ProductDao;
import com.saituo.order.entity.order.Product;

@Service
public class ProductInfoWriter {

	@Autowired(required = false)
	private IndexWriter indexWriter;

	@Autowired
	private ProductDao productDao;

	public void init() {
		Map<String, String> mapData = Maps.newHashMap();
		List<Product> productList = productDao.getProductList(mapData);

		for (Product product : productList) {
			addDoc(product);
		}
	}

	private void addDoc(Product product) {
		Document doc = new Document();
		doc.add(new TextField("product_allinfo", product.getProductName() + " " + product.getProductNum(),
				Field.Store.YES));
		doc.add(new TextField("product_name", product.getProductName(), Field.Store.YES));
		doc.add(new TextField("product_num", product.getProductNum(), Field.Store.YES));
		doc.add(new IntField("product_id", product.getProductId(), Field.Store.YES));
		try {
			indexWriter.addDocument(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
