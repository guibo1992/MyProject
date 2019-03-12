package com.gb.chrom.web.controller;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.SpecimenCultureService;
import com.gb.chrom.core.service.SpecimenTypeService;
import com.gb.chrom.model.SpecimenCulture;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.SpecimenQuery;
import com.gb.chrom.model.query.SpecimenTypeQuery;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * @author Summer
 *
 *         2018年7月29日
 */
@Controller
@RequestMapping(value = "/specimen/culture")
public class SpcimenProcessController {

	@Autowired
	private SpecimenTypeService specimenTypeService;
	@Autowired
	private SpecimenCultureService specimenCultureService;

	@GetMapping(value = "/list.html")
	public String spcimenCultureList(Model model) {
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));
		return "view/cyto/specimen-culture";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult specimenList(SpecimenQuery query) {
		return PaginatorJsonResult.getPaginatorResult(specimenCultureService.queryCultureSpecimenForPagingList(query));
	}

	@PostMapping(value = "/add")
	public @ResponseBody JsonResult addSpecimenCultureProcess(SpecimenCulture culture, BindingResult result) {
		if (result.hasErrors() || null == culture.getSpecimenId()) {
			return JsonResult.error("错误的参数提交!");
		}

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);
		culture.setHandlerId(user.getId());
		return JsonResult.getJsonResult(specimenCultureService.addSpecimenCultureProcess(culture), "操作失败!");
	}

	@GetMapping(value = "/{id}")
	public @ResponseBody JsonResult getpecimenCulture(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(specimenCultureService.querySpecimenCulture(id));
	}

	@GetMapping(value = "/process/list")
	public @ResponseBody List<String> getSpecimenIdList(int typeId, int index) {
		return specimenCultureService.queryCultureSpecimenNoForList(typeId, index);
	}

	@PostMapping(value = "/batch/process")
	public @ResponseBody JsonResult batchCultureSpecimenList(SpecimenCulture culture, BindingResult result, String specimenNos) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);
		culture.setHandlerId(user.getId());

		String[] numArray = specimenNos.split(",");
		if (ArrayUtils.isEmpty(numArray)) {
			return JsonResult.error("错误的参数提交!");
		}

		return JsonResult.getJsonResult(specimenCultureService.processSpecimenListProcess(culture, numArray), "批量操作失败!");
	}

}
