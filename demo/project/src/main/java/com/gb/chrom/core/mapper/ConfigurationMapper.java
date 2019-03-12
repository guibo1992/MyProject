package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.Configuration;

/**
 * 系统配置 Mapper
 * 
 * @author Summer
 *
 *         2017年12月19日
 */
@Mapper
public interface ConfigurationMapper {

	/**
	 * 保存配置
	 * 
	 * @param config
	 * @return
	 * @throws MapperException
	 */
	Integer saveConfiguration(Configuration config) throws MapperException;

	/**
	 * 删除配置
	 * 
	 * @param idcode
	 * @return
	 * @throws MapperException
	 */
	Integer deleteConfigurationByIdcode(String idcode) throws MapperException;

	/**
	 * 查询配置
	 * 
	 * @return
	 * @throws MapperException
	 */
	Configuration findConfiguration() throws MapperException;

}
