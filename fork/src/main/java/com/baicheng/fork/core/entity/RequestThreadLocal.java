package com.baicheng.fork.core.entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author SongPengpeng
 * @date 2017/5/11.
 */
public class RequestThreadLocal {

	private static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<>();

	public static HttpServletRequest getRequest() {
		return requestThreadLocal.get();
	}

	public static void setRequest(HttpServletRequest request) {
		requestThreadLocal.set(request);
	}

	public static HttpServletResponse getResponse() {
		return responseThreadLocal.get();
	}

	public static void setResponse(HttpServletResponse response) {
		responseThreadLocal.set(response);
	}

	public static HttpSession getSession() {
		return requestThreadLocal.get().getSession();
	}
}
