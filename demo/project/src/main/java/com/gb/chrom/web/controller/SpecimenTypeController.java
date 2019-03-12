package com.gb.chrom.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.SpecimenTypeService;
import com.gb.chrom.model.SpecimenType;
import com.gb.chrom.model.query.SpecimenTypeQuery;
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
@RequestMapping(value = "/specimen/type/")
public class SpecimenTypeController {

	@Autowired
	private SpecimenTypeService specimenTypeService;

	@RequestMapping(value = "/list.html")
	public String specimentType() {
		return "view/cyto/specimen-type";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult getSpecimentTypeList(SpecimenTypeQuery query) {
		List<SpecimenType> list = specimenTypeService.querySpecimenTypeForList(query);
		return PaginatorJsonResult.getPaginatorResult(list.size(), list);
	}

	@RequestMapping(value = "/{id}")
	public @ResponseBody JsonResult getSpecimentType(@PathVariable("id") int id) {
		return JsonResult.success(specimenTypeService.querySpecimenType(id));
	}

	@RequestMapping(value = "/add")
	public @ResponseBody JsonResult addSpecimentType(SpecimenType type) {
		return JsonResult.getJsonResult(specimenTypeService.addSpecimenType(type), "添加失败!");
	}

	@RequestMapping(value = "/{id}/update")
	public @ResponseBody JsonResult updateSpecimentType(SpecimenType type) {
		return JsonResult.getJsonResult(specimenTypeService.updateSpecimenType(type), "更新失败!");
	}

	@RequestMapping(value = "/{id}/remove")
	public @ResponseBody JsonResult deleteSpecimentType(@PathVariable("id") int id) {
		return JsonResult.getJsonResult(specimenTypeService.removeSpecimenType(id), "删除失败!");
	}

	@RequestMapping(value = "/{id}/undelete")
	public @ResponseBody JsonResult undeleteSpecimentType(@PathVariable("id") int id) {
		return JsonResult.getJsonResult(specimenTypeService.undeleteSpecimenType(id), "恢复失败!");
	}

}
