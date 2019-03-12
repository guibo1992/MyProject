package com.gb.chrom.model.query;

import com.gb.chrom.domain.Paginator;

/**
 * @author Summer
 *
 *         2018年8月1日
 */
public class ReportTemplateQuery extends Paginator {

	private static final long serialVersionUID = 1L;

	/** 类型ID */
	private Integer typeId;

	/** 状态 */
	private Boolean status;

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
	 * @return the {@link #status}
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the {@link #status} to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

}
