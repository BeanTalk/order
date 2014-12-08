package com.saituo.order.service.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCacheService {

	@Autowired
	private CacheManager ehCacheManager;

	public Object get(String cacheName, String key) {
		return getEhCache(cacheName, key);
	}

	public void put(String cacheName, String key, Object value) {
		putEhCache(cacheName, key, value);
	}

	public void remove(String cacheName, String key) {
		removeEhCache(cacheName, key);
	}

	private Object getEhCache(String cacheName, String key) {
		Element element = getCache(cacheName).get(key);
		return element == null ? null : element.getObjectValue();
	}

	private void putEhCache(String cacheName, String key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	private void removeEhCache(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}

	/**
	 * 获得Cache，没有则创建
	 */
	private Cache getCache(String cacheName) {
		Cache cache = ehCacheManager.getCache(cacheName);
		if (cache == null) {
			ehCacheManager.addCache(cacheName);
			cache = ehCacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}
}
