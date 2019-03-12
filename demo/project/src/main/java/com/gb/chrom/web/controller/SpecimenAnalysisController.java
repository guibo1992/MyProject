package com.gb.chrom.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.ReportTemplateService;
import com.gb.chrom.core.service.SpecimenAnalysisService;
import com.gb.chrom.core.service.SpecimenService;
import com.gb.chrom.core.service.SpecimenTypeService;
import com.gb.chrom.core.service.UserService;
import com.gb.chrom.model.ReportTemplate;
import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.SpecimenQuery;
import com.gb.chrom.model.query.SpecimenTypeQuery;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * @author Summer
 *
 *         2018年7月31日
 */
@Controller
@RequestMapping(value = "/karyotype/analysis")
public class SpecimenAnalysisController {

	@Autowired
	private SpecimenTypeService specimenTypeService;
	@Autowired
	private SpecimenService specimenService;
	@Autowired
	private ReportTemplateService ReportTemplateService;
	@Autowired
	private SpecimenAnalysisService specimenAnalysisService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list.html")
	public String specimenAnalysisList(Model model) {
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));
		return "view/analys/analysis-list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult getSpecimenAnalysisList(SpecimenQuery query) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);

		if (user.getType() <= User.TYPE_OF_ANALYST) {
			query.setAnalystId(user.getId());
		}

		return PaginatorJsonResult.getPaginatorResult(specimenAnalysisService.querySpecimenAnalysisForPagingList(query));
	}

	@RequestMapping(value = "/{id}/result.html")
	public String viewAnalysisResult(@PathVariable("id") long id, Model model) {
		model.addAttribute("specimen", specimenService.querySpecimen(id));
		return "view/analys/analysis-result";
	}

	@RequestMapping(value = "/{id}/result")
	public @ResponseBody JsonResult getAnalysisResult(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(specimenService.querySpecimen(id));
	}

	@RequestMapping(value = "/{id}/tmpl-{templateId}/report.html")
	public String addAnalysisReport(@PathVariable("id") long id, @PathVariable("templateId") long templateId, Model model) {
		Specimen specimen = specimenService.querySpecimen(id);
		ReportTemplate template = ReportTemplateService.queryReportTemplate(templateId);
		
		model.addAttribute("specimen", specimen);
		model.addAttribute("template", template);
		
		User laboratorian = userService.queryUser(specimen.getLaboratorianId());
		model.addAttribute("laboratorian", laboratorian.getName());

		User masterAuditor = userService.queryUser(template.getMasterAuditorId());
		model.addAttribute("masterAuditor", masterAuditor.getName());

		if (null != template.getDeputyAuditorId()) {
			User deputyAuditor = userService.queryUser(template.getDeputyAuditorId());
			model.addAttribute("deputyAuditor", deputyAuditor.getName());
		}

		return "view/analys/analysis-report";
	}

}
