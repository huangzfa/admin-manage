package com.duobei.common.cache;

import java.io.Serializable;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class CacheHelper {
	
	private Ehcache cache;

	public CacheHelper() {
	}
	public CacheHelper(Ehcache cache) {
		this.cache = cache;
	}

	public static CacheHelper generateCacheHelper(Ehcache cache) {
		return new CacheHelper(cache);
	}

	public void put(Serializable key, Serializable value) {
		Element element = new Element(key, value);
		cache.put(element);
	}

	public void put(Element element) {
		cache.put(element);
	}

	public Serializable getVal(Serializable key) throws CacheException {
		Element element = cache.get(key);
		if (element == null)
			return null;
		return element.getValue();
	}

	public Element get(Serializable key) throws CacheException {
		return cache.get(key);
	}
	
	public boolean remove(Serializable key){
		return cache.remove(key);
	}
	public boolean isKeyInCache(Serializable key){
		return cache.isKeyInCache(key);
	}

	public void removeAll() {
		cache.removeAll();
	}
	public Ehcache getCache() {
		return cache;
	}
	public void setCache(Ehcache cache) {
		this.cache = cache;
	}
}
