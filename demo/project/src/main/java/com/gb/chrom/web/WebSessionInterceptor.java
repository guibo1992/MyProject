package com.gb.chrom.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gb.chrom.config.cache.UnreadNoticeCache;
import com.gb.chrom.core.service.ConfigurationService;
import com.gb.chrom.core.service.UserService;
import com.gb.chrom.model.User;

/**
 * User session interceptor
 * 
 * @author Summer
 *
 */
@Component
public class WebSessionInterceptor extends HandlerInterceptorAdapter {

	static final String _ACTIVE_MENU_JSESSIONID = "_active_menu";
	static final String _STATIC_FILE_DOMAIN_JSESSIONID = "_static";
	static final String _SYSTEM_CONFIG_ID = "_sysConfig";

	@Value("${static.resources.path}")
	private String staticServer;

	@Autowired
	private UserService userService;
	@Autowired
	private ConfigurationService configurationService;
	@Autowired
	private UnreadNoticeCache unreadNoticeCache;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Subject subject = SecurityUtils.getSubject();
//		Session session = subject.getSession();
		
		HttpSession session = request.getSession();

		// static file domain
		if (StringUtils.isBlank((CharSequence) session.getAttribute(_STATIC_FILE_DOMAIN_JSESSIONID))) {
			session.setAttribute(_STATIC_FILE_DOMAIN_JSESSIONID, staticServer);
		}

		if (subject.isAuthenticated()) {
			session.setAttribute(_ACTIVE_MENU_JSESSIONID, request.getRequestURI());

			// current user's information
			User user = (User) session.getAttribute(User._ACTIVE_USER_JSESSIONID);
			if (null == user) {
				user = userService.queryUser((String) subject.getPrincipal());
				session.setAttribute(User._ACTIVE_USER_JSESSIONID, user);
			}

			unreadNoticeCache.setUnreadNotices((String) subject.getPrincipal(), request);
		}
		session.setAttribute(_SYSTEM_CONFIG_ID, configurationService.getConfiguration());

		return super.preHandle(request, response, handler);
	}

}