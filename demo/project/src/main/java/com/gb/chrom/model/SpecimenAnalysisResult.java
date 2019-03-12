package com.gb.chrom.model;

import javax.validation.constraints.NotBlank;

/**
 * @author Summer
 *
 *         2018年7月31日
 */
public class SpecimenAnalysisResult implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 标本编号 */
	@NotBlank
	private String specimenNo;

	/** 分析完成时间 */
	private String completedTime;

	/** 分析结果 */
	@NotBlank
	private String analysisResult;

	/** 分析结果图 */
	private String analysisMetImg;

	/** 分析结果排序图 */
	private String analysisKarImg;

	/** 检验师 */
	private String laboratorian;

	/** 分析注解 */
	private String comment;

	/**
	 * @return the {@link #specimenNo}
	 */
	public String getSpecimenNo() {
		return specimenNo;
	}

	/**
	 * @param specimenNo the {@link #specimenNo} to set
	 */
	public void setSpecimenNo(String specimenNo) {
		this.specimenNo = specimenNo;
	}

	/**
	 * @return the {@link #completedTime}
	 */
	public String getCompletedTime() {
		return completedTime;
	}

	/**
	 * @param completedTime the {@link #completedTime} to set
	 */
	public void setCompletedTime(String completedTime) {
		this.completedTime = completedTime;
	}

	/**
	 * @return the {@link #analysisResult}
	 */
	public String getAnalysisResult() {
		return analysisResult;
	}

	/**
	 * @param analysisResult the {@link #analysisResult} to set
	 */
	public void setAnalysisResult(String analysisResult) {
		this.analysisResult = analysisResult;
	}

	/**
	 * @return the {@link #analysisMetImg}
	 */
	public String getAnalysisMetImg() {
		return analysisMetImg;
	}

	/**
	 * @param analysisMetImg the {@link #analysisMetImg} to set
	 */
	public void setAnalysisMetImg(String analysisMetImg) {
		this.analysisMetImg = analysisMetImg;
	}

	/**
	 * @return the {@link #analysisKarImg}
	 */
	public String getAnalysisKarImg() {
		return analysisKarImg;
	}

	/**
	 * @param analysisKarImg the {@link #analysisKarImg} to set
	 */
	public void setAnalysisKarImg(String analysisKarImg) {
		this.analysisKarImg = analysisKarImg;
	}

	/**
	 * @return the laboratorian
	 */
	public String getLaboratorian() {
		return laboratorian;
	}

	/**
	 * @param laboratorian the laboratorian to set
	 */
	public void setLaboratorian(String laboratorian) {
		this.laboratorian = laboratorian;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}
