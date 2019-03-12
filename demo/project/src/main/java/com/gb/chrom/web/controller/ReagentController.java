package com.gb.chrom.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.ReagentService;
import com.gb.chrom.model.Reagent;
import com.gb.chrom.model.query.ReagentQuery;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月23日
 * @since
 */
@Controller
@RequestMapping(value = "/reagent")
public class ReagentController {

	@Autowired
	private ReagentService reagentService;

	@RequestMapping(value = "/list.html")
	public String reagentList(HttpServletRequest request) {
		return "view/reagent-list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult getReagentList(ReagentQuery query) {
		return PaginatorJsonResult.getPaginatorResult(reagentService.queryReagentForPagingList(query));
	}

	@RequestMapping(value = "/{id}")
	public @ResponseBody JsonResult getReagent(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(reagentService.queryReagent(id), "操作失败!");
	}

	@RequestMapping(value = "/add")
	public @ResponseBody JsonResult addReagent(Reagent reagent) {
		return JsonResult.getJsonResult(reagentService.addReagent(reagent), "试剂添加失败!");
	}

	@RequestMapping(value = "/{id}/update")
	public @ResponseBody JsonResult updateReagent(@PathVariable("id") long id, Reagent reagent) {
		reagent.setId(id);
		return JsonResult.getJsonResult(reagentService.updateReagent(reagent), "试剂更新失败!");
	}

}
