package com.baicheng.fork.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.baicheng.fork.web.constants.ControllerConst;

/**
 * MVC拦截器.
 */
public class MVCInterceptor implements HandlerInterceptor {

	/**
	 * 预处理逻辑
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			if (session.getAttribute(ControllerConst.SESSION_USER_INFO) == null) {
				response.sendRedirect(ControllerConst.PATH_LOGIN);
				return false;
			}
		} catch (Exception e) {
			response.sendRedirect(ControllerConst.PATH_LOGIN);
			return false;
		}
		return true;
	}

	/**
	 * 后处理逻辑
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
