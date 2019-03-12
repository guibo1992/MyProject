package com.gb.chrom.core.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.Reagent;
import com.gb.chrom.model.query.ReagentQuery;
import com.github.pagehelper.Page;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月23日
 * @since
 */
@Mapper
public interface ReagentMapper {

	/**
	 * 保存试剂
	 * 
	 * @param reagent
	 * @return
	 * @throws MapperException
	 */
	Integer saveReagent(Reagent reagent) throws MapperException;

	/**
	 * 更新试剂
	 * 
	 * @param reagent
	 * @return
	 * @throws MapperException
	 */
	Integer updateReagent(Reagent reagent) throws MapperException;

	/**
	 * 查询试剂
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	Reagent findReagentById(Long id) throws MapperException;

	/**
	 * 查询试剂列表
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<Reagent> findReagentForList(ReagentQuery query) throws MapperException;

	/**
	 * 入库
	 * 
	 * @param id
	 * @param quantity
	 * @param inTime
	 * @param stocks
	 * @return
	 * @throws MapperException
	 */
	Integer reagentPutInStorage(@Param("id") Long id, @Param("quantity") Long quantity, @Param("inTime") Date inTime, @Param("stocks") Long stocks) throws MapperException;

	/**
	 * 出库
	 * 
	 * @param id
	 * @param quantity
	 * @param outTime
	 * @param stocks
	 * @return
	 * @throws MapperException
	 */
	Integer reagentPutOutStorage(@Param("id") Long id, @Param("quantity") Long quantity, @Param("outTime") Date outTime, @Param("stocks") Long stocks) throws MapperException;

}
