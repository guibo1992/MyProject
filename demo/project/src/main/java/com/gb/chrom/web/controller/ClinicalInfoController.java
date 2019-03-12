package com.gb.chrom.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.ClinicalInfoService;
import com.gb.chrom.model.ClinicalInfo;

@Controller
@RequestMapping(value="/clinicalinfo")
public class ClinicalInfoController {
	
	
	@Autowired
	private ClinicalInfoService clinicalInfoService;
	
	@ResponseBody
	@RequestMapping(value="/findInfo")
	public List<ClinicalInfo> findInfo(Model model, char typehead) throws Exception {
		List<ClinicalInfo> list=clinicalInfoService.findClinicalInfo(typehead);

		model.addAttribute("list", list);
		
		return list;
	}
	

}
