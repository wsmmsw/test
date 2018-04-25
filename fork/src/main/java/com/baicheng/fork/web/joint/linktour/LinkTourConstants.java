package com.baicheng.fork.web.joint.linktour;

import org.apache.commons.lang.StringUtils;

import com.baicheng.domain.EnvConfig;

public class LinkTourConstants {
	
	public static int SUCCESS_HTTP_CODE=200;
	/** 用户名 */
	public static String USER_NAME;
	/** 密码 */
	public static String PASSWORD;
	/** 域名 */
	public static String HOST;

	// 请求类型
	/** 获取token的登录请求类型 */
	public static final int LOGIN_REQUEST_TYPE = 1000;
	/** 获取token的质疑地址请求类型 */
	public static final int CHALLENGE_REQUEST_TYPE = 1001;
	/** 获取产品列表请求类型 */
	public static final int PRODUCT_LIST_REQUEST_TYPE = 1002;
	/** 获取产品详细信息请求类型 */
	public static final int PRODUCT_REQUEST_TYPE = 1003;
	/** 获取产品sku信息请求类型 */
	public static final int PRODUCT_SKU_REQUEST_TYPE = 1004;
	/** 获取产品库存和价格信息请求类型 */
	public static final int PRODUCT_STOCK_PRICE_REQUEST_TYPE = 1005;
	/** 生成订单的请求类型 */
	public static final int CREATE_ORDER_QUEST_TYPE = 1006;

	static {
		/* 根据环境变量，自动配置项目环境 */
		String env = System.getProperty("spring.profiles.active");
		if (StringUtils.isNotBlank(env)) {
			EnvConfig.ENV_SPRING_PROFILES_ACTIVE = env;
		}
		switch (EnvConfig.ENV_SPRING_PROFILES_ACTIVE) {
		case EnvConfig.V_ENV_SPA_DEV:
			// dev
			USER_NAME = "abc@but.agent";
			PASSWORD = "linktour2018";
			HOST = "http://api.demo.linktour.com";
			break;
		case EnvConfig.V_ENV_SPA_TEST:
			// test
			USER_NAME = "abc@but.agent";
			PASSWORD = "linktour2018";
			HOST = "http://api.demo.linktour.com";
			break;
		case EnvConfig.V_ENV_SPA_PRE:
			// pre
			USER_NAME = "abc@seller.agent";
			PASSWORD = "linktour2018";
			HOST = "http://api.demo.linktour.com";
			break;
		case EnvConfig.V_ENV_SPA_ONLINE:
			// online
			USER_NAME = "";
			PASSWORD = "";
			HOST = "http://api.linktour.com";
			break;
		default:
			// test
			USER_NAME = "abc@but.agent";
			PASSWORD = "linktour2018";
			HOST = "https://api.demo.linktour.com";
			break;
		}
	}
}
