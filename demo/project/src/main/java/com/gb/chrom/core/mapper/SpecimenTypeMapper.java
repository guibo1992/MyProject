package com.gb.chrom.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.SpecimenType;
import com.gb.chrom.model.query.SpecimenTypeQuery;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月3日
 * @since
 */
@Mapper
public interface SpecimenTypeMapper {

	/**
	 * 保存标本类型
	 * 
	 * @param type
	 * @return
	 * @throws MapperException
	 */
	Integer saveSpecimenType(SpecimenType type) throws MapperException;

	/**
	 * 更新标本类型
	 * 
	 * @param type
	 * @return
	 * @throws MapperException
	 */
	Integer updateSpecimenType(SpecimenType type) throws MapperException;

	/**
	 * 查询标本类型
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	SpecimenType findSpecimenTypeById(Integer id) throws MapperException;

	/**
	 * 查询标本类型列表
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	List<SpecimenType> findSpecimenTypeForList(SpecimenTypeQuery query) throws MapperException;
	
}
