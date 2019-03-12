package com.gb.chrom.model.query;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * @author Summer
 *
 *         2018年8月14日
 */
public class StatisticsQuery {

	private Date startDate;

	private Date endDate;

	/** 统计的年份 */
	private Integer year;

	/** 统计的季度 */
	private Integer quarter;

	/** 统计的月份 */
	private Integer month;

	/**
	 * @return the {@link #startDate}
	 */
	public Date getStartDate() {
		if (null == this.startDate) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, year);
			int field = Calendar.YEAR;

			if (null != this.quarter) {
				field = Calendar.MONTH;
				calendar.set(Calendar.MONTH, this.quarter * 3 - 3);
			}
			if (null != this.month) {
				field = Calendar.MONTH;
				calendar.set(Calendar.MONTH, month);
			}
			startDate = DateUtils.truncate(calendar, field).getTime();
		}
		return startDate;
	}

	/**
	 * @param startDate the {@link #startDate} to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the {@link #endDate}
	 */
	public Date getEndDate() {
		if (null == this.endDate) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, year);
			int field = Calendar.YEAR;

			if (null != this.quarter) {
				field = Calendar.MONTH;
				calendar.set(Calendar.MONTH, this.quarter * 3 - 1);
			}
			if (null != this.month) {
				field = Calendar.MONTH;
				calendar.set(Calendar.MONTH, month);
			}
			endDate = DateUtils.ceiling(calendar, field).getTime();
		}

		return endDate;
	}

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2018);

		calendar.set(Calendar.MONTH, 2 * 3 - 1);
		System.out.println(DateUtils.ceiling(calendar, Calendar.MONTH).getTime());
	}

	/**
	 * @param endDate the {@link #endDate} to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the {@link #year}
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the {@link #year} to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the {@link #quarter}
	 */
	public Integer getQuarter() {
		return quarter;
	}

	/**
	 * @param quarter the {@link #quarter} to set
	 */
	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	/**
	 * @return the {@link #month}
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * @param month the {@link #month} to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

}
