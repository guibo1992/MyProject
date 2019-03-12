package com.gb.chrom.model;

/**
 * 病患临床信息
 * 
 * @author Summer
 *
 */
public class SpecimenClinicalInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 标本ID */
	private Long specimenId;

	/** Patient ID */
	private Long patientId;

	/** 临床信息 */
	private String clinicalInfo;

	/** 临床信息备注 */
	private String clinicalRemarks;

	/**
	 * @return the specimenId
	 */
	public Long getSpecimenId() {
		return specimenId;
	}

	/**
	 * @param specimenId the specimenId to set
	 */
	public void setSpecimenId(Long specimenId) {
		this.specimenId = specimenId;
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
	 * @return the clinicalRemarks
	 */
	public String getClinicalRemarks() {
		return clinicalRemarks;
	}

	/**
	 * @param clinicalRemarks the clinicalRemarks to set
	 */
	public void setClinicalRemarks(String clinicalRemarks) {
		this.clinicalRemarks = clinicalRemarks;
	}

}
