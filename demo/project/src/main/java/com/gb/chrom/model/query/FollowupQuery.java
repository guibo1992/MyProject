package com.gb.chrom.model.query;

import java.util.Date;

import com.gb.chrom.domain.Paginator;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月21日
 * @since
 */
public class FollowupQuery extends Paginator {

	private static final long serialVersionUID = 1L;

	/** 查询开始日期 */
	private Date startDate;

	/** 查询结束日期 */
	private Date endDate;

	/** 随访状态 */
	private Integer followupStatus;

	/**
	 * @return the {@link #startDate}
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the {@link #startDate} to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the {@link #endDate}
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the {@link #endDate} to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the {@link #followupStatus}
	 */
	public Integer getFollowupStatus() {
		return followupStatus;
	}

	/**
	 * @param followupStatus
	 *            the {@link #followupStatus} to set
	 */
	public void setFollowupStatus(Integer followupStatus) {
		this.followupStatus = followupStatus;
	}

}
