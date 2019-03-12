package com.gb.chrom.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.ReportConclusionService;
import com.gb.chrom.core.service.SpecimenTypeService;
import com.gb.chrom.model.ReportConclusion;
import com.gb.chrom.model.query.ReportConclusionQuery;
import com.gb.chrom.model.query.SpecimenTypeQuery;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * @author Summer
 *
 *         2018年8月2日
 */
@Controller
@RequestMapping(value = "/report/conclusion")
public class ReportConclusionController {

	@Autowired
	private SpecimenTypeService specimenTypeService;
	@Autowired
	private ReportConclusionService reportConclusionService;

	@RequestMapping(value = "/list.html")
	public String reportConclusion(Model model) {
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));
		return "view/analys/conclusion-list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult getReportConclusionList(ReportConclusionQuery query) {
		return PaginatorJsonResult.getPaginatorResult(reportConclusionService.queryReportConclusionForPagingList(query));
	}

	@RequestMapping(value = "/type/{typeId}/list")
	public @ResponseBody List<ReportConclusion> getReportConclusionFullList(@PathVariable("typeId") int typeId) {
		ReportConclusionQuery query = new ReportConclusionQuery();
		query.setCount(false);
		query.setTypeId(typeId);
		query.setLimit(Integer.MAX_VALUE);
		return reportConclusionService.queryReportConclusionForPagingList(query).getList();
	}

	@RequestMapping(value = "/{id}")
	public @ResponseBody JsonResult getReportConclusion(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(reportConclusionService.queryReportConclusion(id), "查询出错!");
	}

	@RequestMapping(value = "/add")
	public @ResponseBody JsonResult addReportConclusion(ReportConclusion conclusion, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}

		return JsonResult.getJsonResult(reportConclusionService.addReportConclusion(conclusion), "操作失败!");
	}

	@RequestMapping(value = "/{id}/update")
	public @ResponseBody JsonResult updateReportConclusion(@PathVariable("id") long id, ReportConclusion conclusion, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}

		conclusion.setId(id);
		return JsonResult.getJsonResult(reportConclusionService.updateReportConclusion(conclusion), "操作失败!");
	}

	@RequestMapping(value = "/{id}/delete")
	public @ResponseBody JsonResult deleteReportConclusion(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(reportConclusionService.deleteReportConclusion(id), "操作失败!");
	}

}
