package com.gb.chrom.model;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * 标本分析报告
 * 
 * @author Summer
 *
 *         2018年7月31日
 */
public class AnalysisReport implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 报告ID */
	private Long id;

	/** 模板ID */
	private Long templateId;

	/** 类型ID */
	private Integer typeId;

	/** 患者ID */
	private Long patientId;

	/** 报告名称 */
	private String name;

	/** HIS ID */
	private String hisId;

	/** 标本编号 */
	private String specimenNo;

	/** 送检样本 */
	private String inspectionType;

	/** 采样时间 */
	private String specimenDate;

	/** 病人姓名 */
	private String patientName;

	/** 病人性别 */
	private String patientSex;

	/** 病人年龄 */
	private String patientAge;

	/** 送检医生 */
	private String inspectionPhysician;

	/** 送检部门 */
	private String inspectionDept;

	/** 临床信息/送检指征 */
	private String clinicalInfo;

	/** 检测方法 */
	private String detectionMethod;

	/** 分析结果 */
	private String analysisResult;

	/** 分析结果图 */
	private String analysisMetImg;

	/** 分析结果排序图 */
	private String analysisKarImg;

	/** 结果解释 */
	private String resultInterpretation;

	/** 报告结论 */
	private String reportConclusion;

	/** 备注 */
	private String remarks;

	/** 报告提交人ID */
	private Long submitterId;

	/** 检验师 */
	private String laboratorian;

	/** 审核人 */
	@NotNull
	private Long masterAuditorId;

	/** 副审人 */
	private Long deputyAuditorId;

	/** 审核人 */
	private String masterAuditor;

	/** 副审人 */
	private String deputyAuditor;

	/** 报告日期 */
	private String reportDate;
	
