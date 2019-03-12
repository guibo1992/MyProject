package com.gb.chrom.core.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.AnalysisWorkStatistics;
import com.gb.chrom.model.query.StatisticsQuery;

/**
 * @author Summer
 *
 *         2018年8月12日
 */
@Mapper
public interface AnalysisWorkStatisticsMapper {
	/**
	 * 查询某id标本分析次数
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	@MapKey("userId")
	Map<Long, AnalysisWorkStatistics> findSpecimenAnalysisCountForMap(StatisticsQuery query) throws MapperException;
	/**
	 * 查询某id审核次数
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	@MapKey("userId")
	Map<Long, AnalysisWorkStatistics> findReportCountForMap(StatisticsQuery query) throws MapperException;

}
