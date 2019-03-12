/**
 * 
 */
package com.gb.chrom.model;

/**
 * 分析工作统计
 * 
 * @author Summer
 *
 */
public class AnalysisWorkStatistics implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 用户ID */
	private Long userId;

	/** 统计的年份 */
	private Integer statisYear;

	/** 统计的季度 */
	private Integer statisQuarter;

	/** 统计的月份 */
	private Integer statisMonth;

	/** 分析标本的次数 */
	private Integer analysisSpecimenCount = 0;

	/** 出报告的效率 */
	private String reportedEfficiency;

	/** 报告数量 */
	private Integer reportedCount = 0;

	/** 报告驳回数量 */
	private Integer reportRejectCount = 0;

	/** 分析人员 */
	private User laboratorian;

	/**
	 * @return the {@link #userId}
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the {@link #userId} to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the {@link #statisYear}
	 */
	public Integer getStatisYear() {
		return statisYear;
	}

	/**
	 * @param statisYear the {@link #statisYear} to set
	 */
	public void setStatisYear(Integer statisYear) {
		this.statisYear = statisYear;
	}

	/**
	 * @return the {@link #statisQuarter}
	 */
	public Integer getStatisQuarter() {
		return statisQuarter;
	}

	/**
	 * @param statisQuarter the {@link #statisQuarter} to set
	 */
	public void setStatisQuarter(Integer statisQuarter) {
		this.statisQuarter = statisQuarter;
	}

	/**
	 * @return the {@link #statisMonth}
	 */
	public Integer getStatisMonth() {
		return statisMonth;
	}

	/**
	 * @param statisMonth the {@link #statisMonth} to set
	 */
	public void setStatisMonth(Integer statisMonth) {
		this.statisMonth = statisMonth;
	}

	/**
	 * @return the {@link #analysisSpecimenCount}
	 */
	public Integer getAnalysisSpecimenCount() {
		return analysisSpecimenCount;
	}

	/**
	 * @param analysisSpecimenCount the {@link #analysisSpecimenCount} to set
	 */
	public void setAnalysisSpecimenCount(Integer analysisSpecimenCount) {
		this.analysisSpecimenCount = analysisSpecimenCount;
	}

	/**
	 * @return the {@link #reportedEfficiency}
	 */
	public String getReportedEfficiency() {
		return reportedEfficiency;
	}

	/**
	 * @param reportedEfficiency the {@link #reportedEfficiency} to set
	 */
	public void setReportedEfficiency(String reportedEfficiency) {
		this.reportedEfficiency = reportedEfficiency;
	}

	/**
	 * @return the {@link #reportedCount}
	 */
	public Integer getReportedCount() {
		return reportedCount;
	}

	/**
	 * @param reportedCount the {@link #reportedCount} to set
	 */
	public void setReportedCount(Integer reportedCount) {
		this.reportedCount = reportedCount;
	}

	/**
	 * @return the {@link #reportRejectCount}
	 */
	public Integer getReportRejectCount() {
		return reportRejectCount;
	}

	/**
	 * @param reportRejectCount the {@link #reportRejectCount} to set
	 */
	public void setReportRejectCount(Integer reportRejectCount) {
		this.reportRejectCount = reportRejectCount;
	}

	/**
	 * @return the {@link #laboratorian}
	 */
	public User getLaboratorian() {
		return laboratorian;
	}

	/**
	 * @param laboratorian the {@link #laboratorian} to set
	 */
	public void setLaboratorian(User laboratorian) {
		this.laboratorian = laboratorian;
	}

}
