package com.saituo.order.service.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCacheService {

	private static final String SYS_CACHE = "orderBussionss";

	private static final String ROLES = "roles";
	private static final String PERMISSIONS = "perms";
	private static final String GROUPS = "group";
	private static final String USER = "user";

	@Autowired
	private CacheManager cacheManager;

	public Object get(String key) {
		return get(SYS_CACHE, key);
	}

	public void put(String key, Object value) {
		put(SYS_CACHE, key, value);
	}

	public void remove(String key) {
		remove(SYS_CACHE, key);
	}

	private Object get(String cacheName, String key) {
		Element element = getCache(cacheName).get(key);
		return element == null ? null : element.getObjectValue();
	}

	private void put(String cacheName, String key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	private void remove(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}

	/**
	 * 获得Cache，没有则创建
	 */
	private Cache getCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}
}
