package com.gb.chrom.model.query;

import com.gb.chrom.domain.Paginator;

/**
 * @since 1.0
 * @author Danson
 */
public class AnnouncementQuery extends Paginator {

	private static final long serialVersionUID = 1L;
	
	/** 状态 */
	private boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}



}
