package com.saituo.order.service.variable;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProductInfoSearcher {

	@Autowired
	private IndexSearcher indexSearcher;

	@Autowired
	private Analyzer luceneAnalyzer;

	@Value("${config.search.hitperpage}")
	private int hitsPerPage;

	// public void searcher(String querystr) {
	//
	// try {
	// String[] queryStrs = {querystr};
	// // 待查找字符串对应的字段
	// String[] fields = {"product_name"};
	// // Occur.MUST表示对应字段必须有查询值， Occur.MUST_NOT 表示对应字段必须没有查询值
	// Occur[] occ = {Occur.MUST};
	//
	// Query query = MultiFieldQueryParser.parse(Version.LUCENE_40, queryStrs,
	// fields, occ, luceneAnalyzer);
	// TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage,
	// true);
	//
	// indexSearcher.search(query, collector);
	// ScoreDoc[] hits = collector.topDocs().scoreDocs;
	//
	// System.out.println("dd:"+hits.length);
	//
	// for (int i = 0; i < hits.length; ++i) {
	// int docId = hits[i].doc;
	// Document d = indexSearcher.doc(docId);
	// System.out.println((i + 1) + ". " + d.get("product_name") + "\t" +
	// d.get("product_id"));
	// }
	// } catch (Exception ex) {
	// }
	// }

	public void searcher(String querystr, int start) {
		try {
			// 待查找字符串对应的字段
			String[] fields = {"product_num", "product_name"};
			// Occur.MUST表示对应字段必须有查询值， Occur.MUST_NOT 表示对应字段必须没有查询值
			Occur[] occ = {Occur.SHOULD, Occur.SHOULD};

			Query query = MultiFieldQueryParser.parse(Version.LUCENE_40, querystr, fields, occ, luceneAnalyzer);
			TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);

			indexSearcher.search(query, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;

			System.out.println("dd:" + hits.length);

			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				Document d = indexSearcher.doc(docId);
				System.out.println((i + 1) + ". " + d.get("product_name") + "\t" + d.get("product_id"));
			}
		} catch (Exception ex) {
		}
	}

	// public void searcher(String querystr) {
	// try {
	// Query query = new QueryParser(Version.LUCENE_40, "product_name",
	// luceneAnalyzer).parse(querystr);
	// TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage,
	// true);
	//
	// indexSearcher.search(query, collector);
	// ScoreDoc[] hits = collector.topDocs().scoreDocs;
	//
	// for (int i = 0; i < hits.length; ++i) {
	// int docId = hits[i].doc;
	// Document d = indexSearcher.doc(docId);
	// System.out.println((i + 1) + ". " + d.get("product_name") + "\t" +
	// d.get("product_num"));
	// }
	// } catch (Exception ex) {
	// }
	// }
}