//	/** 审核时间 */
//	private String auditTime;

	/** 审核状态 (Reject, Auditing, Passed) */
	private String auditStatus;

	/** 有效状态 */
	private Boolean status;

	/** 报告模板 */
	private ReportTemplate template;

	/** 审核记录 */
	private List<AnalysisReportAudit> auditList;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the templateId
	 */
	public Long getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId the templateId to set
	 */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	/**
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the patientId
	 */
	public Long getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the hisId
	 */
	public String getHisId() {
		return hisId;
	}

	/**
	 * @param hisId the hisId to set
	 */
	public void setHisId(String hisId) {
		this.hisId = hisId;
	}

	/**
	 * @return the specimenNo
	 */
	public String getSpecimenNo() {
		return specimenNo;
	}

	/**
	 * @param specimenNo the specimenNo to set
	 */
	public void setSpecimenNo(String specimenNo) {
		this.specimenNo = specimenNo;
	}

	/**
	 * @return the inspectionType
	 */
	public String getInspectionType() {
		return inspectionType;
	}

	/**
	 * @param inspectionType the inspectionType to set
	 */
	public void setInspectionType(String inspectionType) {
		this.inspectionType = inspectionType;
	}

	/**
	 * @return the specimenDate
	 */
	public String getSpecimenDate() {
		return specimenDate;
	}

	/**
	 * @param specimenDate the specimenDate to set
	 */
	public void setSpecimenDate(String specimenDate) {
		this.specimenDate = specimenDate;
	}

	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return the patientSex
	 */
	public String getPatientSex() {
		return patientSex;
	}

	/**
	 * @param patientSex the patientSex to set
	 */
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	/**
	 * @return the patientAge
	 */
	public String getPatientAge() {
		return patientAge;
	}

	/**
	 * @param patientAge the patientAge to set
	 */
	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}

	/**
	 * @return the inspectionPhysician
	 */
	public String getInspectionPhysician() {
		return inspectionPhysician;
	}

	/**
	 * @param inspectionPhysician the inspectionPhysician to set
	 */
	public void setInspectionPhysician(String inspectionPhysician) {
		this.inspectionPhysician = inspectionPhysician;
	}

	/**
	 * @return the inspectionDept
	 */
	public String getInspectionDept() {
		return inspectionDept;
	}

	/**
	 * @param inspectionDept the inspectionDept to set
	 */
	public void setInspectionDept(String inspectionDept) {
		this.inspectionDept = inspectionDept;
	}

	/**
	 * @return the clinicalInfo
	 */
	public String getClinicalInfo() {
		return clinicalInfo;
	}

	/**
	 * @param clinicalInfo the clinicalInfo to set
	 */
	public void setClinicalInfo(String clinicalInfo) {
		this.clinicalInfo = clinicalInfo;
	}

	/**
	 * @return the detectionMethod
	 */
	public String getDetectionMethod() {
		return detectionMethod;
	}

	/**
	 * @param detectionMethod the detectionMethod to set
	 */
	public void setDetectionMethod(String detectionMethod) {
		this.detectionMethod = detectionMethod;
	}

	/**
	 * @return the analysisResult
	 */
	public String getAnalysisResult() {
		return analysisResult;
	}

	/**
	 * @param analysisResult the analysisResult to set
	 */
	public void setAnalysisResult(String analysisResult) {
		this.analysisResult = analysisResult;
	}

	/**
	 * @return the analysisMetImg
	 */
	public String getAnalysisMetImg() {
		return analysisMetImg;
	}

	/**
	 * @param analysisMetImg the analysisMetImg to set
	 */
	public void setAnalysisMetImg(String analysisMetImg) {
		this.analysisMetImg = analysisMetImg;
	}

	/**
	 * @return the analysisKarImg
	 */
	public String getAnalysisKarImg() {
		return analysisKarImg;
	}

	/**
	 * @param analysisKarImg the analysisKarImg to set
	 */
	public void setAnalysisKarImg(String analysisKarImg) {
		this.analysisKarImg = analysisKarImg;
	}

	/**
	 * @return the resultInterpretation
	 */
	public String getResultInterpretation() {
		return resultInterpretation;
	}

	/**
	 * @param resultInterpretation the resultInterpretation to set
	 */
	public void setResultInterpretation(String resultInterpretation) {
		this.resultInterpretation = resultInterpretation;
	}

	/**
	 * @return the reportConclusion
	 */
	public String getReportConclusion() {
		return reportConclusion;
	}

	/**
	 * @param reportConclusion the reportConclusion to set
	 */
	public void setReportConclusion(String reportConclusion) {
		this.reportConclusion = reportConclusion;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	 * @return the laboratorian
	 */
	public String getLaboratorian() {
		return laboratorian;
	}

	/**
	 * @param laboratorian the laboratorian to set
	 */
	public void setLaboratorian(String laboratorian) {
		this.laboratorian = laboratorian;
	}

	/**
	 * @return the {@link #masterAuditorId}
	 */
	public Long getMasterAuditorId() {
		return masterAuditorId;
	}

	/**
	 * @param masterAuditorId the {@link #masterAuditorId} to set
	 */
	public void setMasterAuditorId(Long masterAuditorId) {
		this.masterAuditorId = masterAuditorId;
	}

	/**
	 * @return the masterAuditor
	 */
	public String getMasterAuditor() {
		return masterAuditor;
	}

	/**
	 * @param masterAuditor the masterAuditor to set
	 */
	public void setMasterAuditor(String masterAuditor) {
		this.masterAuditor = masterAuditor;
	}

	/**
	 * @return the {@link #deputyAuditorId}
	 */
	public Long getDeputyAuditorId() {
		return deputyAuditorId;
	}

	/**
	 * @param deputyAuditorId the {@link #deputyAuditorId} to set
	 */
	public void setDeputyAuditorId(Long deputyAuditorId) {
		this.deputyAuditorId = deputyAuditorId;
	}

	/**
	 * @return the deputyAuditor
	 */
	public String getDeputyAuditor() {
		return deputyAuditor;
	}

	/**
	 * @param deputyAuditor the deputyAuditor to set
	 */
	public void setDeputyAuditor(String deputyAuditor) {
		this.deputyAuditor = deputyAuditor;
	}

	/**
	 * @return the reportDate
	 */
	public String getReportDate() {
		return reportDate;
	}

	/**
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * @return the auditStatus
	 */
	public String getAuditStatus() {
		return auditStatus;
	}

	/**
	 * @param auditStatus the auditStatus to set
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @return the template
	 */
	public ReportTemplate getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(ReportTemplate template) {
		this.template = template;
	}

	/**
	 * @return the auditList
	 */
	public List<AnalysisReportAudit> getAuditList() {
		return auditList;
	}

	/**
	 * @param auditList the auditList to set
	 */
	public void setAuditList(List<AnalysisReportAudit> auditList) {
		this.auditList = auditList;
	}

}
