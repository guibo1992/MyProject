package com.gb.chrom.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.ReportAnomalyService;
import com.gb.chrom.core.service.SpecimenTypeService;
import com.gb.chrom.model.ReportAnomaly;
import com.gb.chrom.model.query.ReportAnomalyQuery;
import com.gb.chrom.model.query.SpecimenTypeQuery;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * @author Summer
 *
 *         2018年8月2日
 */
@Controller
@RequestMapping(value = "/report/anomaly")
public class ReportAnomalyController {

	@Autowired
	private SpecimenTypeService specimenTypeService;
	@Autowired
	private ReportAnomalyService reportAnomalyService;

	@RequestMapping(value = "/list.html")
	public String reportAnomaly(Model model) {
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));
		return "view/analys/anomaly-list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult getReportAnomalyList(ReportAnomalyQuery query) {
		return PaginatorJsonResult.getPaginatorResult(reportAnomalyService.queryReportAnomalyForPagingList(query));
	}

	@RequestMapping(value = "/type/{typeId}/list")
	public @ResponseBody List<ReportAnomaly> getReportAnomalyFullList(@PathVariable("typeId") int typeId) {
		ReportAnomalyQuery query = new ReportAnomalyQuery();
		query.setCount(false);
		query.setTypeId(typeId);
		query.setLimit(Integer.MAX_VALUE);
		return reportAnomalyService.queryReportAnomalyForPagingList(query).getList();
	}

	@RequestMapping(value = "/{id}")
	public @ResponseBody JsonResult getReportAnomaly(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(reportAnomalyService.queryReportAnomaly(id), "查询出错!");
	}

	@RequestMapping(value = "/add")
	public @ResponseBody JsonResult addReportAnomaly(ReportAnomaly anomaly, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}

		return JsonResult.getJsonResult(reportAnomalyService.addReportAnomaly(anomaly), "操作失败!");
	}

	@RequestMapping(value = "/{id}/update")
	public @ResponseBody JsonResult updateReportAnomaly(@PathVariable("id") long id, ReportAnomaly anomaly, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}

		anomaly.setId(id);
		return JsonResult.getJsonResult(reportAnomalyService.updateReportAnomaly(anomaly), "操作失败!");
	}

	@RequestMapping(value = "/{id}/delete")
	public @ResponseBody JsonResult deleteReportAnomaly(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(reportAnomalyService.deleteReportAnomaly(id), "操作失败!");
	}

}
