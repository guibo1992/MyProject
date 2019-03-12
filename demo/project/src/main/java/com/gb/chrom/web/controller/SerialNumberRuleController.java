package com.gb.chrom.web.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.SerialNumberRuleService;
import com.gb.chrom.model.SerialNumberRule;
import com.gb.chrom.web.result.JsonResult;

/**
 * Specimen serial rule
 * 
 * @author Summer
 */
@Controller
@RequestMapping(value = "/serial/number")
public class SerialNumberRuleController {

	@Autowired
	private SerialNumberRuleService serialNumberRuleService;

	@RequestMapping(value = "/rule.html")
	public String sysConfig(HttpServletRequest request, Model model) {
		SerialNumberRule rule = serialNumberRuleService.getSerialNumberRule();
		model.addAttribute("rule", null == rule ? new SerialNumberRule() : rule);
		return "view/sn-rule";
	}

	@RequestMapping(value = "/rule/update")
	public @ResponseBody JsonResult updateSysConfig(@Valid SerialNumberRule rule, BindingResult result) {
		if (result.hasErrors()) {
			return JsonResult.error("错误的参数提交!");
		}
		boolean flag = true;
		Optional<SerialNumberRule> optional = Optional.ofNullable(serialNumberRuleService.getSerialNumberRule());
		if (optional.isPresent()) {
			flag = serialNumberRuleService.updateSerialNumberRule(rule);
		} else {
			flag = serialNumberRuleService.setSerialNumberRule(rule);
		}

		return JsonResult.getJsonResult(flag, "操作失败!");
	}

}
