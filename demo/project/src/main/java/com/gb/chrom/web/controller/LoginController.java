package com.gb.chrom.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Summer
 *
 * @date 2016年3月5日
 */
@Controller
public class LoginController implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login.html")
	public String login(HttpServletRequest request, Model model) {
		String shiroLoginFailure = (String) request.getAttribute("shiroLoginFailure");
		
		if (StringUtils.isNotBlank(shiroLoginFailure)) {
			logger.error("user login failed :", shiroLoginFailure);

			if (StringUtils.equals(shiroLoginFailure, UnknownAccountException.class.getName())) {
				model.addAttribute("errorMsg", "*用户名不存在");
			} else if (StringUtils.equals(shiroLoginFailure, LockedAccountException.class.getName())) {
				model.addAttribute("errorMsg", "*用户无权登录");
			} else if (StringUtils.equals(shiroLoginFailure, IncorrectCredentialsException.class.getName())) {								
				
				model.addAttribute("errorMsg", "*密码错误");
			} else if (StringUtils.equals(shiroLoginFailure, ExcessiveAttemptsException.class.getName())) {
				model.addAttribute("errorMsg", "*频繁的错误登录，锁定10分钟！");
			}
		}

		return "view/login";
	}

}
