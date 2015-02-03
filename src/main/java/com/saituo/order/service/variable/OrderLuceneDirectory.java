package com.saituo.order.service.variable;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.NativeFSLockFactory;

public class OrderLuceneDirectory {

	public static FSDirectory getFSDirectory(String indexDir, Boolean isCreate) {

		File location = new File(indexDir);
		if (!location.exists() || !location.canRead()) {
			System.out.println("Creating directory: '" + location.getAbsolutePath() + "'");
			location.mkdirs();
		}

		FSDirectory fsDirectory = null;
		try {
			fsDirectory = FSDirectory.open(location, new NativeFSLockFactory());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (isCreate) {
			// 将之前的索引文件删除
			try {
				for (String file : FSDirectory.listAll(location)) {
					fsDirectory.deleteFile(file);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			return FSDirectory.open(location, new NativeFSLockFactory());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
