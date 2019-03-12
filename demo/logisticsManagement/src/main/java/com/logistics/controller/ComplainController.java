package com.logistics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.entity.Complain;
import com.logistics.service.ComplainService;

@Controller
@RequestMapping("/")
public class ComplainController {
	@Autowired
	private ComplainService complainService;
	@ResponseBody
	@RequestMapping("addtousu")
	public int addtousu(Complain complain, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int temp = complainService.insert(complain);
		return temp;

	}

	@RequestMapping("showtousu")
	public String showtousu(Model model) throws Exception {
       
		List<Complain> list = complainService.getallcomplain();
		model.addAttribute("list", list);
		return "投诉查询.jsp";
	}

	@RequestMapping("deletetousu")
	public String deletetousu(String id) throws Exception {
		//Map map = new HashMap();
		int temp = complainService.deleteByPrimaryKey(new Integer(id));
	//	map.put("temp", temp);
		return "showtousu";
	}
}
