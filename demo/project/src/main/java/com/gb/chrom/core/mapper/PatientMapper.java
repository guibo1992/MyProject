package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.Patient;

/**
 * @since 1.0
 * @author Summer
 */
@Mapper
public interface PatientMapper {

	/**
	 * 保存病患信息
	 * 
	 * @param patient
	 * @return
	 * @throws MapperException
	 */
	Integer savePatient(Patient patient) throws MapperException;

	/**
	 * 更新病患信息
	 * 
	 * @param patient
	 * @return
	 * @throws MapperException
	 */
	Integer updatePatient(Patient patient) throws MapperException;

	/**
	 * 查询患者信息
	 * 
	 * @param hisId
	 * @return
	 * @throws MapperException
	 */
	Patient findPatientByHisId(String hisId) throws MapperException;

}
