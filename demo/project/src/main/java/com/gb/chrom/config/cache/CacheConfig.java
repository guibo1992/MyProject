package com.gb.chrom.config.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.CacheManager;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月23日
 * @since
 */
@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public EhCacheManagerFactoryBean cacheManagerFactory() {
		EhCacheManagerFactoryBean cacheManagerFactory = new EhCacheManagerFactoryBean();
		cacheManagerFactory.setShared(true);
		return cacheManagerFactory;
	}

	@Bean
	public EhCacheCacheManager cacheCacheManager(CacheManager cacheManager) {
		EhCacheCacheManager cacheCacheManager = new EhCacheCacheManager();
		cacheCacheManager.setCacheManager(cacheManager);
		cacheCacheManager.setTransactionAware(true);
		return cacheCacheManager;
	}

}
