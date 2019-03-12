package com.gb.chrom.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.ReportInterpretationService;
import com.gb.chrom.core.service.SpecimenTypeService;
import com.gb.chrom.model.ReportInterpretation;
import com.gb.chrom.model.query.ReportInterpretationQuery;
import com.gb.chrom.model.query.SpecimenTypeQuery;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * @author Summer
 *
 *         2018年8月2日
 */
@Controller
@RequestMapping(value = "/report/interpretation")
public class ReportInterpretationController {

	@Autowired
	private SpecimenTypeService specimenTypeService;
	@Autowired
	private ReportInterpretationService reportInterpretationService;

	@RequestMapping(value = "/list.html")
	public String ReportInterpretation(Model model) {
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));
		return "view/analys/interpret-list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult getReportInterpretationList(ReportInterpretationQuery query) {
		return PaginatorJsonResult.getPaginatorResult(reportInterpretationService.queryReportInterpretationForPagingList(query));
	}

	@RequestMapping(value = "/type/{typeId}/list")
	public @ResponseBody List<ReportInterpretation> getReportInterpretationFullList(@PathVariable("typeId") int typeId) {
		ReportInterpretationQuery query = new ReportInterpretationQuery();
		query.setCount(false);
		query.setTypeId(typeId);
		query.setLimit(Integer.MAX_VALUE);
		return reportInterpretationService.queryReportInterpretationForPagingList(query).getList();
	}

	@RequestMapping(value = "/{id}")
	public @ResponseBody JsonResult getReportInterpretation(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(reportInterpretationService.queryReportInterpretation(id), "查询出错!");
	}

	@RequestMapping(value = "/add")
	public @ResponseBody JsonResult addReportInterpretation(ReportInterpretation interpretation, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}

		return JsonResult.getJsonResult(reportInterpretationService.addReportInterpretation(interpretation), "操作失败!");
	}

	@RequestMapping(value = "/{id}/update")
	public @ResponseBody JsonResult updateReportInterpretation(@PathVariable("id") long id, ReportInterpretation interpretation, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}

		interpretation.setId(id);
		return JsonResult.getJsonResult(reportInterpretationService.updateReportInterpretation(interpretation), "操作失败!");
	}

	@RequestMapping(value = "/{id}/delete")
	public @ResponseBody JsonResult deleteReportInterpretation(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(reportInterpretationService.deleteReportInterpretation(id), "操作失败!");
	}

}
