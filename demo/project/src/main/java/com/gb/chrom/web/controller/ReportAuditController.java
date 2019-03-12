package com.gb.chrom.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.ReportService;
import com.gb.chrom.core.service.SpecimenTypeService;
import com.gb.chrom.model.AnalysisReportAudit;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.AnalysisReportAuditQuery;
import com.gb.chrom.model.query.AnalysisReportQuery;
import com.gb.chrom.model.query.SpecimenTypeQuery;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * 
 * @author Summer
 */
@Controller
@RequestMapping(value = "/report/audit")
public class ReportAuditController {

	@Autowired
	private SpecimenTypeService specimenTypeService;
	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value = "/list.html")
	public String analysisReportAuditList(Model model) {
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));
		return "view/report/report-audit-list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult getAnalysisRportList(AnalysisReportQuery query) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);
		if (User.TYPE_OF_ADMIN != user.getType()) {
			query.setAuditorId(user.getId());
		}
		//query.setAuditStatus(SpecimenStatus.STATUS_OF_AUDIT_WAITING);
		return PaginatorJsonResult.getPaginatorResult(reportService.queryAnalysisReportForPagingList(query));
	}
	
	@RequestMapping(value = "/{id}.html")
	public String auditAnalysisReport(@PathVariable("id") long reportId, Model model) {
		model.addAttribute("report", reportService.queryAnalysisReport(reportId));
		return "view/report/audit-report";
	}
	
	@RequestMapping(value = "/{id}/submit")
	public @ResponseBody JsonResult submitAnalysisRportAuditResult(@PathVariable("id") long reportId, AnalysisReportAudit audit) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);
		
//		AnalysisReport report = reportService.queryAnalysisReport(reportId);
//		
//		if (User.TYPE_OF_AUDIT != user.getType() || (report.getMasterAuditorId() != user.getId().longValue() && user.getId().longValue() != report.getDeputyAuditorId())) {
//			return JsonResult.error("当前用户无此审核权限!");
//		}
		
		audit.setReportId(reportId);
		audit.setAuditorId(user.getId());
		audit.setAuditorName(user.getName());
		return JsonResult.getJsonResult(reportService.auditAnalysisReport(audit));
	}
	
	@RequestMapping(value = "/records/list.html")
	public String reportAuditRecordsList(Model model) {
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));
		return "view/report/audit-records";
	}

	@RequestMapping(value = "/records/list")
	public @ResponseBody PaginatorJsonResult getRportAuditRecordsList(AnalysisReportAuditQuery query) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);
		if (User.TYPE_OF_ADMIN != user.getType()) {
			query.setAuditorId(user.getId());
		}
		return PaginatorJsonResult.getPaginatorResult(reportService.queryAnalysisReportAuditForPagingList(query));
	}
	
}
