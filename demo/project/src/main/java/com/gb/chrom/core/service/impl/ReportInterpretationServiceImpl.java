package com.gb.chrom.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.chrom.core.mapper.ReportInterpretationMapper;
import com.gb.chrom.core.service.ReportInterpretationService;
import com.gb.chrom.model.ReportInterpretation;
import com.gb.chrom.model.query.ReportInterpretationQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Summer
 *
 *         2018年8月2日
 */
@Service
public class ReportInterpretationServiceImpl implements ReportInterpretationService {

	private static Logger logger = LoggerFactory.getLogger(ReportInterpretationService.class);

	@Autowired
	private ReportInterpretationMapper reportInterpretationMapper;

	@Override
	public boolean addReportInterpretation(ReportInterpretation interpretation) {
		try {
			reportInterpretationMapper.saveReportInterpretation(interpretation);
			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during save report interpretation :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean deleteReportInterpretation(long id) {
		try {
			reportInterpretationMapper.deleteReportInterpretationById(id);
			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during delete report interpretation :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean updateReportInterpretation(ReportInterpretation interpretation) {
		try {
			reportInterpretationMapper.updateReportInterpretation(interpretation);
			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during update report interpretation :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public ReportInterpretation queryReportInterpretation(long id) {
		try {
			return reportInterpretationMapper.findReportInterpretationById(id);
		} catch (Exception e) {
			logger.error("Exception occurred during query report interpretation by id :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageInfo<ReportInterpretation> queryReportInterpretationForPagingList(ReportInterpretationQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit());
			return reportInterpretationMapper.findReportInterpretationForList(query).toPageInfo();
		} catch (Exception e) {
			logger.error("Exception occurred during query report interpretation for paging list :", e);
		}

		return new PageInfo<>();
	}

}
