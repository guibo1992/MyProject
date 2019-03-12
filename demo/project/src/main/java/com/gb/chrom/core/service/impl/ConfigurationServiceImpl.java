package com.gb.chrom.core.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gb.chrom.Application;
import com.gb.chrom.config.cache.ConfigurationCache;
import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.ConfigurationMapper;
import com.gb.chrom.core.service.ConfigurationService;
import com.gb.chrom.model.Configuration;

/**
 * @since 1.0
 * @author Summer
 */
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	private static final Logger logger = LoggerFactory.getLogger(ConfigurationService.class);

	@Autowired
	private ConfigurationCache shareConfigCache;
	@Autowired
	private ConfigurationMapper configitalMapper;

	@Transactional
	@Override
	public boolean setConfiguration(Configuration config) {
		try {
			configitalMapper.deleteConfigurationByIdcode(config.getIdCode());
			configitalMapper.saveConfiguration(config);
			shareConfigCache.putCache(Application.SYS_CONFIG_CACHE_ID, config);
			
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			session.setAttribute(Application._SYSTEM_CONFIG_ID, getConfiguration());

			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during save config :", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Configuration getConfiguration() {
		try {
			Configuration config = shareConfigCache.getCache(Application.SYS_CONFIG_CACHE_ID, Configuration.class);
			if (null == config) {
				config = configitalMapper.findConfiguration();
				if (null != config)
					shareConfigCache.putCache(Application.SYS_CONFIG_CACHE_ID, config);
			}

			return config;
		} catch (MapperException e) {
			logger.error("Exception occurred during query config :", e);
			throw new RuntimeException(e);
		}
	}

}
