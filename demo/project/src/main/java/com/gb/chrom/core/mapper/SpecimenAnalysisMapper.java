package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.SpecimenAnalysisResult;
import com.gb.chrom.model.query.SpecimenQuery;
import com.github.pagehelper.Page;

/**
 * @author Summer
 *
 * 		2018年7月31日
 */
@Mapper
public interface SpecimenAnalysisMapper {
	/**
	 * 添加标本分析模板
	 * @param result
	 * @return
	 * @throws MapperException
	 */
	Integer saveSpecimenAnalysisResult(SpecimenAnalysisResult result) throws MapperException;
	/**
	 * 显示分析结果
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<Specimen> findSpecimenAnalysisForList(SpecimenQuery query) throws MapperException;
	
}
