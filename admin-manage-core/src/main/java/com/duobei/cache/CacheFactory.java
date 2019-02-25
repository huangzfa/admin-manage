package com.duobei.cache;

import java.util.HashMap;

import net.sf.ehcache.Ehcache;

import com.duobei.common.cache.CacheHelper;
import com.duobei.context.AppContext;

public class CacheFactory {
	
	private static HashMap<String, CacheHelper> cacheMap = new HashMap<String, CacheHelper>();

	public static CacheHelper getCache(String cacheKey) {
		CacheHelper helper=cacheMap.get(cacheKey);
		if (helper == null) {
			helper=CacheHelper.generateCacheHelper((Ehcache) AppContext.getBean(cacheKey));
			cacheMap.put(cacheKey,helper);
		}
		return helper;
	}
	
	public static CacheHelper getSysCache() {
		return getCache("sysCache");
	}
}
