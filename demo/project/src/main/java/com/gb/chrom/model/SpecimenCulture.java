package com.gb.chrom.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateSerializer;

/**
 * 标本培养过程
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年7月25日
 * @since
 */
public class SpecimenCulture implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 编号 */
	private Long id;

	/** 标本ID */
	private Long specimenId;

	/** 处理者ID */
	private Long handlerId;

	/** 处理顺序 */
	@NotNull
	private Integer processOrder;

	/** 步骤 */
	@NotBlank
	private String processStep;

	/** 时间 */
	@NotNull
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date processTime;

	/** 备注 */
	private String remark;

	/** 状态 */
	@NotNull
	private Boolean completedStatus;

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
	 * @return the {@link #handlerId}
	 */
	public Long getHandlerId() {
		return handlerId;
	}

	/**
	 * @param handlerId the {@link #handlerId} to set
	 */
	public void setHandlerId(Long handlerId) {
		this.handlerId = handlerId;
	}

	/**
	 * @return the {@link #processOrder}
	 */
	public Integer getProcessOrder() {
		return processOrder;
	}

	/**
	 * @param processOrder the {@link #processOrder} to set
	 */
	public void setProcessOrder(Integer processOrder) {
		this.processOrder = processOrder;
	}

	/**
	 * @return the {@link #processStep}
	 */
	public String getProcessStep() {
		return processStep;
	}

	/**
	 * @param processStep the {@link #processStep} to set
	 */
	public void setProcessStep(String processStep) {
		this.processStep = processStep;
	}

	/**
	 * @return the {@link #processTime}
	 */
	public Date getProcessTime() {
		return processTime;
	}

	/**
	 * @param processTime the {@link #processTime} to set
	 */
	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	/**
	 * @return the {@link #remark}
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the {@link #remark} to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the {@link #completedStatus}
	 */
	public Boolean getCompletedStatus() {
		return completedStatus;
	}

	/**
	 * @param completedStatus the {@link #completedStatus} to set
	 */
	public void setCompletedStatus(Boolean completedStatus) {
		this.completedStatus = completedStatus;
	}

}
