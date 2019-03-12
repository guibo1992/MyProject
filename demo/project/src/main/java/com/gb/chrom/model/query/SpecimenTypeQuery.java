package com.gb.chrom.model.query;

import com.gb.chrom.domain.Paginator;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月3日
 * @since
 */
public class SpecimenTypeQuery extends Paginator {

	private static final long serialVersionUID = 1L;

	/** 状态 */
	private Boolean status;
	
	public SpecimenTypeQuery() {
	}
	
	public SpecimenTypeQuery(boolean status) {
		this.status = status;
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
