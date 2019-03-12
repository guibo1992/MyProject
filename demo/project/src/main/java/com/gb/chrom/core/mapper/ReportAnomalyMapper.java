package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.ReportAnomaly;
import com.gb.chrom.model.query.ReportAnomalyQuery;
import com.github.pagehelper.Page;

/**
 * 报告异常条目
 * 
 * @since 1.0
 * @author Summer
 */
@Mapper
public interface ReportAnomalyMapper {

	/**
	 * 保存报告异常条目
	 * 
	 * @param anomaly
	 * @return
	 * @throws MapperException
	 */
	Integer saveReportAnomaly(ReportAnomaly anomaly) throws MapperException;

	/**
	 * 删除报告异常条目
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	Integer deleteReportAnomalyById(Long id) throws MapperException;

	/**
	 * 更新报告异常条目
	 * 
	 * @param anomaly
	 * @return
	 * @throws MapperException
	 */
	Integer updateReportAnomaly(ReportAnomaly anomaly) throws MapperException;

	/**
	 * 根据ID查询报告异常条目
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	ReportAnomaly findReportAnomalyById(Long id) throws MapperException;

	/**
	 * 查询报告异常条目列表
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<ReportAnomaly> findReportAnomalyForList(ReportAnomalyQuery query) throws MapperException;

}
