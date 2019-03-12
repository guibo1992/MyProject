package com.gb.chrom.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.FollowupRecord;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月24日
 * @since
 */
@Mapper
public interface FollowupRecordMapper {

	/**
	 * save follow-up record info
	 * 
	 * @param record
	 * @return
	 * @throws MapperException
	 */
	Integer saveFollowupRecord(FollowupRecord record) throws MapperException;

	/**
	 * update follow-up record info
	 * 
	 * @param record
	 * @return
	 * @throws MapperException
	 */
	Integer updateFollowupRecord(FollowupRecord record) throws MapperException;

	/**
	 * find follow-up record
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	FollowupRecord findFollowupRecordById(Long id) throws MapperException;

	/**
	 * find follow-up record for list
	 * 
	 * @param specimenNo
	 * @return
	 * @throws MapperException
	 */
	List<FollowupRecord> findFollowupRecordForList(String specimenNo) throws MapperException;

}
