package com.gb.chrom.core.service;

import com.gb.chrom.model.ReportInterpretation;
import com.gb.chrom.model.query.ReportInterpretationQuery;
import com.github.pagehelper.PageInfo;

/**
 * 报告结果解释
 * 
 * @since 1.0
 * @author Summer
 */
public interface ReportInterpretationService {

	/**
	 * 增加报告结果解释
	 * 
	 * @param interpretation
	 * @return
	 */
	public boolean addReportInterpretation(ReportInterpretation interpretation);

	/**
	 * 删除报告结果解释
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteReportInterpretation(long id);

	/**
	 * 更新报告结果解释
	 * 
	 * @param interpretation
	 * @return
	 */
	public boolean updateReportInterpretation(ReportInterpretation interpretation);

	/**
	 * 根据ID查询报告结果解释
	 * 
	 * @param id
	 * @return
	 */
	public ReportInterpretation queryReportInterpretation(long id);

	/**
	 * 查询报告结果解释列表
	 * 
	 * @param query
	 * @return
	 */
	public PageInfo<ReportInterpretation> queryReportInterpretationForPagingList(ReportInterpretationQuery query);

}
