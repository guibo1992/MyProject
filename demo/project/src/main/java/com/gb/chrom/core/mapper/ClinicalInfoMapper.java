package com.gb.chrom.core.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.ClinicalInfo;




@Mapper
public interface ClinicalInfoMapper {
	
	/**
	 * 查询临床信息
	 * 
	 */
List<ClinicalInfo> findClinicalInfo(char typehead) throws MapperException;
}
