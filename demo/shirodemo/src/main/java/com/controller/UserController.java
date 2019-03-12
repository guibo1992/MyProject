package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.User;
import com.util.MD5Utils;

@Controller
public class UserController {

	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request) {
		System.out.println(user.getUsername()+user.getPassword());
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try {
			subject.login(token);
			Session session = subject.getSession();
			session.setAttribute("user", user);
			session.setAttribute("info", "�ɹ���¼������");
			System.out.println("------------------------");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("user", user);
			request.setAttribute("errorMsg", "�û����������");
			return "index";
		}
	}
	
}
