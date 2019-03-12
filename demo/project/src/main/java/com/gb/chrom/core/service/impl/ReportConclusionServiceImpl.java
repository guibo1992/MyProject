package com.gb.chrom.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.chrom.core.mapper.ReportConclusionMapper;
import com.gb.chrom.core.service.ReportConclusionService;
import com.gb.chrom.model.ReportConclusion;
import com.gb.chrom.model.query.ReportConclusionQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Summer
 *
 *         2018年8月2日
 */
@Service
public class ReportConclusionServiceImpl implements ReportConclusionService {

	private static Logger logger = LoggerFactory.getLogger(ReportConclusionService.class);

	@Autowired
	private ReportConclusionMapper reportConclusionMapper;

	@Override
	public boolean addReportConclusion(ReportConclusion conclusion) {
		try {
			reportConclusionMapper.saveReportConclusion(conclusion);
			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during save report conclusion :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean deleteReportConclusion(long id) {
		try {
			reportConclusionMapper.deleteReportConclusionById(id);
			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during delete report conclusion :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean updateReportConclusion(ReportConclusion conclusion) {
		try {
			reportConclusionMapper.updateReportConclusion(conclusion);
			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during update report conclusion :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public ReportConclusion queryReportConclusion(long id) {
		try {
			return reportConclusionMapper.findReportConclusionById(id);
		} catch (Exception e) {
			logger.error("Exception occurred during query report conclusion by id :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageInfo<ReportConclusion> queryReportConclusionForPagingList(ReportConclusionQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit());
			return reportConclusionMapper.findReportConclusionForList(query).toPageInfo();
		} catch (Exception e) {
			logger.error("Exception occurred during query report conclusion for paging list :", e);
		}

		return new PageInfo<>();
	}

}
