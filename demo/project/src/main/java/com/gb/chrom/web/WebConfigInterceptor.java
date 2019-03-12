package com.gb.chrom.web;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Summer
 *
 *         2018年8月22日
 */
@Component
public class WebConfigInterceptor extends HandlerInterceptorAdapter {

	static final String _SYSTEM_CONFIG_ID = "_sysConfig";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();

		if (subject.isAuthenticated()) {
			if (!Optional.ofNullable(session.getAttribute(_SYSTEM_CONFIG_ID)).isPresent()) {
				response.sendRedirect("/config/setting.html");
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}

}
