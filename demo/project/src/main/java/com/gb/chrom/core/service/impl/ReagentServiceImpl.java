package com.gb.chrom.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.chrom.core.mapper.ReagentMapper;
import com.gb.chrom.core.service.ReagentService;
import com.gb.chrom.model.Reagent;
import com.gb.chrom.model.query.ReagentQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月23日
 * @since
 */
@Service
public class ReagentServiceImpl implements ReagentService {

	private static Logger logger = LoggerFactory.getLogger(ReagentService.class);

	@Autowired
	private ReagentMapper reagentMapper;

	@Override
	public boolean addReagent(Reagent reagent) {
		try {
			reagentMapper.saveReagent(reagent);
			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during save regent: ", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean updateReagent(Reagent reagent) {
		try {
			return reagentMapper.updateReagent(reagent) > 0;
		} catch (Exception e) {
			logger.error("Exception occurred during update regent: ", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Reagent queryReagent(long id) {
		try {
			return reagentMapper.findReagentById(id);
		} catch (Exception e) {
			logger.error("Exception occurred during query regent: ", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageInfo<Reagent> queryReagentForPagingList(ReagentQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit(), query.isCount());
			return reagentMapper.findReagentForList(query).toPageInfo();
		} catch (Exception e) {
			logger.error("Exception occurred during query regent for paging list: ", e);
		}

		return new PageInfo<>();
	}

}
