package com.gb.chrom.model.query;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.gb.chrom.domain.Paginator;

/**
 * @since 1.0
 * @author Summer
 */
public class SpecimenQuery extends Paginator {

	private static final long serialVersionUID = 1L;

	/** 创建人ID */
	private Long createrId;

	/** 标本类型 */
	private Integer typeId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

//	/** 标本状态 */
//	private Integer specimenStatus;

	/** 培养状态 */
	private Boolean cultureStatus;

	/** 分析状态 */
	private Boolean analysisStatus;

	/** 报告状态 */
	private Boolean reportedStatus;

	/** 分析者ID */
	private Long analystId;

	/** 有效状态 */
	private Boolean status;

	/** 标本ID */
	private List<Long> idList;

	/**
	 * @return the {@link #createrId}
	 */
	public Long getCreaterId() {
		return createrId;
	}

	/**
	 * @param createrId the {@link #createrId} to set
	 */
	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
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
	 * @return the {@link #startDate}
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the {@link #startDate} to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the {@link #endDate}
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the {@link #endDate} to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the {@link #cultureStatus}
	 */
	public Boolean getCultureStatus() {
		return cultureStatus;
	}

	/**
	 * @param cultureStatus the {@link #cultureStatus} to set
	 */
	public void setCultureStatus(Boolean cultureStatus) {
		this.cultureStatus = cultureStatus;
	}

//	/**
//	 * @return the {@link #specimenStatus}
//	 */
//	public Integer getSpecimenStatus() {
//		return specimenStatus;
//	}
//
//	/**
//	 * @param specimenStatus the {@link #specimenStatus} to set
//	 */
//	public void setSpecimenStatus(Integer specimenStatus) {
//		this.specimenStatus = specimenStatus;
//	}

	/**
	 * @return the {@link #analysisStatus}
	 */
	public Boolean getAnalysisStatus() {
		return analysisStatus;
	}

	/**
	 * @param analysisStatus the {@link #analysisStatus} to set
	 */
	public void setAnalysisStatus(Boolean analysisStatus) {
		this.analysisStatus = analysisStatus;
	}

	/**
	 * @return the {@link #reportedStatus}
	 */
	public Boolean getReportedStatus() {
		return reportedStatus;
	}

	/**
	 * @param reportedStatus the {@link #reportedStatus} to set
	 */
	public void setReportedStatus(Boolean reportedStatus) {
		this.reportedStatus = reportedStatus;
	}

	/**
	 * @return the {@link #analystId}
	 */
	public Long getAnalystId() {
		return analystId;
	}

	/**
	 * @param analystId the {@link #analystId} to set
	 */
	public void setAnalystId(Long analystId) {
		this.analystId = analystId;
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

	/**
	 * @return the {@link #idList}
	 */
	public List<Long> getIdList() {
		return idList;
	}

	/**
	 * @param idList the {@link #idList} to set
	 */
	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}

}
