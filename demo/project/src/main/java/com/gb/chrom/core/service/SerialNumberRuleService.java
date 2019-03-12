package com.gb.chrom.core.service;

import com.gb.chrom.model.SerialNumberRule;

/**
 * 系统配置项
 * 
 * @since
 * @author Summer
 */
public interface SerialNumberRuleService {

	/**
	 * set serial rule
	 * 
	 * @param rule
	 * @return
	 */
	public boolean setSerialNumberRule(SerialNumberRule rule);

	/**
	 * update serial rule
	 * 
	 * @param rule
	 * @return
	 */
	public boolean updateSerialNumberRule(SerialNumberRule rule);

	/**
	 * get serial rule
	 * 
	 * @return
	 */
	public SerialNumberRule getSerialNumberRule();

}
