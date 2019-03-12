package com.gb.chrom.model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateSerializer;

/**
 * 病患信息
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月22日
 * @since
 */
public class Patient implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private Long id;

	/** HIS ID */
	private String hisId;

	/** 姓名 */
	private String name;

	/** 性别 */
	private String sex;

	/** 身份证 */
	private String idcard;

	/** 出生日期 */
	private String birthdate;

	/** 联系电话 */
	private String phone;

	/** 邮箱 */
	private String email;

	/** 医生 */
	private String doctor;

	/** 更新时间 */
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date gmtModified;

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
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the {@link #name} to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the {@link #sex}
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the {@link #sex} to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the {@link #idcard}
	 */
	public String getIdcard() {
		return idcard;
	}

	/**
	 * @param idcard
	 *            the {@link #idcard} to set
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	/**
	 * @return the {@link #birthdate}
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the {@link #birthdate} to set
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the {@link #phone}
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the {@link #phone} to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the {@link #email}
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the {@link #email} to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the {@link #doctor}
	 */
	public String getDoctor() {
		return doctor;
	}

	/**
	 * @param doctor
	 *            the {@link #doctor} to set
	 */
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	/**
	 * @return the {@link #gmtModified}
	 */
	public Date getGmtModified() {
		return gmtModified;
	}

	/**
	 * @param gmtModified
	 *            the {@link #gmtModified} to set
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	
	public Integer getAge() {
		if (StringUtils.isNotBlank(this.birthdate)) {
			try {
				
				Calendar calendar = Calendar.getInstance();
				int now = calendar.get(Calendar.YEAR) * 12 + calendar.get(Calendar.MONTH);
				
				Date date = DateUtils.parseDate(birthdate, "yyyy-MM-dd");
				calendar.setTime(date);
				int birthInt = calendar.get(Calendar.YEAR) * 12 + calendar.get(Calendar.MONTH);
				
				return (now - birthInt) / 12;
			} catch (ParseException e) {
				return null;
			}
		}
		
		return null;
	}

}
