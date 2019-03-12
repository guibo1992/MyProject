package com.gb.chrom.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateTimeSerializer;

/**
 * 标本打印记录
 * 
 * @since 1.0
 * @author Summer
 */
public class SlidePrintRecords implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 门诊号 */
	private String hisId;

	/** 标本ID */
	private Long specimenId;

	/** 类型ID */
	private Integer typeId;

	/** 标本号 */
	private String specimenNo;

	/** 打印日期 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private Date printTime;

	/** 打印数量 */
	private Integer printCount;

	/** 类型 */
	private SpecimenType type;

	/** 病人信息 */
	private Patient patient;

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
	 * @return the {@link #specimenId}
	 */
	public Long getSpecimenId() {
		return specimenId;
	}

	/**
	 * @param specimenId the {@link #specimenId} to set
	 */
	public void setSpecimenId(Long specimenId) {
		this.specimenId = specimenId;
	}

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
	 * @return the {@link #printTime}
	 */
	public Date getPrintTime() {
		return printTime;
	}

	/**
	 * @param printTime the {@link #printTime} to set
	 */
	public void setPrintTime(Date printTime) {
		this.printTime = printTime;
	}

	/**
	 * @return the {@link #printCount}
	 */
	public Integer getPrintCount() {
		return printCount;
	}

	/**
	 * @param printCount the {@link #printCount} to set
	 */
	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}

	/**
	 * @return the {@link #type}
	 */
	public SpecimenType getType() {
		return type;
	}

	/**
	 * @param type the {@link #type} to set
	 */
	public void setType(SpecimenType type) {
		this.type = type;
	}

	/**
	 * @return the {@link #patient}
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient the {@link #patient} to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
