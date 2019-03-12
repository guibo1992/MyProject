package com.gb.chrom.web.result;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

/**
 * @author Summer
 *
 *         2016年5月6日
 */
public class JsonResult {

	private static final String DEF_OPERATION_FAIL_MSG = "操作失败!";

	/** callback result, true or false */
	private boolean success = false;

	/** callback message */
	private String message = DEF_OPERATION_FAIL_MSG;

	/** callback data */
	private Object data;

	/** callback extra datas */
	private Map<String, Object> datas = new HashMap<>();

	private JsonResult(boolean success) {
		this.success = success;
	}

	private JsonResult(boolean success, String message) {
		this(success);
		this.message = message;
	}

	/**
	 * @return the {@link #success}
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the {@link #success} to set
	 */
	public JsonResult setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	/**
	 * @return the {@link #datas}
	 */
	public Map<String, Object> getDatas() {
		return datas;
	}

	/**
	 * @param datas the {@link #datas} to set
	 */
	public JsonResult setDatas(Map<String, Object> datas) {
		this.datas = datas;
		return this;
	}

	/**
	 * @param key
	 * @param value
	 */
	public JsonResult put(String key, Object value) {
		if (MapUtils.isEmpty(datas)) {
			datas = new HashMap<>();
		}
		datas.put(key, value);

		return this;
	}

	/**
	 * @return the {@link #data}
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the {@link #data} to set
	 */
	public JsonResult setData(Object data) {
		this.data = data;
		return this;
	}

	/**
	 * @return the {@link #message}
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the {@link #message} to set
	 */
	public JsonResult setMessage(String message) {
		this.message = message;
		return this;
	}

	public static JsonResult success() {
		return new JsonResult(true);
	}

	public static JsonResult error() {
		return new JsonResult(false);
	}

	public static JsonResult success(Object object) {
		return success().setData(object);
	}

	public static JsonResult error(String errorMessage) {
		return error().setMessage(errorMessage);
	}

	public static JsonResult getJsonResult(boolean isSuccess) {
		return isSuccess ? success() : error();
	}

	public static JsonResult getJsonResult(Object data) {
		return null != data ? success().setData(data) : error();
	}

	public static JsonResult getJsonResult(boolean isSuccess, String defaulMessage) {
		return isSuccess ? success(defaulMessage) : error(defaulMessage);
	}

	public static JsonResult getJsonResult(Object data, String defaulMessage) {
		return null != data ? success(defaulMessage).setData(data) : error(defaulMessage);
	}

}
