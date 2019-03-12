package com.gb.chrom.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateSerializer;

/**
 * 标本分析报告
 * 
 * @author Summer
 *
 *         2018年7月31日
 */
public class AnalysisReportAudit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 报告ID */
	private Long reportId;

	/** 门诊号 */
	private String hisId;

	/** 报告名称 */
	private String reportName;

	/** 标本编号 */
	private String specimenNo;

	/** 样本类型 */
	private String inspectionType;

	/** 审核时间 */
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date auditTime;

	/** 审核状态 */
	private Boolean auditStatus;

	/** 驳回理由 */
	private String rejectReason;

	/** 审核人ID */
	private Long auditorId;

	/** 审核人 */
	private String auditorName;

	/** 分析报告 */
	private AnalysisReport report;

	/**
	 * @return the {@link #reportId}
	 */
	public Long getReportId() {
		return reportId;
	}

	/**
	 * @param reportId the {@link #reportId} to set
	 */
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	/**
	 * @return the {@link #reportName}
	 */
	public String getReportName() {
		return reportName;
	}

	/**
	 * @param reportName the {@link #reportName} to set
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	/**
	 * @return the {@link #hisId}
	 */
	public String getHisId() {
		return hisId;
	}

	/**
	 * @param hisId the {@link #hisId} to set
	 */
	public void setHisId(String hisId) {
		this.hisId = hisId;
	}

	/**
	 * @return the {@link #specimenNo}
	 */
	public String getSpecimenNo() {
		return specimenNo;
	}

	/**
	 * @param specimenNo the {@link #specimenNo} to set
	 */
	public void setSpecimenNo(String specimenNo) {
		this.specimenNo = specimenNo;
	}

	/**
	 * @return the {@link #inspectionType}
	 */
	public String getInspectionType() {
		return inspectionType;
	}

	/**
	 * @param inspectionType the {@link #inspectionType} to set
	 */
	public void setInspectionType(String inspectionType) {
		this.inspectionType = inspectionType;
	}

	/**
	 * @return the {@link #auditTime}
	 */
	public Date getAuditTime() {
		return auditTime;
	}

	/**
	 * @param auditTime the {@link #auditTime} to set
	 */
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	/**
	 * @return the {@link #auditStatus}
	 */
	public Boolean getAuditStatus() {
		return auditStatus;
	}

	/**
	 * @param auditStatus the {@link #auditStatus} to set
	 */
	public void setAuditStatus(Boolean auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * @return the {@link #rejectReason}
	 */
	public String getRejectReason() {
		return rejectReason;
	}

	/**
	 * @param rejectReason the {@link #rejectReason} to set
	 */
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
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
	 * @return the {@link #auditorName}
	 */
	public String getAuditorName() {
		return auditorName;
	}

	/**
	 * @param auditorName the {@link #auditorName} to set
	 */
	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	/**
	 * @return the {@link #report}
	 */
	public AnalysisReport getReport() {
		return report;
	}

	/**
	 * @param report the {@link #report} to set
	 */
	public void setReport(AnalysisReport report) {
		this.report = report;
	}

}
