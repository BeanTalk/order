package com.saituo.order.commons.lucene;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Value;

public class OrderIndexWriter {

	private static final Logger logger = Logger.getLogger("Indexer");

	private IndexWriter writer;

	@Value("${config.search.hitperpage}")
	private String directory;// 索引所在目录

	public void Indexer() throws IOException {
		File file = new File(directory);
		Directory directory = FSDirectory.open(file);
		if (directory.fileExists(IndexWriter.WRITE_LOCK_NAME)) {
			directory.clearLock(IndexWriter.WRITE_LOCK_NAME);
			logger.warning("Existing write.lock at ["
					+ directory
					+ "] has been found and removed. This is a likely result of non-gracefully terminated server. Check for index discrepancies!");
		}
		directory.close();
		
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_40, new StandardAnalyzer(Version.LUCENE_40));
		
		writer = new IndexWriter(directory, new StandardAnalyzer(Version.LUCENE_30), IndexWriter.MaxFieldLength.UNLIMITED);
	}
	protected Document getDocument(String name, String contact) throws Exception {
		Document doc = new Document();
		doc.add(new Field("name", name, // 8
				Field.Store.YES, Field.Index.NOT_ANALYZED));// 8
		doc.add(new Field("contact", contact, Field.Store.YES, Field.Index.NOT_ANALYZED)); // 7
		return doc;
	}

	/**
	 * @param oldName
	 * @param oldContact
	 * @param name
	 * @param contact
	 * @throws Exception
	 * 
	 * @purpose 修改某人联系方式,用不着，毕竟在公司，邮箱都是公司的且唯一
	 */
	public void updateIndex(String oldName, String oldContact, String name, String contact) throws Exception {
		Document doc = getDocument(name, contact);
		writer.updateDocument(new Term(oldName, oldContact), doc);
	}

	/**
	 * @param name
	 * @param contact
	 * @throws CorruptIndexException
	 * @throws IOException
	 * @throws Exception
	 * 
	 * @purpose 增加联系方式
	 */
	public void addIndex(String name, String contact) throws CorruptIndexException, IOException, Exception {
		writer.addDocument(getDocument(name, contact));
	}

	/**
	 * @throws IOException
	 * 
	 * @purpose 如果不显示调用commit，则需要调用这个来提交
	 */
	public void close() throws IOException {
		writer.close();
	}

	public void commit() throws Exception {
		writer.commit();
	}
}
