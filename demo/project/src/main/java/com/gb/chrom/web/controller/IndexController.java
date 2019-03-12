package com.gb.chrom.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @since 1.0
 * @author Summer
 */
@Controller
public class IndexController {


	@RequestMapping(value = {"/", "/index.html"})
	public String homepage(HttpServletRequest request, HttpServletResponse response) {
//		Optional<Configuration> optional = Optional.ofNullable(sysconfigService.getSysconfig());
//		if (optional.isPresent()) {
//			return "view/dashboard";
//		}
		//return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/sys/config.html";
		return "view/dashboard";
	}
	
	@RequestMapping("/decorator.dec")
	public String decorater(HttpServletRequest request, Model model) {
		return "layout/decorator";
	}
	
	@RequestMapping(value = "/setting.html")
	public String settingpage(HttpServletRequest request, HttpServletResponse response) {
		return "view/settings";
	}
	
	@RequestMapping(value = "/technical/support.html")
	public String technicalSupport(HttpServletRequest request) {
		return "view/support";
	}
}
