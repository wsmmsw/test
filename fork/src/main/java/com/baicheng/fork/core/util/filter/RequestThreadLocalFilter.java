package com.baicheng.fork.core.util.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baicheng.fork.core.entity.RequestThreadLocal;

/**
 * 过滤器
 * <p>
 * 为request的当前线程绑定request对象和response对象
 * </p>
 *
 * @author SongPengpeng
 * @date
 */
public class RequestThreadLocalFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		RequestThreadLocal.setRequest((HttpServletRequest) request);
		RequestThreadLocal.setResponse((HttpServletResponse) response);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
