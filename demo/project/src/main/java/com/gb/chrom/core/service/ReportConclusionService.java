package com.gb.chrom.core.service;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.ReportConclusion;
import com.gb.chrom.model.query.ReportConclusionQuery;
import com.github.pagehelper.PageInfo;

/**
 * 报告结论
 * 
 * @since 1.0
 * @author Summer
 */
public interface ReportConclusionService {

	/**
	 * 增加报告结论
	 * 
	 * @param conclusion
	 * @return
	 */
	public boolean addReportConclusion(ReportConclusion conclusion);

	/**
	 * 删除报告结论
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteReportConclusion(long id);

	/**
	 * 更新报告结论
	 * 
	 * @param conclusion
	 * @return
	 */
	public boolean updateReportConclusion(ReportConclusion conclusion);

	/**
	 * 根据ID查询报告结论
	 * 
	 * @param id
	 * @return
	 */
	public ReportConclusion queryReportConclusion(long id);

	/**
	 * 查询报告结论列表
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	public PageInfo<ReportConclusion> queryReportConclusionForPagingList(ReportConclusionQuery query);

}
