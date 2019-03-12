package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.ReagentStockOut;
import com.gb.chrom.model.query.ReagentStockQuery;
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
public interface ReagentStockOutMapper {

	/**
	 * 保存出库单
	 * 
	 * @param stockOut
	 * @return
	 * @throws MapperException
	 */
	Integer saveReagentStockOut(ReagentStockOut stockOut) throws MapperException;

	/**
	 * 查询出库单
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<ReagentStockOut> findReagentStockOutForList(ReagentStockQuery query) throws MapperException;

}
