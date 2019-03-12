package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.SlidePrintRecords;
import com.gb.chrom.model.query.SlidePrintRecordsQuery;
import com.github.pagehelper.Page;

/**
 * 载玻片打印记录
 * 
 * @since 1.0
 * @author Summer
 */
@Mapper
public interface SlidePrintRecordsMapper {

	/**
	 * 保存载玻片打印记录
	 * 
	 * @param printRecords
	 * @return
	 * @throws MapperException
	 */
	Integer saveSlidePrintRecords(SlidePrintRecords printRecords) throws MapperException;

	/**
	 * 查询载玻片打印记录
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<SlidePrintRecords> findSlidePrintRecordsForList(SlidePrintRecordsQuery query) throws MapperException;

}
