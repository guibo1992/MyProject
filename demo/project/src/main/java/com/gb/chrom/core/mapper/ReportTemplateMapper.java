package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.ReportTemplate;
import com.gb.chrom.model.query.ReportTemplateQuery;
import com.github.pagehelper.Page;

/**
 * @since 1.0
 * @author Summer
 */
@Mapper
public interface ReportTemplateMapper {

	/**
	 * 保存分析报告模板
	 * 
	 * @param template
	 * @return
	 * @throws MapperException
	 */
	Integer saveReportTemplate(ReportTemplate template) throws MapperException;

	/**
	 * 更新分析报告模板
	 * 
	 * @param template
	 * @return
	 * @throws MapperException
	 */
	Integer updateReportTemplate(ReportTemplate template) throws MapperException;

	/**
	 * 根据ID查询分析报告模板
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	ReportTemplate findReportTemplateById(Long id) throws MapperException;

	/**
	 * 查询分析报告模板列表
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<ReportTemplate> findReportTemplateForList(ReportTemplateQuery query) throws MapperException;

}
