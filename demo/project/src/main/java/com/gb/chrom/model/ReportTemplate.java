package com.gb.chrom.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateTimeSerializer;

/**
 * 分析报告模板
 * 
 * @author Summer
 *
 *         2018年8月1日
 */
public class ReportTemplate implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 模板ID */
	private Long id;

	/** 类型ID */
	@NotNull
	private String typeId;

	/** 名称 */
	@NotBlank
	private String name;

	/** 显示检测方法 */
	private Boolean hideDetectionMethod = Boolean.FALSE;

	/** 默认检测方法 */
	private String defaultDetectionMethod;
	
	/**显示logo*/
	private Boolean logo = Boolean.FALSE;

	/**显示姓名*/
	private Boolean patientName=Boolean.FALSE;
	
	/**显示门诊号*/
	private Boolean hisId=Boolean.FALSE;
	
	/**显示样本*/
	private Boolean inspectionType=Boolean.FALSE;
	
	/**显示性别*/
	private Boolean patientSex=Boolean.FALSE;
	
	/**显示送检科室*/
	private Boolean inspectionDept=Boolean.FALSE;
	
	/**显示送检日期*/
	private Boolean specimenDate=Boolean.FALSE;
	
	/**显示年龄*/
	private Boolean patientAge=Boolean.FALSE;
	
	/**显示送检医生*/
	private Boolean inspectionPhysician=Boolean.FALSE;
	
	/**显示样本号*/
	private Boolean specimenNo=Boolean.FALSE;
	
	/**显示指征*/
	private Boolean clinicalInfo=Boolean.FALSE;
	
	/**显示结果解释*/
	private Boolean resultInterpretation=Boolean.FALSE;
	
	/**显示结论*/
	private Boolean reportConclusion=Boolean.FALSE;

	/** 隐藏结果原图 */
	private Boolean hideResultMetImg = Boolean.FALSE;

	/** 隐藏结果中的性别信息 */
	private Boolean hideResultSex = Boolean.FALSE;

	/** 显示结果解释 */
	private Boolean hideResultInterpert = Boolean.FALSE;

	/** 显示备注 */
	private Boolean hideRemarks = Boolean.FALSE;

	/** 默认备注 */
	private String defaultRemarks;

	/** 审核人 */
	@NotNull
	private Long masterAuditorId;

	/** 副审人 */
	private Long deputyAuditorId;

	/** 有效状态 */
	private Boolean status;

	/** 更新时间 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private Date gmtModified;

	private SpecimenType type;
	
	/**
	 * 
	 * @return
	 */
	public Boolean getInspectionDept() {
		return inspectionDept;
	}
	
	public void setInspectionDept(Boolean inspectionDept) {
		this.inspectionDept = inspectionDept;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getSpecimenDate() {
		return specimenDate;
	}

	public void setSpecimenDate(Boolean specimenDate) {
		this.specimenDate = specimenDate;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(Boolean patientAge) {
		this.patientAge = patientAge;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getInspectionPhysician() {
		return inspectionPhysician;
	}

	public void setInspectionPhysician(Boolean inspectionPhysician) {
		this.inspectionPhysician = inspectionPhysician;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getSpecimenNo() {
		return specimenNo;
	}

	public void setSpecimenNo(Boolean specimenNo) {
		this.specimenNo = specimenNo;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getClinicalInfo() {
		return clinicalInfo;
	}

	public void setClinicalInfo(Boolean clinicalInfo) {
		this.clinicalInfo = clinicalInfo;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getResultInterpretation() {
		return resultInterpretation;
	}

	public void setResultInterpretation(Boolean resultInterpretation) {
		this.resultInterpretation = resultInterpretation;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getReportConclusion() {
		return reportConclusion;
	}

	public void setReportConclusion(Boolean reportConclusion) {
		this.reportConclusion = reportConclusion;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(Boolean patientSex) {
		this.patientSex = patientSex;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(Boolean inspectionType) {
		this.inspectionType = inspectionType;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean getHisId() {
		return hisId;
	}

	public void setHisId(Boolean hisId) {
		this.hisId = hisId;
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getPatientName() {
		return patientName;
	}

	public void setPatientName(Boolean patientName) {
		this.patientName = patientName;
	}
	
	/**
	 * @return
	 */
	public Boolean getLogo() {
		return logo;
	}

	public void setLogo(Boolean logo) {
		this.logo = logo;
	}	

	/**
	 * @return the {@link #id}
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the {@link #id} to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the {@link #typeId}
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the {@link #typeId} to set
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the {@link #name} to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the {@link #hideDetectionMethod}
	 */
	public Boolean getHideDetectionMethod() {
		return hideDetectionMethod;
	}

	/**
	 * @param hideDetectionMethod the {@link #hideDetectionMethod} to set
	 */
	public void setHideDetectionMethod(Boolean hideDetectionMethod) {
		this.hideDetectionMethod = hideDetectionMethod;
	}

	/**
	 * @return the {@link #defaultDetectionMethod}
	 */
	public String getDefaultDetectionMethod() {
		return defaultDetectionMethod;
	}

	/**
	 * @param defaultDetectionMethod the {@link #defaultDetectionMethod} to set
	 */
	public void setDefaultDetectionMethod(String defaultDetectionMethod) {
		this.defaultDetectionMethod = defaultDetectionMethod;
	}

	/**
	 * @return the {@link #hideResultMetImg}
	 */
	public Boolean getHideResultMetImg() {
		return hideResultMetImg;
	}

	/**
	 * @param hideResultMetImg the {@link #hideResultMetImg} to set
	 */
	public void setHideResultMetImg(Boolean hideResultMetImg) {
		this.hideResultMetImg = hideResultMetImg;
	}

	/**
	 * @return the {@link #hideResultSex}
	 */
	public Boolean getHideResultSex() {
		return hideResultSex;
	}

	/**
	 * @param hideResultSex the {@link #hideResultSex} to set
	 */
	public void setHideResultSex(Boolean hideResultSex) {
		this.hideResultSex = hideResultSex;
	}

	/**
	 * @return the {@link #hideResultInterpert}
	 */
	public Boolean getHideResultInterpert() {
		return hideResultInterpert;
	}

	/**
	 * @param hideResultInterpert the {@link #hideResultInterpert} to set
	 */
	public void setHideResultInterpert(Boolean hideResultInterpert) {
		this.hideResultInterpert = hideResultInterpert;
	}

	/**
	 * @return the {@link #hideRemarks}
	 */
	public Boolean getHideRemarks() {
		return hideRemarks;
	}

	/**
	 * @param hideRemarks the {@link #hideRemarks} to set
	 */
	public void setHideRemarks(Boolean hideRemarks) {
		this.hideRemarks = hideRemarks;
	}

	/**
	 * @return the {@link #defaultRemarks}
	 */
	public String getDefaultRemarks() {
		return defaultRemarks;
	}

	/**
	 * @param defaultRemarks the {@link #defaultRemarks} to set
	 */
	public void setDefaultRemarks(String defaultRemarks) {
		this.defaultRemarks = defaultRemarks;
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

	/**
	 * @return the {@link #gmtModified}
	 */
	public Date getGmtModified() {
		return gmtModified;
	}

	/**
	 * @param gmtModified the {@link #gmtModified} to set
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	/**
	 * @return the type
	 */
	public SpecimenType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(SpecimenType type) {
		this.type = type;
	}

}
