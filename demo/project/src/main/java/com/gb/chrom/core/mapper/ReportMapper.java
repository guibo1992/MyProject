package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.AnalysisReport;
import com.gb.chrom.model.query.AnalysisReportQuery;
import com.github.pagehelper.Page;

/**
 * @author Summer
 *
 *         2018年8月1日
 */
@Mapper
public interface ReportMapper {
	/**
	 * 保存分析报告
	 * @param report
	 * @return
	 * @throws MapperException
	 */
	Integer saveAnalysisReport(AnalysisReport report) throws MapperException;
	/**
	 * 添加分析报告
	 * @param report
	 * @return
	 * @throws MapperException
	 */
	Integer updateAnalysisReport(AnalysisReport report) throws MapperException;
	/**
	 * id查询分析报告
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	AnalysisReport findAnalysisReportById(Long id) throws MapperException;
	/**
	 * 显示所有分析报告
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<AnalysisReport> findAnalysisReportForList(AnalysisReportQuery query) throws MapperException;

}
