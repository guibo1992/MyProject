package com.gb.chrom.model.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.gb.chrom.domain.Paginator;

/**
 * @author Summer
 *
 *         2018年8月1日
 */
public class AnalysisReportQuery extends Paginator {

	private static final long serialVersionUID = 1L;

	/** 类型ID */
	private Integer typeId;

	/** 报告提交人ID */
	private Long submitterId;

	/** 审核人 */
	private Long auditorId;

	/** 开始日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	/** 结束日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	/** 审核状态 (Reject, Wait audit, Wait reaudit, Passed) */
	private String auditStatus;

	/** 有效状态 */
	private Boolean status;

	/**
	 * @return the {@link #typeId}
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the {@link #typeId} to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

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
	 * @return the submitterId
	 */
	public Long getSubmitterId() {
		return submitterId;
	}

	/**
	 * @param submitterId the submitterId to set
	 */
	public void setSubmitterId(Long submitterId) {
		this.submitterId = submitterId;
	}

	/**
	 * @return the {@link #startDate}
	 */
	public Date getStartDate() {
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
		return endDate;
	}

	/**
	 * @param endDate the {@link #endDate} to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the {@link #auditStatus}
	 */
	public String getAuditStatus() {
		return auditStatus;
	}

	/**
	 * @param auditStatus the {@link #auditStatus} to set
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * @return the {@link #status}
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the {@link #status} to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

}
