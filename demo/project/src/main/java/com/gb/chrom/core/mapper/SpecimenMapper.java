package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.query.SpecimenQuery;
import com.github.pagehelper.Page;

/**
 * @since 1.0
 * @author Summer
 */
@Mapper
public interface SpecimenMapper {

	/**
	 * 保存标本
	 * 
	 * @param specimen
	 * @return
	 * @throws MapperException
	 */
	Integer saveSpecimen(Specimen specimen) throws MapperException;

	/**
	 * 更新标本
	 * 
	 * @param specimen
	 * @return
	 * @throws MapperException
	 */
	Integer updateSpecimen(Specimen specimen) throws MapperException;

	/**
	 * 删除标本
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	Integer deleteSpecimenById(Long id) throws MapperException;

	/**
	 * 查询标本
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	Specimen findSpecimenById(Long id) throws MapperException;

	/**
	 * 查询标本
	 * 
	 * @param specimenNo
	 * @return
	 * @throws MapperException
	 */
	Specimen findSpecimenBySpecimenNo(String specimenNo) throws MapperException;
	
	/**
	 * 查询标本
	 * 
	 * @param hisId
	 * @return
	 * @throws MapperException
	 */
	Specimen findSpecimenByHisId(String hisId) throws MapperException;


	/**
	 * 查询标本列表
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<Specimen> findSpecimenForList(SpecimenQuery query) throws MapperException;

	/**
	 * 查询标本一年中最大序号
	 * 
	 * @param type
	 * @return
	 * @throws MapperException
	 */
	Long findSpecimenYearMaxSerialNo(@Param("typeId") Integer typeId, @Param("year") Integer year) throws MapperException;

	/**
	 * 查询同类型下最一个标本的分析师id
	 * 
	 * @return
	 * @throws MapperException
	 */
	Long findLastSpecimenAnalystId(@Param("specimenTypeId") Integer typeId, @Param("userType") Integer userType) throws MapperException;

	/**
	 * 查询分析员分析标本数量
	 * 
	 * @param id
	 */
	Integer findCountForLaboratorian(Long id) throws MapperException;

}
