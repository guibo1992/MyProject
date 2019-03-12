package com.gb.chrom.core.service;

import com.gb.chrom.model.Configuration;

/**
 * 系统配置 Service
 * 
 * @since 1.0
 * @author Summer
 */
public interface ConfigurationService {

	/**
	 * 保存配置
	 * 
	 * @param config
	 * @return
	 */
	public boolean setConfiguration(Configuration config);

	/**
	 * 查询配置信息
	 * 
	 * @return
	 */
	public Configuration getConfiguration();

}
