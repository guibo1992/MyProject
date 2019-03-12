package com.gb.chrom.core.service;

import com.gb.chrom.model.SlidePrintRecords;
import com.gb.chrom.model.query.SlidePrintRecordsQuery;
import com.github.pagehelper.PageInfo;

/**
 * 标本打印记录
 * 
 * @since 1.0
 * @author Summer
 */
public interface SlidePrintRecordsService {

	/**
	 * 保存标本打印记录
	 * 
	 * @param print
	 * @return
	 */
	public boolean addSlidePrintRecords(SlidePrintRecords printLog);

	/**
	 * 查询标本打印记录
	 * 
	 * @param query
	 * @return
	 */
	public PageInfo<SlidePrintRecords> querySlidePrintRecordsForPagingList(SlidePrintRecordsQuery query);

}
