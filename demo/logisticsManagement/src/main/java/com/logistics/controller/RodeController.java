package com.logistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logistics.service.RodeService;

@Controller
@RequestMapping("/")
public class RodeController {
	/*
	@Autowired RodeService rodeService;
	
	@RequestMapping(value="showAllRole")
	public String showAllRole(Model model)throws Exception{
		model.addAttribute("roles", rodeService.getAllRole());
		return "changePermission";
	}*/

}
