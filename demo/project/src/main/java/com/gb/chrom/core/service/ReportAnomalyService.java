package com.gb.chrom.core.service;

import com.gb.chrom.model.ReportAnomaly;
import com.gb.chrom.model.query.ReportAnomalyQuery;
import com.github.pagehelper.PageInfo;

/**
 * 报告异常
 * 
 * @since 1.0
 * @author Summer
 */
public interface ReportAnomalyService {

	/**
	 * 增加报告结果异常
	 * 
	 * @param anomaly
	 * @return
	 */
	public boolean addReportAnomaly(ReportAnomaly anomaly);

	/**
	 * 删除报告结果异常
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteReportAnomaly(long id);

	/**
	 * 更新报告结果异常
	 * 
	 * @param anomaly
	 * @return
	 */
	public boolean updateReportAnomaly(ReportAnomaly anomaly);

	/**
	 * 根据ID查询报告结果异常
	 * 
	 * @param id
	 * @return
	 */
	public ReportAnomaly queryReportAnomaly(long id);

	/**
	 * 查询报告结果异常列表
	 * 
	 * @param query
	 * @return
	 */
	public PageInfo<ReportAnomaly> queryReportAnomalyForPagingList(ReportAnomalyQuery query);

}
