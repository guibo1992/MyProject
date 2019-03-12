package com.gb.chrom.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateTimeSerializer;

/**
 * 标本类型
 * <p>
 * 
 * @author Summer
 *         </p>
 *         Created by 2018年4月3日
 * @since
 */
public class SpecimenType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	public Integer id;

	/** 代表字符 */
	private String typeHead;

	/** 名称 */
	private String name;

	/** 分析Case模板 */
	private String caseTemplate;

	/** 备注 */
	private String remark;

	/** 状态 */
	private Boolean status;

	/** 创建时间 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private Date gmtCreated;

	/** 更新时间 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private Date gmtModified;

	/**
	 * @return the {@link #id}
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the {@link #id} to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the {@link #typeHead}
	 */
	public String getTypeHead() {
		return typeHead;
	}

	/**
	 * @param typeHead
	 *            the {@link #typeHead} to set
	 */
	public void setTypeHead(String typeHead) {
		this.typeHead = typeHead;
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
	 * @return the {@link #caseTemplate}
	 */
	public String getCaseTemplate() {
		return caseTemplate;
	}

	/**
	 * @param caseTemplate
	 *            the {@link #caseTemplate} to set
	 */
	public void setCaseTemplate(String caseTemplate) {
		this.caseTemplate = caseTemplate;
	}

	/**
	 * @return the {@link #remark}
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the {@link #remark} to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * @return the {@link #gmtCreated}
	 */
	public Date getGmtCreated() {
		return gmtCreated;
	}

	/**
	 * @param gmtCreated
	 *            the {@link #gmtCreated} to set
	 */
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
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

}
