package com.gb.chrom.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateTimeSerializer;

/**
 * 报告结论
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月21日
 * @since
 */
public class ReportConclusion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private Long id;

	/** 类型 */
	@NotNull
	private Integer typeId;

	/** 标题 */
	@NotBlank
	private String title;

	/** 结论 */
	@NotBlank
	private String conclusion;

	/** 更新时间 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private Date gmtModified;

	/** 标本类型 */
	private SpecimenType type;

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
	 * @return the {@link #title}
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the {@link #title} to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the {@link #conclusion}
	 */
	public String getConclusion() {
		return conclusion;
	}

	/**
	 * @param conclusion the {@link #conclusion} to set
	 */
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
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

}
