package com.gb.chrom.web.controller;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.ConfigurationService;
import com.gb.chrom.core.service.ReportService;
import com.gb.chrom.core.service.SpecimenTypeService;
import com.gb.chrom.model.AnalysisReport;
import com.gb.chrom.model.Configuration;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.AnalysisReportQuery;
import com.gb.chrom.model.query.SpecimenTypeQuery;
import com.gb.chrom.utils.ReportDocumentBuilder;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * 
 * @author Summer
 */
@Controller
@RequestMapping(value = "/analysis/report")
public class ReportController {

	@Autowired
	private SpecimenTypeService specimenTypeService;
	@Autowired
	private ReportService reportService;

	@Autowired
	private ConfigurationService configurationService;
	@Autowired
	private MultipartConfigElement multipartConfigElement;

	@RequestMapping(value = "/list.html")
	public String analysisRportList(Model model) {
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));
		return "view/report/report-list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult getAnalysisRportList(AnalysisReportQuery query) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);

		if (user.getType() <= User.TYPE_OF_ANALYST) {
			query.setSubmitterId(user.getId());
		}

		return PaginatorJsonResult.getPaginatorResult(reportService.queryAnalysisReportForPagingList(query));
	}

	@RequestMapping(value = "/submit")
	public @ResponseBody JsonResult submitAnalysisReport(AnalysisReport report) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);
		report.setSubmitterId(user.getId());

		return JsonResult.getJsonResult(reportService.submitAnalysisReport(report), "报告提交失败!");
	}

	@RequestMapping(value = "/{id}/edit.html")
	public String analysisRportDetail(@PathVariable("id") long reportId, Model model) {
		model.addAttribute("report", reportService.queryAnalysisReport(reportId));
		return "view/report/report-edit";
	}

	@RequestMapping(value = "{id}/resubmit")
	public @ResponseBody JsonResult resubmitAnalysisReport(@PathVariable("id") long id, AnalysisReport report) {
		report.setId(id);
		return JsonResult.getJsonResult(reportService.resubmitAnalysisReport(report), "报告重新提交失败!");
	}

	@RequestMapping(value = "/info/{id}.html")
	public String analysisRport(@PathVariable("id") long reportId, Model model) {
		model.addAttribute("report", reportService.queryAnalysisReport(reportId));
		return "view/report/report-info";
	}

	@RequestMapping(value = "/{id}/print")
	public @ResponseBody JsonResult printAnalysisRport(@PathVariable("id") long reportId, HttpServletResponse response) throws Exception {
		String filename = new StringBuilder("/report/").append(reportId).append(".pdf").toString();
		File file = new File(multipartConfigElement.getLocation(), filename);

//		if (file.exists()) {
//			return JsonResult.getJsonResult(filename);
//		}
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		Configuration config = configurationService.getConfiguration();
		AnalysisReport report = reportService.queryAnalysisReport(reportId);
		try {
			new ReportDocumentBuilder(file.getAbsolutePath()).build(report, config.getHospName(), config.getHospLogo(), multipartConfigElement.getLocation());
		} catch (Exception e) {
			file.delete();
			throw e;
		}
		return JsonResult.getJsonResult(filename);
	}

	@RequestMapping(value = "/{id}/view.html")
	public String viewAnalysisRport(@PathVariable("id") long reportId, Model model) {
		model.addAttribute("typeList", reportService.queryAnalysisReport(reportId));
		return "view/report/report-view";
	}

}
