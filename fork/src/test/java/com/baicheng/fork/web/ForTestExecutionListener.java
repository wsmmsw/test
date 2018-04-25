package com.baicheng.fork.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.web.context.WebApplicationContext;

import com.baicheng.fork.core.util.listener.CustomServletContextListener;

/**
 * @author SongPengpeng
 * @date 2017/12/26.
 */
public class ForTestExecutionListener extends ServletTestExecutionListener {

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		ApplicationContext context = testContext.getApplicationContext();
		if (context instanceof WebApplicationContext) {
			WebApplicationContext wac = (WebApplicationContext) context;
			ServletContext servletContext = wac.getServletContext();
			new CustomServletContextListener().contextInitialized(
					new ServletContextEvent(servletContext));
		}
	}

	@Override
	public void afterTestClass(TestContext testContext) throws Exception {
		ApplicationContext context = testContext.getApplicationContext();
		if (context instanceof WebApplicationContext) {
			WebApplicationContext wac = (WebApplicationContext) context;
			ServletContext servletContext = wac.getServletContext();
			new CustomServletContextListener().contextDestroyed(
					new ServletContextEvent(servletContext));
		}
	}

}
