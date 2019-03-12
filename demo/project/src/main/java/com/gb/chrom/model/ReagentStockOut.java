package com.gb.chrom.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateSerializer;

/**
 * 试剂出库单
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月23日
 * @since
 */
public class ReagentStockOut implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	/** 出库时间 */
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date outTime;

	/** 批次号 */
	@NotBlank
	private String batchNo;

	/** 试剂ID */
	@NotNull
	private Long reagentId;

	/** 试剂名称 */
	private String reagent;

	/** 入库数量 */
	@NotNull
	private Long quantity;

	/** 备注 */
	private String remark;

	/** 操作用户ID */
	private Long operatorId;

	/** 操作用户ID */
	private String operator;

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
	 * @return the {@link #outTime}
	 */
	public Date getOutTime() {
		return outTime;
	}

	/**
	 * @param outTime the {@link #outTime} to set
	 */
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	/**
	 * @return the {@link #batchNo}
	 */
	public String getBatchNo() {
		return batchNo;
	}

	/**
	 * @param batchNo the {@link #batchNo} to set
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * @return the {@link #reagentId}
	 */
	public Long getReagentId() {
		return reagentId;
	}

	/**
	 * @param reagentId the {@link #reagentId} to set
	 */
	public void setReagentId(Long reagentId) {
		this.reagentId = reagentId;
	}

	/**
	 * @return the {@link #reagent}
	 */
	public String getReagent() {
		return reagent;
	}

	/**
	 * @param reagent the {@link #reagent} to set
	 */
	public void setReagent(String reagent) {
		this.reagent = reagent;
	}

	/**
	 * @return the {@link #quantity}
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the {@link #quantity} to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
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
	 * @return the {@link #operatorId}
	 */
	public Long getOperatorId() {
		return operatorId;
	}

	/**
	 * @param operatorId the {@link #operatorId} to set
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * @return the {@link #operator}
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator the {@link #operator} to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

}
