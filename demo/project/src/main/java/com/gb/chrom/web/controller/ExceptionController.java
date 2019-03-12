package com.gb.chrom.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gb.chrom.utils.HttpUtils;
import com.gb.chrom.utils.JacksonUtils;
import com.gb.chrom.web.result.JsonResult;

/**
 * @since 1.0
 * @author Summer
 */
@ControllerAdvice
@RequestMapping(value = "/error")
public class ExceptionController {

	@ExceptionHandler(MultipartException.class)
	public @ResponseBody JsonResult handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
		return JsonResult.error().setMessage("文件上传错误");
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String defaultException(HttpServletRequest req, HttpServletResponse response, Exception ex) {
		if (HttpUtils.isAjaxRequest(req)) {
			String json = JacksonUtils.getJsonFromObject(JsonResult.error().setMessage("服务器500错误"));
			try {
				response.setCharacterEncoding("UTF-8");
				response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
				response.getOutputStream().write(json.getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			return null;
		}

		return "view/500";
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public String notFoundPage404(HttpServletRequest req, HttpServletResponse response, Exception ex) {
		if (HttpUtils.isAjaxRequest(req)) {
			String json = JacksonUtils.getJsonFromObject(JsonResult.error().setMessage("404错误"));
			try {
				response.setCharacterEncoding("UTF-8");
				response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
				response.getOutputStream().write(json.getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}

		return "view/404";
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = UnauthorizedException.class)
	public String unauthorizedException(HttpServletRequest req, HttpServletResponse response, Exception e) {
		if (HttpUtils.isAjaxRequest(req)) {
			String json = JacksonUtils.getJsonFromObject(JsonResult.error().setMessage("401错误"));
			try {
				response.setCharacterEncoding("UTF-8");
				response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
				response.getOutputStream().write(json.getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			return null;
		}

		return "view/401";
	}

}
