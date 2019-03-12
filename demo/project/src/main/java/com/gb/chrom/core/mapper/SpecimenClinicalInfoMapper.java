package com.gb.chrom.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.SpecimenClinicalInfo;

/**
 * 
 * @author Summer
 */
@Mapper
public interface SpecimenClinicalInfoMapper {
	/**
	 * 保存临床信息
	 * @param ClinicalInfoList
	 * @return
	 * @throws MapperException
	 */
	Integer saveClinicalInfoList(List<SpecimenClinicalInfo> ClinicalInfoList) throws MapperException;
	/**
	 * id删除临床信息报告
	 * @param specimenId
	 * @return
	 * @throws MapperException
	 */
	Integer deleteClinicalInfoBySpecimenId(Long specimenId) throws MapperException;
	/**
	 * id查找临床信息报告
	 * @param specimenId
	 * @return
	 * @throws MapperException
	 */
	List<SpecimenClinicalInfo> findClinicalInfoForList(Long specimenId) throws MapperException;

}
