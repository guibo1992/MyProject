package com.gb.chrom.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gb.chrom.Application;
import com.gb.chrom.config.cache.ConfigurationCache;
import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.SerialNumberRuleMapper;
import com.gb.chrom.core.service.SerialNumberRuleService;
import com.gb.chrom.model.SerialNumberRule;

/**
 * @since 1.0
 * @author Summer
 */
@Service
public class SerialNumberRuleServiceImpl implements SerialNumberRuleService {

	private static final Logger logger = LoggerFactory.getLogger(SerialNumberRuleService.class);

	@Autowired
	private SerialNumberRuleMapper serialSerialNumberRuleMapper;

	@Autowired
	private ConfigurationCache shareConfigCache;


	@Transactional
	@Override
	public boolean setSerialNumberRule(SerialNumberRule rule) {
		try {
			serialSerialNumberRuleMapper.saveSerialNumberRule(rule);
			shareConfigCache.putCache(Application.SPM_SERIAL_NO_RULE_ID, rule);

			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during save serial number rule :", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean updateSerialNumberRule(SerialNumberRule rule) {
		try {
			int affect = serialSerialNumberRuleMapper.updateSerialNumberRule(rule);
			if (affect > 0) {
				shareConfigCache.putCache(Application.SPM_SERIAL_NO_RULE_ID, rule);

				return true;
			}
		} catch (MapperException e) {
			logger.error("Exception occurred during update serial number rule :", e);
		}
		return false;
	}

	@Override
	public SerialNumberRule getSerialNumberRule() {
		try {
			SerialNumberRule rule = shareConfigCache.getCache(Application.SPM_SERIAL_NO_RULE_ID, SerialNumberRule.class);
			if (null == rule) {
				rule = serialSerialNumberRuleMapper.findSerialNumberRule();
				shareConfigCache.putCache(Application.SPM_SERIAL_NO_RULE_ID, rule);
			}

			return rule;
		} catch (MapperException e) {
			logger.error("Exception occurred during query serial number rule :", e);
		}
		return null;
	}

}
