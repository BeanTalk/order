package com.saituo.order.service.variable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;

import com.google.common.collect.Maps;
import com.saituo.order.dao.order.ProductDao;
import com.saituo.order.entity.order.Product;

public class ProductInfoWriter {

	private IndexWriter indexWriter;

	private ProductDao productDao;

	public void setIndexWriter(IndexWriter indexWriter) {
		this.indexWriter = indexWriter;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

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
		doc.add(new TextField("spec_value", product.getSpecValue(), Field.Store.YES));
		doc.add(new TextField("unit_value", product.getUnitValue(), Field.Store.YES));
		doc.add(new StringField("catalog_fee", String.valueOf(product.getCatalogFee()), Field.Store.YES));
		try {
			indexWriter.addDocument(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
