package com.gb.chrom.model.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.gb.chrom.domain.Paginator;

/**
 * @author Summer
 *
 *         2018年8月1日
 */
public class AnalysisReportAuditQuery extends Paginator {

	private static final long serialVersionUID = 1L;

	/** 审核人 ID */
	private Long auditorId;

	/** 开始日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	/** 结束日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	/** 审核状态 */
	private Boolean auditStatus;

	/**
	 * @return the auditorId
	 */
	public Long getAuditorId() {
		return auditorId;
	}

	/**
	 * @param auditorId the auditorId to set
	 */
	public void setAuditorId(Long auditorId) {
		this.auditorId = auditorId;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the auditStatus
	 */
	public Boolean getAuditStatus() {
		return auditStatus;
	}

	/**
	 * @param auditStatus the auditStatus to set
	 */
	public void setAuditStatus(Boolean auditStatus) {
		this.auditStatus = auditStatus;
	}

}
