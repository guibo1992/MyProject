package com.gb.chrom.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.AnalysisReportAudit;
import com.gb.chrom.model.query.AnalysisReportAuditQuery;
import com.github.pagehelper.Page;

/**
 * @author Summer
 *
 *         2018年8月1日
 */
@Mapper
public interface ReportAuditMapper {
	/**
	 * 保存报告审核
	 * @param reportAudit
	 * @return
	 * @throws MapperException
	 */
	Integer saveReportAudit(AnalysisReportAudit reportAudit) throws MapperException;
	/**
	 * id查找报告审核
	 * @param reportId
	 * @return
	 * @throws MapperException
	 */
	List<AnalysisReportAudit> findReportAuditListByReportId(Long reportId)  throws MapperException;
	/**
	 * 显示报告审核
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<AnalysisReportAudit> findReportAuditForList(AnalysisReportAuditQuery query) throws MapperException;

}
