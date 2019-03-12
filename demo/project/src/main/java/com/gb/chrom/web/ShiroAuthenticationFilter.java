package com.gb.chrom.web;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.alibaba.druid.support.json.JSONUtils;
import com.gb.chrom.utils.HttpUtils;
import com.gb.chrom.web.result.JsonResult;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月19日
 * @since
 */
public class ShiroAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		HttpServletResponse httpResponse = WebUtils.toHttp(response);

		if (HttpUtils.isAjaxRequest(httpRequest)) {
			httpResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			httpResponse.getWriter().write(buildUnauthorizedJson());
			return false;
		}

		return super.onAccessDenied(request, response);
	}

	private String buildUnauthorizedJson() {
		return JSONUtils.toJSONString(JsonResult.error("登录超时,请重新登录!"));
	}

}
