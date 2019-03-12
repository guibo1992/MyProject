package com.gb.chrom.core.service;

import com.gb.chrom.model.AnalysisWorkStatistics;
import com.gb.chrom.model.query.StatisticsQuery;
import com.github.pagehelper.PageInfo;

/**
 * @author Summer
 *
 *         2018年8月12日
 */
public interface AnalysisWorkStatisticsService {

	public boolean addAnalysisWorkStatistics(AnalysisWorkStatistics workStatistics);

	public boolean updateAnalysisWorkStatistics(AnalysisWorkStatistics workStatistics);

	public AnalysisWorkStatistics queryAnalysisStatisticsOfMonth(int year, int month);

	public PageInfo<AnalysisWorkStatistics> queryAnalysisWorkStatisticsForList(StatisticsQuery query);

}
