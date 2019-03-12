package com.gb.chrom.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateTimeSerializer;

/**
 * @author Summer
 *
 *         2017年12月19日
 */
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public static final String _ACTIVE_USER_JSESSIONID = "_active_user";

	public static final int TYPE_OF_DEFAULT = 1;
	public static final int TYPE_OF_ANALYST = 3;
	public static final int TYPE_OF_AUDIT = 5;
	public static final int TYPE_OF_ADMIN = 9;

	/** 用户 ID */
	private Long id;

	/** 用户名 */
	@NotBlank
	private String username;

	/** 用户类型 (1:录入员, 3:分析员, 5:审核员) */
	@NotNull
	private Integer type;

	/** 姓名 */
	@NotBlank
	private String name;

	/** 签名图 */
	private String signImg;

	/** 部门,科室 */
	private String dept;

	/** 手机 */
	private String phone;

	/** 用户状态 */
	private Boolean status;

	/** 密码 */
	private String password;

	/** 加密salt */
	private String salt;

	/** 最近一次登录时间 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private Date loginTime;

	/** 更新时间 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
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
	 * @return the {@link #username}
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the {@link #username} to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the {@link #type}
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the {@link #type} to set
	 */
	public void setType(Integer type) {
		this.type = type;
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
	 * @return the {@link #signImg}
	 */
	public String getSignImg() {
		return signImg;
	}

	/**
	 * @param signImg
	 *            the {@link #signImg} to set
	 */
	public void setSignImg(String signImg) {
		this.signImg = signImg;
	}

	/**
	 * @return the {@link #dept}
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * @param dept
	 *            the {@link #dept} to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
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
	 * @return the {@link #password}
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the {@link #password} to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the {@link #status}
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the {@link #status} to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @return the {@link #salt}
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt
	 *            the {@link #salt} to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * @return the {@link #loginTime}
	 */
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime
	 *            the {@link #loginTime} to set
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
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

	@Override
	public String toString() {
		return new StringBuilder().append(this.getClass().getSimpleName()).append(" {").append("\n\t id=").append(id).append(",\n\t username=").append(username)
				.append(",\n\t type=").append(type).append(",\n\t name=").append(name).append(",\n\t dept=").append(dept).append(",\n\t phone=").append(phone)
				.append(",\n\t password=").append(password).append(",\n\t salt=").append(salt).append(", status=").append(status).append(", loginTime=")
				.append(loginTime).append(", gmtModified=").append(gmtModified).append("\n}").toString();
	}

}
