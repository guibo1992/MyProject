package com.gb.chrom.web.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.SerialNumberRuleService;
import com.gb.chrom.core.service.SpecimenService;
import com.gb.chrom.core.service.SpecimenTypeService;
import com.gb.chrom.core.service.UserService;
import com.gb.chrom.model.SerialNumberRule;
import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.SpecimenQuery;
import com.gb.chrom.model.query.SpecimenTypeQuery;
import com.gb.chrom.model.query.UserQuery;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * @since 1.0
 * @author Summer
 */
@Controller
@RequestMapping(value = "/specimen")
public class SpecimenController {

	@Autowired
	private SpecimenService specimenService;
	@Autowired
	private SpecimenTypeService specimenTypeService;
	@Autowired
	private SerialNumberRuleService serialRuleNumberService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list.html")
	public String specimenList(Model model) {
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));
		return "view/cyto/specimen-list";
	}

	@RequestMapping(value = "/add.html")
	public String toAddspecimen(HttpServletRequest request, Model model) {
		Optional<SerialNumberRule> optional = Optional.ofNullable(serialRuleNumberService.getSerialNumberRule());
		if (!optional.isPresent()) {
			model.addAttribute("unconfigMessage", "标本编号生成规则未设定,请先前往设 定!");
		}
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));

		return "view/cyto/specimen-add";
	}

	@RequestMapping(value = "/{id}.html")
	public String toEditspecimen(@PathVariable("id") long id, Model model) {
		model.addAttribute("specimen", specimenService.querySpecimen(id));
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));

		return "view/cyto/specimen-edit";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult specimenList(SpecimenQuery query) {
		return PaginatorJsonResult.getPaginatorResult(specimenService.querySpecimenForPagingList(query));
	}

	@RequestMapping(value = "/{id}/info")
	public @ResponseBody JsonResult getSpecimen(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(specimenService.querySpecimen(id), "标本信息查询出错!");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody JsonResult addSpecimen(@Valid Specimen specimen, BindingResult result) {
		Optional<SerialNumberRule> optional = Optional.ofNullable(serialRuleNumberService.getSerialNumberRule());
		if (!optional.isPresent()) {
			return JsonResult.error("标本编号规则未设定,请先前往设 定!");
		}
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}
		
		if (Optional.ofNullable(specimenService.querySpecimen(specimen.getHisId())).isPresent()) {
			return JsonResult.error("该门诊号已经存在,请检查输入内容!");
		}

		UserQuery query = new UserQuery();
		query.setStatus(true);
		query.setLimit(Integer.MAX_VALUE);
		query.setType(User.TYPE_OF_ANALYST);
		List<User> analystList = userService.queryUserForPagingList(query).getList();

		if (CollectionUtils.isEmpty(analystList)) {
			return JsonResult.error("未检测到分析人员,标本无法分配!");
		}

		return JsonResult.getJsonResult(specimenService.addSpecimen(specimen, analystList), "标本信息保存出错!");
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public @ResponseBody JsonResult addSpecimen(@PathVariable("id") long id, @Valid Specimen specimen, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}
		specimen.setId(id);
		return JsonResult.getJsonResult(specimenService.updateSpecimen(specimen), "标本信息更新失败!");
	}

	@RequestMapping(value = "/{id}/remove")
	public @ResponseBody JsonResult removeSpecimen(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(specimenService.deleteSpecimen(id), "操作失败!");
	}

	@RequestMapping(value = "/{id}/undo")
	public @ResponseBody JsonResult undoSpecimen(@PathVariable("id") long id) {
		return JsonResult.getJsonResult(specimenService.undeleteSpecimen(id), "操作失败!");
	}

	@RequestMapping(value = "/sn/list")
	public @ResponseBody List<Specimen> getSpecimenSNList(SpecimenQuery query) {
		query.setCount(false);
		return specimenService.querySpecimenForPagingList(query).getList();
	}

}
