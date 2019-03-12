package com.gb.chrom.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateSerializer;

/**
 * 病理标本
 * 
 * @since 1.0
 * @author Summer
 */
public class Specimen implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private Long id;

	/** HIS ID */
	@NotBlank
	private String hisId;

	/** 标本类型 */
	@NotNull
	private Integer typeId;

	/** 标本序号 */
	private Long serialNo;

	/** 标本号(type + serialNo) */
	private String specimenNo;

	/** 患者ID */
	private Long patientId;

	/** 单双线类型 */
	private String lineType;

	/** 单线打印数量 */
	private Integer linePrintCount;

//	/** 缴费情况 (0, 1, 2) */
//	private Integer payStatus;

	/** 送检医生 */
	private String inspectionPhysician;

	/** 送检部门 */
	private String inspectionDept;

	/** 培养完成状态 */
	private Boolean cultureStatus;

	/** 培养完成时间 */
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date cultureCompletedTime;

	/** 分析完成状态 */
	private Boolean analysisStatus;

	/** 分析完成时间 */
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date analysisCompletedTime;

	/** 报告生成状态 */
	private Boolean reportStatus;

	/** 报告生成日期 */
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date reportTime;

//	/** 报告完成状态 */
//	private Boolean reportCompletedStatus;

	/** 报告完成日期 */
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date reportCompletedTime;

	/** 分析结果 */
	private String analysisResult;

	/** 分析结果图 */
	private String analysisMetImg;

	/** 分析结果排序图 */
	private String analysisKarImg;

	/** 检测人ID */
	private Long laboratorianId;

	/** 备注 */
	private String remarks;

	/** 有效状态 */
	private Boolean status;

	/** 创建人ID */
	private Long createrId;

	/** 创建时间 */
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date gmtCreate;

	/** 类型 */
	private SpecimenType type;

	/** 病患信息 */
	private Patient patient;

	/** 临床信息 */
	private List<SpecimenClinicalInfo> clinicalInfoList;

	/** 培养过程 */
	private List<SpecimenCulture> cultureList;

	private User laboratorian;

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
	 * @return the {@link #hisId}
	 */
	public String getHisId() {
		return hisId;
	}

	/**
	 * @param hisId the {@link #hisId} to set
	 */
	public void setHisId(String hisId) {
		this.hisId = hisId;
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
	 * @return the {@link #serialNo}
	 */
	public Long getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo the {@link #serialNo} to set
	 */
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}

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
	 * @return the {@link #patientId}
	 */
	public Long getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId the {@link #patientId} to set
	 */
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return the {@link #lineType}
	 */
	public String getLineType() {
		return lineType;
	}

	/**
	 * @param lineType the {@link #lineType} to set
	 */
	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	/**
	 * @return the {@link #linePrintCount}
	 */
	public Integer getLinePrintCount() {
		return linePrintCount;
	}

	/**
	 * @param linePrintCount the {@link #linePrintCount} to set
	 */
	public void setLinePrintCount(Integer linePrintCount) {
		this.linePrintCount = linePrintCount;
	}

//	/**
//	 * @return the {@link #payStatus}
//	 */
//	public Integer getPayStatus() {
//		return payStatus;
//	}
//
//	/**
//	 * @param payStatus the {@link #payStatus} to set
//	 */
//	public void setPayStatus(Integer payStatus) {
//		this.payStatus = payStatus;
//	}

//	/**
//	 * @return the {@link #cultureStatus}
//	 */
//	public Boolean getCultureStatus() {
//		return cultureStatus;
//	}
//
//	/**
//	 * @param cultureStatus the {@link #cultureStatus} to set
//	 */
//	public void setCultureStatus(Boolean cultureStatus) {
//		this.cultureStatus = cultureStatus;
//	}
//
//	/**
//	 * @return the {@link #analysisStatus}
//	 */
//	public Boolean getAnalysisStatus() {
//		return analysisStatus;
//	}
//
//	/**
//	 * @param analysisStatus the {@link #analysisStatus} to set
//	 */
//	public void setAnalysisStatus(Boolean analysisStatus) {
//		this.analysisStatus = analysisStatus;
//	}
//
//	/**
//	 * @return the {@link #reportedStatus}
//	 */
//	public Boolean getReportedStatus() {
//		return reportedStatus;
//	}
//
//	/**
//	 * @param reportedStatus the {@link #reportedStatus} to set
//	 */
//	public void setReportedStatus(Boolean reportedStatus) {
//		this.reportedStatus = reportedStatus;
//	}

	/**
	 * @return the inspectionPhysician
	 */
	public String getInspectionPhysician() {
		return inspectionPhysician;
	}

	/**
	 * @param inspectionPhysician the inspectionPhysician to set
	 */
	public void setInspectionPhysician(String inspectionPhysician) {
		this.inspectionPhysician = inspectionPhysician;
	}

	/**
	 * @return the inspectionDept
	 */
	public String getInspectionDept() {
		return inspectionDept;
	}

	/**
	 * @param inspectionDept the inspectionDept to set
	 */
	public void setInspectionDept(String inspectionDept) {
		this.inspectionDept = inspectionDept;
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

	/**
	 * @return the {@link #cultureCompletedTime}
	 */
	public Date getCultureCompletedTime() {
		return cultureCompletedTime;
	}

	/**
	 * @param cultureCompletedTime the {@link #cultureCompletedTime} to set
	 */
	public void setCultureCompletedTime(Date cultureCompletedTime) {
		this.cultureCompletedTime = cultureCompletedTime;
	}

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
	 * @return the {@link #analysisCompletedTime}
	 */
	public Date getAnalysisCompletedTime() {
		return analysisCompletedTime;
	}

	/**
	 * @param analysisCompletedTime the {@link #analysisCompletedTime} to set
	 */
	public void setAnalysisCompletedTime(Date analysisCompletedTime) {
		this.analysisCompletedTime = analysisCompletedTime;
	}

	/**
	 * @return the reportStatus
	 */
	public Boolean getReportStatus() {
		return reportStatus;
	}

	/**
	 * @param reportStatus the reportStatus to set
	 */
	public void setReportStatus(Boolean reportStatus) {
		this.reportStatus = reportStatus;
	}

	/**
	 * @return the reportTime
	 */
	public Date getReportTime() {
		return reportTime;
	}

	/**
	 * @param reportTime the reportTime to set
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
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
	 * @return the laboratorianId
	 */
	public Long getLaboratorianId() {
		return laboratorianId;
	}

	/**
	 * @param laboratorianId the laboratorianId to set
	 */
	public void setLaboratorianId(Long laboratorianId) {
		this.laboratorianId = laboratorianId;
	}

	/**
	 * @return the {@link #remarks}
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the {@link #remarks} to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	 * @return the {@link #gmtCreate}
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/**
	 * @param gmtCreate the {@link #gmtCreate} to set
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/**
	 * @return the {@link #type}
	 */
	public SpecimenType getType() {
		return type;
	}

	/**
	 * @param type the {@link #type} to set
	 */
	public void setType(SpecimenType type) {
		this.type = type;
	}

	/**
	 * @return the {@link #patient}
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient the {@link #patient} to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * @return the clinicalInfoList
	 */
	public List<SpecimenClinicalInfo> getClinicalInfoList() {
		return clinicalInfoList;
	}

	/**
	 * @param clinicalInfoList the clinicalInfoList to set
	 */
	public void setClinicalInfoList(List<SpecimenClinicalInfo> clinicalInfoList) {
		this.clinicalInfoList = clinicalInfoList;
	}

	/**
	 * @return the {@link #cultureList}
	 */
	public List<SpecimenCulture> getCultureList() {
		return cultureList;
	}

	/**
	 * @param cultureList the {@link #cultureList} to set
	 */
	public void setCultureList(List<SpecimenCulture> cultureList) {
		this.cultureList = cultureList;
	}

	/**
	 * @return the {@link #laboratorian}
	 */
	public User getLaboratorian() {
		return laboratorian;
	}

	/**
	 * @param laboratorian the {@link #laboratorian} to set
	 */
	public void setLaboratorian(User laboratorian) {
		this.laboratorian = laboratorian;
	}

	@JsonIgnore
	public String getClinicalInfo() {
		StringBuilder builder = new StringBuilder();

		if (CollectionUtils.isNotEmpty(this.clinicalInfoList)) {
			this.clinicalInfoList.forEach(ci -> {

				if (StringUtils.isNotBlank(ci.getClinicalInfo())) {
					builder.append(ci.getClinicalInfo());

					if (StringUtils.isNotBlank(ci.getClinicalRemarks())) {
						builder.append(",").append(ci.getClinicalRemarks());
					}
					builder.append(";");
				}
			});
		}
		return builder.toString();
	}

	@JsonIgnore
	public Map<String, SpecimenClinicalInfo> getClinicalInfoMap() {
		Map<String, SpecimenClinicalInfo> map = new HashMap<>();

		if (CollectionUtils.isNotEmpty(this.clinicalInfoList)) {
			this.clinicalInfoList.forEach(ci -> {
				map.put(ci.getClinicalInfo(), ci);
			});
		}
		return map;
	}

}
