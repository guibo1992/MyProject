package com.gb.chrom.core.service;

import com.gb.chrom.model.ReagentStockIn;
import com.gb.chrom.model.ReagentStockOut;
import com.gb.chrom.model.query.ReagentStockQuery;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月23日
 * @since
 */
public interface ReagentStockService {

	/**
	 * 试剂入库
	 * 
	 * @param stockIn
	 * @return
	 */
	public boolean reagentPutInStorage(ReagentStockIn stockIn);

	/**
	 * 试剂出库
	 * 
	 * @param stockOut
	 * @return
	 */
	public boolean reagentPutOutStorage(ReagentStockOut stockOut);

	/**
	 * 查询入库单记录
	 * 
	 * @param query
	 * @return
	 */
	public PageInfo<ReagentStockIn> queryReagentStockInForPagingList(ReagentStockQuery query);

	/**
	 * 查询出库单记录
	 * 
	 * @param query
	 * @return
	 */
	public PageInfo<ReagentStockOut> queryReagentStockOutForPagingList(ReagentStockQuery query);

}
