package com.gb.chrom.config.cache;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月23日
 * @since
 */
@Component
public class ConfigurationCache {

	private static Logger logger = LoggerFactory.getLogger(ConfigurationCache.class);

	private static final String SYS_CONFIG_CACHE_NAME = "sys-config-cache";

	@Autowired
	private CacheManager cacheCacheManager;

	private Cache cache;

	@PostConstruct
	public void init() {
		cache = cacheCacheManager.getCache(SYS_CONFIG_CACHE_NAME);
		cache.clear();
		logger.info("Sys config cache init success.");
	}

	public <K, V> V getCache(K k, Class<V> clazz) {
		if (null == cache) {
			cache = cacheCacheManager.getCache(SYS_CONFIG_CACHE_NAME);
		}
		return cache.get(k, clazz);
	}

	public <K, V> void putCache(K k, V v) {
		if (null == cache) {
			cache = cacheCacheManager.getCache(SYS_CONFIG_CACHE_NAME);
		}
		cache.put(k, v);
	}

}
