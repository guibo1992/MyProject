package com.gb.chrom.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.chrom.core.mapper.ReportAnomalyMapper;
import com.gb.chrom.core.service.ReportAnomalyService;
import com.gb.chrom.model.ReportAnomaly;
import com.gb.chrom.model.query.ReportAnomalyQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Summer
 *
 *         2018年8月2日
 */
@Service
public class ReportAnomalyServiceImpl implements ReportAnomalyService {

	private static Logger logger = LoggerFactory.getLogger(ReportAnomalyService.class);

	@Autowired
	private ReportAnomalyMapper reportAnomalyMapper;

	@Override
	public boolean addReportAnomaly(ReportAnomaly anomaly) {
		try {
			reportAnomalyMapper.saveReportAnomaly(anomaly);
			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during save report anomaly :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean deleteReportAnomaly(long id) {
		try {
			reportAnomalyMapper.deleteReportAnomalyById(id);
			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during delete report anomaly :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean updateReportAnomaly(ReportAnomaly anomaly) {
		try {
			reportAnomalyMapper.updateReportAnomaly(anomaly);
			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during update report anomaly :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public ReportAnomaly queryReportAnomaly(long id) {
		try {
			return reportAnomalyMapper.findReportAnomalyById(id);
		} catch (Exception e) {
			logger.error("Exception occurred during query report anomaly by id :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageInfo<ReportAnomaly> queryReportAnomalyForPagingList(ReportAnomalyQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit());
			return reportAnomalyMapper.findReportAnomalyForList(query).toPageInfo();
		} catch (Exception e) {
			logger.error("Exception occurred during query report anomaly for paging list :", e);
		}

		return new PageInfo<>();
	}

}
