package com.gb.chrom.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateSerializer;

/**
 * 随访
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月24日
 * @since
 */
public class Followup implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private Long id;

	/** HIS ID */
	private String hisId;

	/** 标本号 */
	private String specimenNo;

	/** 病患姓名 */
	private String patientName;

	/** 病患性别 */
	private String patientSex;

	/** 病患出生日期 */
	private String patientBirthdate;

	/** 第一次随访日期 */
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date firstFollowupDate;

	/** 最后一次随访日期 */
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date lastFollowupDate;

	/** 随访总次数 */
	private Integer followupTotalCount;

	/** 成功访次数 */
	private Integer successFollowupCount;

	/** 失访次数 */
	private Integer lostFollowupCount;

	/** 拒访次数 */
	private Integer rejectFollowupCount;

	/**
	 * @return the {@link #id}
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the {@link #id} to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the {@link #hisId}
	 */
	public String getHisId() {
		return hisId;
	}

	/**
	 * @param hisId
	 *            the {@link #hisId} to set
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
	 * @param specimenNo
	 *            the {@link #specimenNo} to set
	 */
	public void setSpecimenNo(String specimenNo) {
		this.specimenNo = specimenNo;
	}

	/**
	 * @return the {@link #patientName}
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName
	 *            the {@link #patientName} to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return the {@link #patientSex}
	 */
	public String getPatientSex() {
		return patientSex;
	}

	/**
	 * @param patientSex
	 *            the {@link #patientSex} to set
	 */
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	/**
	 * @return the {@link #patientBirthdate}
	 */
	public String getPatientBirthdate() {
		return patientBirthdate;
	}

	/**
	 * @param patientBirthdate
	 *            the {@link #patientBirthdate} to set
	 */
	public void setPatientBirthdate(String patientBirthdate) {
		this.patientBirthdate = patientBirthdate;
	}

	/**
	 * @return the {@link #firstFollowupDate}
	 */
	public Date getFirstFollowupDate() {
		return firstFollowupDate;
	}

	/**
	 * @param firstFollowupDate
	 *            the {@link #firstFollowupDate} to set
	 */
	public void setFirstFollowupDate(Date firstFollowupDate) {
		this.firstFollowupDate = firstFollowupDate;
	}

	/**
	 * @return the {@link #lastFollowupDate}
	 */
	public Date getLastFollowupDate() {
		return lastFollowupDate;
	}

	/**
	 * @param lastFollowupDate
	 *            the {@link #lastFollowupDate} to set
	 */
	public void setLastFollowupDate(Date lastFollowupDate) {
		this.lastFollowupDate = lastFollowupDate;
	}

	/**
	 * @return the {@link #followupTotalCount}
	 */
	public Integer getFollowupTotalCount() {
		return followupTotalCount;
	}

	/**
	 * @param followupTotalCount
	 *            the {@link #followupTotalCount} to set
	 */
	public void setFollowupTotalCount(Integer followupTotalCount) {
		this.followupTotalCount = followupTotalCount;
	}

	/**
	 * @return the {@link #successFollowupCount}
	 */
	public Integer getSuccessFollowupCount() {
		return successFollowupCount;
	}

	/**
	 * @param successFollowupCount
	 *            the {@link #successFollowupCount} to set
	 */
	public void setSuccessFollowupCount(Integer successFollowupCount) {
		this.successFollowupCount = successFollowupCount;
	}

	/**
	 * @return the {@link #lostFollowupCount}
	 */
	public Integer getLostFollowupCount() {
		return lostFollowupCount;
	}

	/**
	 * @param lostFollowupCount
	 *            the {@link #lostFollowupCount} to set
	 */
	public void setLostFollowupCount(Integer lostFollowupCount) {
		this.lostFollowupCount = lostFollowupCount;
	}

	/**
	 * @return the {@link #rejectFollowupCount}
	 */
	public Integer getRejectFollowupCount() {
		return rejectFollowupCount;
	}

	/**
	 * @param rejectFollowupCount
	 *            the {@link #rejectFollowupCount} to set
	 */
	public void setRejectFollowupCount(Integer rejectFollowupCount) {
		this.rejectFollowupCount = rejectFollowupCount;
	}

}
