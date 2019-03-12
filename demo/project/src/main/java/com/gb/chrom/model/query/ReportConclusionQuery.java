package com.gb.chrom.model.query;

import com.gb.chrom.domain.Paginator;

/**
 * 分析结论
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月21日
 * @since
 */
public class ReportConclusionQuery extends Paginator {

	private static final long serialVersionUID = 1L;

	/** 类型 */
	private Integer typeId;

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

}