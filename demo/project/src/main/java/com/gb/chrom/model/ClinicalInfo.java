package com.gb.chrom.model;

public class ClinicalInfo implements java.io.Serializable {

	/**
	 * 临床信息
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**信息id*/
	private long infoId;
	
	/**临床信息*/
	private String information;
	
	/**所属类型头部*/
	private char typeHead;

	public long getInfoId() {
		return infoId;
	}

	public void setInfoId(long infoId) {
		this.infoId = infoId;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public char getTypeHead() {
		return typeHead;
	}

	public void setTypeHead(char typeHead) {
		this.typeHead = typeHead;
	}

}
