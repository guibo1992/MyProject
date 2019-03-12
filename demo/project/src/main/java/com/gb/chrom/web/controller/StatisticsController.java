package com.gb.chrom.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.AnalysisWorkStatisticsService;
import com.gb.chrom.model.AnalysisWorkStatistics;
import com.gb.chrom.model.query.StatisticsQuery;
import com.gb.chrom.web.result.PaginatorJsonResult;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 
 * @author Danson
 * 
 *         Created by 2018年5月13日
 * @since
 */
@Controller
@RequestMapping(value = "/work/summary")
public class StatisticsController {

	@Autowired
	private AnalysisWorkStatisticsService analysisWorkStatisticsService;

	@RequestMapping(value = "/list.html")
	public String toStatisticsWorkList(HttpServletRequest request) {
		return "view/work-statistics";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult getAnalysisWorkStatisticsList(StatisticsQuery query) {
		PageInfo<AnalysisWorkStatistics>  pageInfo = analysisWorkStatisticsService.queryAnalysisWorkStatisticsForList(query);
		return PaginatorJsonResult.getPaginatorResult(pageInfo);
	}

	@RequestMapping(value = "/analysis-count")
	public @ResponseBody List<AnalysisWorkStatistics> getAnalysisCountStatisticsList(StatisticsQuery query) {
		return analysisWorkStatisticsService.queryAnalysisWorkStatisticsForList(query).getList();
	}
	
}
