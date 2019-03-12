package com.gb.chrom.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月19日
 * @since
 */
public class HttpUtils {

	public static boolean isAjaxRequest(HttpServletRequest request) {
		return StringUtils.isNotBlank(request.getHeader("X-Requested-With")) && StringUtils.equalsIgnoreCase("XMLHttpRequest", request.getHeader("X-Requested-With"));
	}

	/**
	 * 获取IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(',');
			if (index != -1)
				return ip.substring(0, index);
			else
				return ip;
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	// api 调用 ( depend jsoup )

	// public static String get(String url) {
	// try {
	// Connection connect = HttpConnection.connect(url).userAgent("Mozilla").ignoreContentType(true).method(Method.GET).timeout(3000);
	// Response response = connect.execute();
	// return response.body();
	// } catch (IOException e) {
	// logger.error("Exception occurred during GET URL [{}], request failed.", url, e);
	// }
	//
	// return null;
	// }
	//
	// public static String post(String url, Map<String, String> params) {
	// try {
	// Connection connect = HttpConnection.connect(url).ignoreContentType(true).method(Method.POST).timeout(3000);
	// if (null != params) {
	// connect.data(params);
	// }
	// Response response = connect.execute();
	// logger.info("Remote callback http status: {}", response.statusCode());
	//
	// return response.body();
	// } catch (IOException e) {
	// logger.error("Exception occurred during POST URL [{}], request failed.", url, e);
	// }
	//
	// return null;
	// }
	//
	// public static String postJson(String url, String json) {
	// try {
	// Connection connect = HttpConnection.connect(url).ignoreContentType(true).method(Method.POST).timeout(3000);
	// connect.requestBody(json).header("Content-Type", "application/json");
	// Response response = connect.execute();
	//
	// logger.info("Remote callback http status: {}", response.statusCode());
	//
	// return response.body();
	// } catch (IOException e) {
	// logger.error("Exception occurred during POST URL [{}], request failed.", url, e);
	// }
	//
	// return null;
	// }

}
