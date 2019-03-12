package com.gb.chrom.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.model.Followup;
import com.gb.chrom.model.query.FollowupQuery;
import com.github.pagehelper.Page;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月24日
 * @since
 */
@Mapper
public interface FollowupMapper {

	/**
	 * save follow-up
	 * 
	 * @param followup
	 * @return
	 * @throws MapperException
	 */
	Integer saveFollowup(Followup followup) throws MapperException;

	/**
	 * update follow-up
	 * 
	 * @param followup
	 * @return
	 * @throws MapperException
	 */
	Integer updateFollowup(Followup followup) throws MapperException;

	/**
	 * find follow-up by id
	 * 
	 * @param id
	 * @return
	 * @throws MapperException
	 */
	Followup findFollowupById(Long id) throws MapperException;

	/**
	 * find follow-up by speciment No.
	 * 
	 * @param specimenNo
	 * @return
	 * @throws MapperException
	 */
	Followup findFollowupBySpecimenNo(String specimenNo) throws MapperException;

	/**
	 * find follow-up for list
	 * 
	 * @param query
	 * @return
	 * @throws MapperException
	 */
	Page<Followup> findFollowupForList(FollowupQuery query) throws MapperException;

}
