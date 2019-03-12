package com.gb.chrom.core.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.AnalysisWorkStatisticsMapper;
import com.gb.chrom.core.mapper.UserMapper;
import com.gb.chrom.core.service.AnalysisWorkStatisticsService;
import com.gb.chrom.model.AnalysisWorkStatistics;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.StatisticsQuery;
import com.gb.chrom.model.query.UserQuery;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * @author Summer
 *
 *         2018年8月13日
 */
@Service
public class AnalysisWorkStatisticsServiceImpl implements AnalysisWorkStatisticsService {

	private static Logger logger = LoggerFactory.getLogger(AnalysisWorkStatisticsService.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AnalysisWorkStatisticsMapper analysisWorkStatisticsMapper;

	@Transactional
	public boolean restatisticsAnalysisWork(AnalysisWorkStatistics statis) {
//		try {
//			AnalysisWorkStatistics statistics = analysisWorkStatisticsMapper.findAnalysisStatisticsOfMonth(statis.getUserId(), statis.getStatisYear(),
//					statis.getStatisMonth());
//
//			if (null != statistics) {
//				analysisWorkStatisticsMapper.updateAnalysisWorkStatistics(statis);
//			} else {
//				analysisWorkStatisticsMapper.saveAnalysisWorkStatistics(statis);
//			}

		return true;
//		} catch (MapperException e) {
//
//			throw new RuntimeException(e);
//		}

	}

	@Override
	public boolean addAnalysisWorkStatistics(AnalysisWorkStatistics workStatistics) {
		return false;
	}

	@Override
	public boolean updateAnalysisWorkStatistics(AnalysisWorkStatistics workStatistics) {
		return false;
	}

	@Override
	public AnalysisWorkStatistics queryAnalysisStatisticsOfMonth(int year, int month) {
		return null;
	}

	@Override
	public PageInfo<AnalysisWorkStatistics> queryAnalysisWorkStatisticsForList(StatisticsQuery query) {

		try (Page<AnalysisWorkStatistics> page = new Page<>()) {
			UserQuery userQuery = new UserQuery();
			userQuery.setCount(false);
			userQuery.setLimit(Integer.MAX_VALUE);
			userQuery.setType(User.TYPE_OF_ANALYST);
			List<User> userList = userMapper.findUserForList(userQuery);

			AnalysisWorkStatistics statis = null;
			Map<Long, AnalysisWorkStatistics> reportCountMap = analysisWorkStatisticsMapper.findReportCountForMap(query);
			Map<Long, AnalysisWorkStatistics> specimenCountMap = analysisWorkStatisticsMapper.findSpecimenAnalysisCountForMap(query);

			for (User user : userList) {
				statis = new AnalysisWorkStatistics();
				AnalysisWorkStatistics reportCountStatis = reportCountMap.get(user.getId());
				AnalysisWorkStatistics specimenCountStatis = specimenCountMap.get(user.getId());

				statis.setLaboratorian(user);
				if (null != reportCountStatis) {
					statis.setReportedCount(reportCountStatis.getReportedCount());
					statis.setReportRejectCount(reportCountStatis.getReportRejectCount());
				}
				if (null != specimenCountStatis) {
					statis.setAnalysisSpecimenCount(specimenCountStatis.getAnalysisSpecimenCount());
				}
				page.add(statis);
			}

			return page.toPageInfo();
		} catch (MapperException e) {
			logger.error("", e);
		} 

		return new PageInfo<>();
	}

}
