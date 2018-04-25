package com.baicheng.fork.core.util.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.baicheng.fork.core.util.BeanUtils;
import com.baicheng.fork.core.util.email.jdk.SMTPConfigUtil;
import com.baicheng.fork.core.util.email.sendcloud.SendcloudConfigUtil;

/**
 * @author Seamus
 */
public class CustomServletContextListener implements ServletContextListener {

	private static Logger LOGGER = Logger
			.getLogger(CustomServletContextListener.class.getName());

	private static WebApplicationContext webAppContext;
	private static ServletContext servletContext;

	public CustomServletContextListener() {
		super();
	}

	public static ApplicationContext getApplicationContext() {
		return webAppContext;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// Configuration of mail
		SMTPConfigUtil.config();
		SendcloudConfigUtil.config();
		// Hold context
		webAppContext = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());

		servletContext = event.getServletContext();

		// Pre-load service
		BeanUtils.getInstance().init(webAppContext);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// Stop the dubbo service
		ProtocolConfig.destroyAll();
	}

}
