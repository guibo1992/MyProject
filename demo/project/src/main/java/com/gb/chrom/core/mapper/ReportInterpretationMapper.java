package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.ReportInterpretation;
import com.gb.chrom.model.query.ReportInterpretationQuery;
import com.github.pagehelper.Page;

/**
 * 报告结果解释
 * 
 * @since 1.0
 * @author Summer
 */
@Mapper
public interface ReportInterpretationMapper {

	/**
	 * 保存报告结果解释
	 * 
	 * @param interpretation
	 * @return
	 * @throws MapperException
	 */
	Integer saveReportInterpretation(ReportInterpretation interpretation) throws MapperException;

	/**
	 * 删除报告结果解释
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	Integer deleteReportInterpretationById(Long id) throws MapperException;

	/**
	 * 更新报告结果解释
	 * 
	 * @param interpretation
	 * @return
	 * @throws MapperException
	 */
	Integer updateReportInterpretation(ReportInterpretation interpretation) throws MapperException;

	/**
	 * 根据ID查询报告结果解释
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	ReportInterpretation findReportInterpretationById(Long id) throws MapperException;

	/**
	 * 查询报告结果解释列表
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<ReportInterpretation> findReportInterpretationForList(ReportInterpretationQuery query) throws MapperException;

}
