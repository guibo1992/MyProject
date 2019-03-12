package com.gb.chrom.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.SpecimenCulture;
import com.gb.chrom.model.query.SpecimenQuery;
import com.github.pagehelper.Page;

/**
 * @since 1.0
 * @author Summer
 */
@Mapper
public interface SpecimenCultureMapper {

	/**
	 * 标本培养
	 * 
	 * @param culture
	 * @return
	 * @throws MapperException
	 */
	Integer saveSpecimenCulture(SpecimenCulture culture) throws MapperException;

	/**
	 * 批量标本培养
	 * 
	 * @param cultureList
	 * @return
	 * @throws MapperException
	 */
	Integer batchSaveSpecimensCultureList(List<SpecimenCulture> cultureList) throws MapperException;

	/**
	 * 查询培养标本的信息
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	Specimen findCultureSpecimenById(Long id) throws MapperException;

	/**
	 * 查询培养标本列表
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<Specimen> findCultureSpecimenForList(SpecimenQuery query) throws MapperException;

	/**
	 * 查询待下一步处理的标本SN
	 * 
	 * @param typeId
	 * @param index
	 * @return
	 * @throws MapperException
	 */
	List<String> findCultureSpecimenNoForList(@Param("typeId") Integer typeId, @Param("processIndex") Integer index) throws MapperException;

}
