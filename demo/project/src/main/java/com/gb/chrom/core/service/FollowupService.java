package com.gb.chrom.core.service;

import java.util.List;

import com.gb.chrom.model.Followup;
import com.gb.chrom.model.FollowupRecord;
import com.gb.chrom.model.query.FollowupQuery;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月24日
 * @since
 */
public interface FollowupService {

	/**
	 * add follow-up
	 * 
	 * @param record
	 * @return
	 */
	public boolean addFollowupRecord(FollowupRecord record);

	/**
	 * update follow-up
	 * 
	 * @param record
	 * @return
	 */
	public boolean updateFollowupRecord(FollowupRecord record);

	/**
	 * query follow-up
	 * 
	 * @param id
	 * @return
	 */
	public FollowupRecord queryFollowupRecord(long id);

	/**
	 * query follow-up record for list
	 * 
	 * @param specimenNo
	 * @return
	 */
	public List<FollowupRecord> queryFollowupRecordForList(String specimenNo);

	/**
	 * @param id
	 * @return
	 */
	public Followup queryFollowup(long id);
	
	/**
	 * @param specimenNo
	 * @return
	 */
	public Followup queryFollowup(String specimenNo);

	/**
	 * query follow-up for paging list
	 * 
	 * @param query
	 * @return
	 */
	public PageInfo<Followup> queryFollowupForPagingList(FollowupQuery query);

}
