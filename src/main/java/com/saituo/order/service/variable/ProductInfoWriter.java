package com.saituo.order.service.variable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.saituo.order.dao.order.ProductDao;
import com.saituo.order.entity.order.Product;

@Service
public class ProductInfoWriter {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private Analyzer luceneAnalyzer;

	@Value("${config.search.indexDir}")
	private String indexDir;

	public void init() {

		FSDirectory directory;
		IndexWriter indexWriter = null;
		try {
			directory = OrderLuceneDirectory.getFSDirectory(indexDir, true);
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, luceneAnalyzer);
			indexWriter = new IndexWriter(directory, config);

			Map<String, String> mapData = Maps.newHashMap();
			List<Product> productList = productDao.getProductList(mapData);
			for (Product product : productList) {
				addDoc(indexWriter, product);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (indexWriter != null) {
					indexWriter.commit();
					indexWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void addDoc(IndexWriter indexWriter, Product product) {
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
