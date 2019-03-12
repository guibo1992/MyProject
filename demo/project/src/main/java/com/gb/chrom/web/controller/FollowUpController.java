package com.gb.chrom.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.FollowupService;
import com.gb.chrom.core.service.SpecimenService;
import com.gb.chrom.model.Followup;
import com.gb.chrom.model.FollowupRecord;
import com.gb.chrom.model.query.FollowupQuery;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * 随访
 * <p>
 * 
 * @author Summer
 *         </p>
 *         Created by 2018年4月3日
 * @since
 */
@Controller
@RequestMapping(value = "/followup")
public class FollowUpController {

	@Autowired
	private FollowupService followupService;
	@Autowired
	private SpecimenService specimenService;

	@RequestMapping(value = "/list.html")
	public String followUp(HttpServletRequest request) {
		return "view/followup-list";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult getFollowUpList(FollowupQuery query) {
		return PaginatorJsonResult.getPaginatorResult(followupService.queryFollowupForPagingList(query));
	}

	@RequestMapping(value = "/{id}/records.html")
	public String followUpRecordList(@PathVariable("id") long id, Model model) {
		Followup followup = followupService.queryFollowup(id);
		model.addAttribute("followup", followup);
		model.addAttribute("recordList", followupService.queryFollowupRecordForList(followup.getSpecimenNo()));

		return "view/followup-records";
	}

	@RequestMapping(value = "/record/{id}")
	public @ResponseBody JsonResult getFollowUpRecord(@PathVariable("id") long id) {
		return JsonResult.success(followupService.queryFollowupRecord(id));
	}

	@RequestMapping(value = "/record/add.html")
	public String toAddFollowUpRecord(FollowupRecord record) {
		return "view/followup-add";
	}

	@RequestMapping(value = "/record/add")
	public @ResponseBody JsonResult addFollowUpRecord(@Valid FollowupRecord record, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}
		if (!specimenService.validateSpecimenNo(record.getSpecimenNo())) {
			return JsonResult.error("无效的标本编号!");
		}

		return JsonResult.getJsonResult(followupService.addFollowupRecord(record), "随访记录保存失败!");
	}

	@RequestMapping(value = "/record/{id}/update")
	public @ResponseBody JsonResult updateFollowUp(FollowupRecord record) {
		return JsonResult.getJsonResult(followupService.updateFollowupRecord(record), "随访记录更新失败!");
	}

}
