package com.baicheng.fork.core.util;

import java.lang.reflect.Type;

import org.apache.log4j.Logger;

import com.google.gson.*;

public class JsonUtil {

	public static final String DATE_FMT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FMT_DAY = "yyyy-MM-dd";
	public static final String DATE_FMT_MONTH = "yyyy-MM";

	public static final Logger LOGGER = Logger.getLogger(JsonUtil.class.getName());

	/**
	 *
	 * @param bean
	 * @return
	 */
	public static String bean2json(Object bean) {
		Gson gson = new GsonBuilder().setDateFormat(DATE_FMT).create();
		return gson.toJson(bean);
	}

	/**
	 *
	 * @param bean
	 * @return
	 */
	public static String bean2jsonAnnotation(Object bean) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(DATE_FMT).create();
		return gson.toJson(bean);
	}

	/**
	 *
	 * @param bean
	 * @param isEscap
	 * @return
	 */
	public static String bean2json(Object bean, boolean isEscap) {
		Gson gson = null;
		if (!isEscap) {
			gson = new GsonBuilder().disableHtmlEscaping().setDateFormat(
					DATE_FMT).create();
		} else {
			gson = new GsonBuilder().setDateFormat(DATE_FMT).create();
		}
		return gson.toJson(bean);
	}

	/**
	 *
	 * @param <T>
	 * @param json
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T json2bean(String json, Type type) {
		Gson gson = new GsonBuilder().setDateFormat(DATE_FMT).create();
		return (T) gson.fromJson(json, type);
	}

	/**
	 *
	 * @param <T>
	 * @param json
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T json2beanDay(String json, Type type) {
		Gson gson = new GsonBuilder().setDateFormat(DATE_FMT_DAY).create();
		return (T) gson.fromJson(json, type);
	}

	@SuppressWarnings("unchecked")
	public static <T> T json2beanMonth(String json, Type type) {
		Gson gson = new GsonBuilder().setDateFormat(DATE_FMT_MONTH).create();
		return (T) gson.fromJson(json, type);
	}

	/**
	 *
	 * @param jsonText
	 * @param jsonKey
	 * @return
	 */
	public static String getJsonValue(String jsonText, String jsonKey) {
		JsonElement jsonElement = new JsonParser().parse(jsonText);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		return jsonObject.get(jsonKey).getAsString();
	}

	/**
	 * determine whether a string is a json.
	 *
	 * @param jsonStr
	 *
	 * @return
	 */
	public static boolean isJson(String jsonStr) {
		boolean flag = false;
		try {
			if (jsonStr != null && !jsonStr.isEmpty()) {
				jsonStr = jsonStr.trim();
				final char[] chars = jsonStr.toCharArray();
				char first = chars[0];
				char last = chars[chars.length - 1];
				if ((first == '{' && last == '}') || (first == '[' && last == ']')) {
					flag = true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return flag;
	}

	/**
	 * if return true,must be a jsonformat array,else it is unknown, so shoud to
	 * first determine whether a objdec is jsonformat String and then invoke this
	 * method.
	 *
	 * @param jsonStr
	 *
	 * @return
	 */
	public static boolean isArray(String jsonStr) {
		boolean flag = false;
		try {
			if (isJson(jsonStr)) {
				jsonStr = jsonStr.trim();
				final char[] chars = jsonStr.toCharArray();
				char first = chars[0];
				char last = chars[chars.length - 1];
				if (first == '[' && last == ']') {
					flag = true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return flag;
	}

}
