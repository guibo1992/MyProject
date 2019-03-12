package com.gb.chrom.model.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.gb.chrom.domain.Paginator;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月23日
 * @since
 */
public class ReagentStockQuery extends Paginator {

	private static final long serialVersionUID = 1L;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

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

}
