package com.gb.chrom.model.query;

import com.gb.chrom.domain.Paginator;

/**
 * @author Summer
 *
 *         2016年3月18日
 */
public class UserQuery extends Paginator {

	private static final long serialVersionUID = 1L;

	/** 用户类型 (1:标本管理, 5:分析报告管理, 7:报告审核, 9:系统管理) */
	private Integer type;

	/** 状态 */
	private Boolean status;

	/**
	 * @return the {@link #type}
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the {@link #type} to set
	 */
	public void setType(Integer type) {
		this.type = type;
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

}
