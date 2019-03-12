package com.gb.chrom.core.service;

import com.gb.chrom.model.ReportTemplate;
import com.gb.chrom.model.query.ReportTemplateQuery;
import com.github.pagehelper.PageInfo;

/**
 * @since 1.0
 * @author Summer
 */
public interface ReportTemplateService {

	/**
	 * 添加报告模板
	 * 
	 * @param template
	 * @return
	 */
	public boolean addReportTemplate(ReportTemplate template);

	/**
	 * 更新报告模板
	 * 
	 * @param template
	 * @return
	 */
	public boolean updateReportTemplate(ReportTemplate template);

	/**
	 * 删除报告模板
	 * 
	 * @param template
	 * @return
	 */
	public boolean removeReportTemplate(long id);

	/**
	 * 启用报告模板
	 * 
	 * @param template
	 * @return
	 */
	public boolean undeleteReportTemplate(long id);

	/**
	 * 查询报告模板
	 * 
	 * @param name
	 * @return
	 */
	public ReportTemplate queryReportTemplate(String name);

	/**
	 * 查询报告模板
	 * 
	 * @param id
	 * @return
	 */
	public ReportTemplate queryReportTemplate(long id);

	/**
	 * 查询报告模板列表
	 * 
	 * @param query
	 * @return
	 */
	PageInfo<ReportTemplate> queryReportTemplateForPagingList(ReportTemplateQuery query);

}
