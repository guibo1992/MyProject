package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.ReportConclusion;
import com.gb.chrom.model.query.ReportConclusionQuery;
import com.github.pagehelper.Page;

/**
 * 报告结论
 * 
 * @since 1.0
 * @author Summer
 */
@Mapper
public interface ReportConclusionMapper {

	/**
	 * 保存报告结论
	 * 
	 * @param conclusion
	 * @return
	 * @throws MapperException
	 */
	Integer saveReportConclusion(ReportConclusion conclusion) throws MapperException;

	/**
	 * 删除报告结论
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	Integer deleteReportConclusionById(Long id) throws MapperException;

	/**
	 * 更新报告结论
	 * 
	 * @param conclusion
	 * @return
	 * @throws MapperException
	 */
	Integer updateReportConclusion(ReportConclusion conclusion) throws MapperException;

	/**
	 * 根据ID查询报告结论
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	ReportConclusion findReportConclusionById(Long id) throws MapperException;

	/**
	 * 查询报告结论列表
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<ReportConclusion> findReportConclusionForList(ReportConclusionQuery query) throws MapperException;

}
