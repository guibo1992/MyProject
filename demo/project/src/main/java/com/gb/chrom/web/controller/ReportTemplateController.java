package com.gb.chrom.web.controller;

import java.io.File;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.ConfigurationService;
import com.gb.chrom.core.service.ReportTemplateService;
import com.gb.chrom.core.service.SpecimenTypeService;
import com.gb.chrom.core.service.UserService;
import com.gb.chrom.model.AnalysisReport;
import com.gb.chrom.model.Configuration;
import com.gb.chrom.model.ReportTemplate;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.ReportTemplateQuery;
import com.gb.chrom.model.query.SpecimenTypeQuery;
import com.gb.chrom.model.query.UserQuery;
import com.gb.chrom.utils.ReportDocumentBuilder;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月3日
 * @since
 */
@Controller
@RequestMapping(value = "/report/template")
public class ReportTemplateController {

	@Autowired
	private SpecimenTypeService specimenTypeService;
	@Autowired
	private ReportTemplateService reportTemplateService;

	@Autowired
	private UserService userService;
	@Autowired
	private ConfigurationService configurationService;
	@Autowired
	private MultipartConfigElement multipartConfigElement;

	@RequestMapping(value = "/list.html")
	public String reportTemplateList(HttpServletRequest request, Model model) {
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));
		return "view/analys/report-template-list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult getReportTemplateList(ReportTemplateQuery query) {
		return PaginatorJsonResult.getPaginatorResult(reportTemplateService.queryReportTemplateForPagingList(query));
	}

	@RequestMapping(value = "/{typeId}/list")
	public @ResponseBody JsonResult getReportTemplateList(@PathVariable("typeId") int typeId) {
		ReportTemplateQuery query = new ReportTemplateQuery();
		query.setTypeId(typeId);
		query.setStatus(true);
		query.setLimit(Integer.MAX_VALUE);
		List<ReportTemplate> list = reportTemplateService.queryReportTemplateForPagingList(query).getList();

		if (CollectionUtils.isEmpty(list)) {
			return JsonResult.error("无法获取此类型的模板数据,请检查是否已设置!");
		}

		return JsonResult.getJsonResult(list);
	}

	@RequestMapping(value = "/add.html")
	public String getReportTemplate(Model model) {
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));

		UserQuery query = new UserQuery();
		query.setLimit(Integer.MAX_VALUE);
		query.setType(User.TYPE_OF_AUDIT);
		query.setStatus(true);
		model.addAttribute("userList", userService.queryUserForPagingList(query).getList());

		return "view/analys/report-template";
	}

	@RequestMapping(value = "/{id}.html")
	public String getReportTemplate(@PathVariable("id") long id, Model model) {
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));
		model.addAttribute("template", reportTemplateService.queryReportTemplate(id));

		UserQuery query = new UserQuery();
		query.setLimit(Integer.MAX_VALUE);
		query.setType(User.TYPE_OF_AUDIT);
		query.setStatus(true);
		model.addAttribute("userList", userService.queryUserForPagingList(query).getList());

		return "view/analys/report-template";
	}

	@RequestMapping(value = "/add")
	public @ResponseBody JsonResult addReportTemplate(ReportTemplate template, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}
		return JsonResult.getJsonResult(reportTemplateService.addReportTemplate(template));
	}

	@RequestMapping(value = "/{id}/update")
	public @ResponseBody JsonResult getReportTemplate(@PathVariable("id") long id, ReportTemplate template, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}
		template.setId(id);
		return JsonResult.getJsonResult(reportTemplateService.updateReportTemplate(template));
	}

	@RequestMapping(value = "/{id}/remove")
	public @ResponseBody JsonResult removeReportTemplate(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(reportTemplateService.removeReportTemplate(id));
	}

	@RequestMapping(value = "/{id}/undelete")
	public @ResponseBody JsonResult undeleteReportTemplate(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(reportTemplateService.undeleteReportTemplate(id));
	}

	@RequestMapping(value = "/test/print")
	public @ResponseBody JsonResult getReportPdf(@RequestBody AnalysisReport report, HttpServletResponse response) throws Exception {
		String filename = multipartConfigElement.getLocation() + "/report/exmaple.pdf";
		File file = new File(filename);

		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		Configuration config = configurationService.getConfiguration();
		new ReportDocumentBuilder(filename).build(report, config.getHospName(), config.getHospLogo(), multipartConfigElement.getLocation());

		return JsonResult.getJsonResult(filename);
	}

}
