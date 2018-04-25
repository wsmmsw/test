package com.baicheng.fork.web.constants;

import org.apache.commons.lang.StringUtils;

import com.baicheng.domain.EnvConfig;

/**
 * @author mabaoyu
 *
 * @date：2017年6月8日 下午1:23:39
 */
public class SanMaoYouConstants {

	public static String AGENT_ID;
	public static String SIGN_KEY;
	public static Integer SMS_TYPE;
	public static String HOST;
	public static String VERSION = "1.0";
	public static String CREATE_ORDER = "BatchCreateOrder";
	public static String ORDER_CODE = "BatchGetCode";
	public static String CODE_HOST = "https://smapi.sanmaoyou.com/OtaApi/sm/orders/BatchGetCode";

	static {
		/* 根据环境变量，自动配置项目环境 */
		String env = System.getProperty("spring.profiles.active");
		if (StringUtils.isNotBlank(env)) {
			EnvConfig.ENV_SPRING_PROFILES_ACTIVE = env;
		}
		switch (EnvConfig.ENV_SPRING_PROFILES_ACTIVE) {
		case EnvConfig.V_ENV_SPA_DEV:
			// dev
			AGENT_ID = "139dc0b4081db9d1";
			SIGN_KEY = "150907b5f4dfd8e1";
			HOST = "https://smapi.sanmaoyou.com/OtaApi/sm/orders/batchCreateOrder";
			SMS_TYPE = 2;
			break;
		case EnvConfig.V_ENV_SPA_TEST:
			// test
			AGENT_ID = "139dc0b4081db9d1";
			SIGN_KEY = "150907b5f4dfd8e1";
			HOST = "https://smapi.sanmaoyou.com/OtaApi/sm/orders/batchCreateOrder";
			SMS_TYPE = 2;
			break;
		case EnvConfig.V_ENV_SPA_PRE:
			// pre
			AGENT_ID = "139dc0b4081db9d1";
			SIGN_KEY = "150907b5f4dfd8e1";
			HOST = "https://smapi.sanmaoyou.com/OtaApi/sm/orders/batchCreateOrder";
			SMS_TYPE = 2;
			break;
		case EnvConfig.V_ENV_SPA_ONLINE:
			// online
			AGENT_ID = "139dc0b4081db9d1";
			SIGN_KEY = "150907b5f4dfd8e1";
			HOST = "https://smapi.sanmaoyou.com/OtaApi/sm/orders/batchCreateOrder";
			SMS_TYPE = 1;
			break;
		default:
			// test
			AGENT_ID = "139dc0b4081db9d1";
			SIGN_KEY = "150907b5f4dfd8e1";
			HOST = "https://smapi.sanmaoyou.com/OtaApi/sm/orders/batchCreateOrder";
			SMS_TYPE = 2;
			break;
		}
	}

}
