package com.gb.chrom.core.service;

import com.gb.chrom.model.AnalysisReport;
import com.gb.chrom.model.AnalysisReportAudit;
import com.gb.chrom.model.query.AnalysisReportAuditQuery;
import com.gb.chrom.model.query.AnalysisReportQuery;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author Summer 2018-08-06
 */
public interface ReportService {

	public boolean submitAnalysisReport(AnalysisReport report);

	public boolean resubmitAnalysisReport(AnalysisReport report);

	public AnalysisReport queryAnalysisReport(long id);

	public PageInfo<AnalysisReport> queryAnalysisReportForPagingList(AnalysisReportQuery query);

	/**
	 * 审核分析报告
	 * 
	 * @param reportAudit
	 * @return
	 */
	public boolean auditAnalysisReport(AnalysisReportAudit reportAudit);

	/**
	 * 查询分析报告审核记录
	 * 
	 * @param query
	 * @return
	 */
	public PageInfo<AnalysisReportAudit> queryAnalysisReportAuditForPagingList(AnalysisReportAuditQuery query);

}
