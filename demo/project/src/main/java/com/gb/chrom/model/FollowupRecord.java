package com.gb.chrom.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateSerializer;

/**
 * 随访记录
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月21日
 * @since
 */
public class FollowupRecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private Long id;

	/** HIS ID */
	private String hisId;

	/** 标本编号 */
	@NotBlank
	private String specimenNo;

	/** follow-up记录主题 */
	private String subject;

	/** 随访日期 */
	@NotNull
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date followupDate;

	/** 随访内容 */
	private String followupContent;

	/** 随访人 */
	private String followuper;

	/** 随访状态 (1:正常, 0:失访, -1: 拒访) */
	@NotNull
	private Integer followupStatus;

	/** 病患信息 */
	private Patient patient;

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
	 * @return the {@link #subject}
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the {@link #subject} to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the {@link #followupDate}
	 */
	public Date getFollowupDate() {
		return followupDate;
	}

	/**
	 * @param followupDate
	 *            the {@link #followupDate} to set
	 */
	public void setFollowupDate(Date followupDate) {
		this.followupDate = followupDate;
	}

	/**
	 * @return the {@link #followupContent}
	 */
	public String getFollowupContent() {
		return followupContent;
	}

	/**
	 * @param followupContent
	 *            the {@link #followupContent} to set
	 */
	public void setFollowupContent(String followupContent) {
		this.followupContent = followupContent;
	}

	/**
	 * @return the {@link #followuper}
	 */
	public String getFollowuper() {
		return followuper;
	}

	/**
	 * @param followuper
	 *            the {@link #followuper} to set
	 */
	public void setFollowuper(String followuper) {
		this.followuper = followuper;
	}

	/**
	 * @return the {@link #followupStatus}
	 */
	public Integer getFollowupStatus() {
		return followupStatus;
	}

	/**
	 * @param followupStatus
	 *            the {@link #followupStatus} to set
	 */
	public void setFollowupStatus(Integer followupStatus) {
		this.followupStatus = followupStatus;
	}

	/**
	 * @return the {@link #patient}
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient
	 *            the {@link #patient} to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
