package com.baicheng.fork.core.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.context.WebApplicationContext;

public class BeanUtils {

	private static BeanUtils instance = null;

	private WebApplicationContext context;
	private Map<String, Object> beans;

	private BeanUtils() {
		beans = new HashMap();
	}

	public static BeanUtils getInstance() {
		if (instance == null) {
			instance = new BeanUtils();
		}
		return instance;
	}

	public void init(WebApplicationContext context) {
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	public <T> T getService(Class<T> type) {
		if (!beans.containsKey(type.getName())) {
			T service = context.getBean(type);
			if (service != null) {
				beans.put(type.getName(), service);
			}
		}
		return (T) beans.get(type.getName());
	}

	public <T> T getComponent(Class<T> type) {
		return context.getBean(type);
	}

}
