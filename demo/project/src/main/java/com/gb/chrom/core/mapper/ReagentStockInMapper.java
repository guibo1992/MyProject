package com.gb.chrom.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.ReagentStockIn;
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
public interface ReagentStockInMapper {

	/**
	 * 保存入库单
	 * 
	 * @param stockIn
	 * @return
	 * @throws MapperException
	 */
	Integer saveReagentStockIn(ReagentStockIn stockIn) throws MapperException;

	/**
	 * 查询入库单
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<ReagentStockIn> findReagentStockInForList(ReagentStockQuery query) throws MapperException;

	/**
	 * 更新库存量
	 * 
	 * @param batchNo
	 * @param stocks
	 * @return
	 * @throws MapperException
	 */
	Integer updateReagentStocks(@Param("batchNo") String batchNo, @Param("stocks") Long stocks) throws MapperException;

	/**
	 * 查询可用库存量
	 * 
	 * @param reagentId
	 * @return
	 * @throws MapperException
	 */
	List<ReagentStockIn> findReagentAvailableStocksForList(Long reagentId) throws MapperException;

}
