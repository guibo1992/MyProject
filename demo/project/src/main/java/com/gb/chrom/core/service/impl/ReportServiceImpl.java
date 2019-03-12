package com.gb.chrom.core.service.impl;

import java.util.Calendar;

import javax.servlet.MultipartConfigElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gb.chrom.constants.SpecimenStatus;
import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.ReportAuditMapper;
import com.gb.chrom.core.mapper.ReportMapper;
import com.gb.chrom.core.mapper.ReportTemplateMapper;
import com.gb.chrom.core.mapper.SpecimenMapper;
import com.gb.chrom.core.service.ReportService;
import com.gb.chrom.model.AnalysisReport;
import com.gb.chrom.model.AnalysisReportAudit;
import com.gb.chrom.model.ReportTemplate;
import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.query.AnalysisReportAuditQuery;
import com.gb.chrom.model.query.AnalysisReportQuery;
import com.gb.chrom.utils.ImageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ReportServiceImpl implements ReportService {

	private static Logger logger = LoggerFactory.getLogger(ReportService.class);

	@Autowired
	private SpecimenMapper specimenMapper;
	@Autowired
	private ReportMapper reportMapper;
	@Autowired
	private ReportAuditMapper reportAuditMapper;
	@Autowired
	private ReportTemplateMapper reportTemplateMapper;
	
	@Autowired
	private MultipartConfigElement multipartConfigElement;

	@Transactional
	@Override
	public boolean submitAnalysisReport(AnalysisReport report) {
		try {
			Specimen specimen = specimenMapper.findSpecimenBySpecimenNo(report.getSpecimenNo());
			ReportTemplate template = reportTemplateMapper.findReportTemplateById(report.getTemplateId());
			
			report.setDetectionMethod(template.getHideDetectionMethod() ? null : report.getDetectionMethod());
			report.setRemarks(template.getHideRemarks() ? null : report.getRemarks());
			
			// report.setPatientName(specimen.getPatient().getName());
			// report.setPatientSex(specimen.getPatient().getSex());
			// report.setPatientAge(String.valueOf(specimen.getPatient().getAge()));
			
			report.setAnalysisKarImg(specimen.getAnalysisKarImg());
			report.setAnalysisMetImg(specimen.getAnalysisMetImg());
			
			if (template.getHideResultSex()) {
				report.setAnalysisResult(report.getAnalysisResult().replaceAll("XY", "X?"));
				report.setAnalysisKarImg(ImageUtil.maskImage(multipartConfigElement.getLocation(), specimen.getAnalysisKarImg()));
			}
			
			
			report.setAuditStatus(SpecimenStatus.STATUS_OF_AUDIT_WAITING);
			reportMapper.saveAnalysisReport(report);

			specimen.setReportStatus(true);
			specimen.setReportTime(Calendar.getInstance().getTime());
			specimenMapper.updateSpecimen(specimen);

			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during submit analysis report.", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean resubmitAnalysisReport(AnalysisReport report) {
		try {
			report.setAuditStatus(SpecimenStatus.STATUS_OF_AUDIT_WAITING);
			reportMapper.updateAnalysisReport(report);
			return true;
		} catch (MapperException e) {
			logger.error("Exception occurred during resubmit analysis report :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public AnalysisReport queryAnalysisReport(long id) {
		try {
			AnalysisReport report = reportMapper.findAnalysisReportById(id);
			if (null != report) {
				report.setAuditList(reportAuditMapper.findReportAuditListByReportId(report.getId()));
			}
			return report;
		} catch (MapperException e) {
			logger.error("Exception occurred during query analysis report.", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageInfo<AnalysisReport> queryAnalysisReportForPagingList(AnalysisReportQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit());
			return reportMapper.findAnalysisReportForList(query).toPageInfo();
		} catch (MapperException e) {
			logger.error("Exception occurred during query analysis report for paging list.", e);
		}
		return new PageInfo<>();
	}

	@Transactional
	@Override
	public boolean auditAnalysisReport(AnalysisReportAudit reportAudit) {
		try {
			AnalysisReport report = reportMapper.findAnalysisReportById(reportAudit.getReportId());
			reportAudit.setReportName(report.getName());
			reportAudit.setHisId(report.getHisId());
			reportAudit.setSpecimenNo(report.getSpecimenNo());
			reportAudit.setInspectionType(report.getInspectionType());
			reportAudit.setAuditTime(Calendar.getInstance().getTime());
			reportAuditMapper.saveReportAudit(reportAudit);

			report.setAuditStatus(reportAudit.getAuditStatus() ? SpecimenStatus.STATUS_OF_AUDIT_PASSRD : SpecimenStatus.STATUS_OF_AUDIT_REJECT);
			int affect = reportMapper.updateAnalysisReport(report);

			return affect > 0;
		} catch (MapperException e) {
			logger.error("Exception occurred during audit analysis report :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageInfo<AnalysisReportAudit> queryAnalysisReportAuditForPagingList(AnalysisReportAuditQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit());
			return reportAuditMapper.findReportAuditForList(query).toPageInfo();
		} catch (MapperException e) {
			logger.error("Exception occurred during query analysis report audit record for paging list.", e);
		}
		return new PageInfo<>();
	}

}
