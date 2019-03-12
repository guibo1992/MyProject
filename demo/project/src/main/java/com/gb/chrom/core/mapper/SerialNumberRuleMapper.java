package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.SerialNumberRule;

/**
 * 编号规则 Mapper
 * 
 * @author Summer
 *
 *         2017年12月19日
 */
@Mapper
public interface SerialNumberRuleMapper {

	/**
	 * 保存编号规则
	 * 
	 * @param rule
	 * @return
	 * @throws MapperException
	 */
	Integer saveSerialNumberRule(SerialNumberRule rule) throws MapperException;

	/**
	 * 更新编号规则
	 * 
	 * @param rule
	 * @return
	 * @throws MapperException
	 */
	Integer updateSerialNumberRule(SerialNumberRule rule) throws MapperException;

	/**
	 * 查询编号规则
	 * 
	 * @param hcode
	 * @return
	 * @throws MapperException
	 */
	SerialNumberRule findSerialNumberRule() throws MapperException;

}
