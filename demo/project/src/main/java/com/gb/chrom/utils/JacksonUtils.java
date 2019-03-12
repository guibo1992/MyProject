package com.gb.chrom.utils;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年5月2日
 * @since
 */
public class JacksonUtils<K, V> {

	static final ThreadLocal<ObjectMapper> local = new ThreadLocal<>();

	private static final ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
		// 去掉默认的时间戳格式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		// 设置为中国上海时区
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		// objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);

		// 反序列化时，属性不存在的兼容处理
		objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// 序列化时，日期的统一格式
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		// 空值不序列化
		objectMapper.setSerializationInclusion(Include.NON_NULL);

		/* 属性名称允许为非双引号 */
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		/* 允许使用单引号包住属性和值 */
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		/* 允许接受所有引号引起来的字符 */
		objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
		objectMapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static Object getMapper() {
		return local.get();
	}

	/**
	 * 将对象序列化
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJsonFromObject(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 反序列化对象字符串
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T getObjectFromJson(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 反序列化字符串成为对象
	 * 
	 * @param json
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T getObjectFromJson(String json, TypeReference<T> valueTypeRef) {
		try {
			return objectMapper.readValue(json, valueTypeRef);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 反序列化字符串成为对象
	 * 
	 * @param json
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T getObjectFromJson(String json, JavaType type) {
		try {
			return objectMapper.readValue(json, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String mapSerializer(Map<?, ?> map) {
		if (MapUtils.isEmpty(map)) {
			return null;
		}

		return getJsonFromObject(map);
	}

	public static <T> T deserializer(String jsonString, JavaType type) {
		if (StringUtils.isBlank(jsonString)) {
			return null;
		}

		return getObjectFromJson(jsonString, type);
	}
	
	public static JavaType constructType(Class<?> clazz, Class<?>... paramClasses) {
		return TypeFactory.defaultInstance().constructParametricType(clazz, paramClasses);
	}
	
	// public static void main(String[] args) {
	// String json = "{\"specialTypeId\":\"办公建筑\",\"otherSpecialTypeId\":\"谁知道是什么\",\"name\":\"办公建筑类型\"}";
	//
	// Map<String, String> map = deserializer(json, constructType(Map.class, String.class, String.class));
	// System.out.println(map);
	// }

}