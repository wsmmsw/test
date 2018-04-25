package com.baicheng.fork.web.constants;

import org.apache.commons.lang.StringUtils;

import com.baicheng.domain.EnvConfig;

/**
 * @author mabaoyu
 *
 * @date：2017年6月8日 下午1:23:39
 */
public class SCRMConstants {

	public static String APP_KEY;
	public static String APP_SCRET;
	public static String SESSION_KEY;
	public static String HOST;
	public static String METHOD;
	public static String GC_SOURCE;
	public static String FORMAT = "json";
	public static String VERSION = "1.0";
	public static String SIGN_METHOD = "md5";

	static {
		/* 根据环境变量，自动配置项目环境 */
		String env = System.getProperty("spring.profiles.active");
		if (StringUtils.isNotBlank(env)) {
			EnvConfig.ENV_SPRING_PROFILES_ACTIVE = env;
		}
		switch (EnvConfig.ENV_SPRING_PROFILES_ACTIVE) {
		case EnvConfig.V_ENV_SPA_DEV:
			// dev
			APP_KEY = "0b15826f-0562-46d2-9fca-daf4ca242029";
			APP_SCRET = "8b9dc030-786b-490f-a769-85532f2ab1f5";
			SESSION_KEY = "df56dd7b-2bb1-400b-b794-b46e9cca52eb";
			HOST = "http://test2.edbkelai.com/router";
			METHOD = "kl.gc.data";
			GC_SOURCE = "gc8001";
			break;
		case EnvConfig.V_ENV_SPA_TEST:
			// test
			APP_KEY = "0b15826f-0562-46d2-9fca-daf4ca242029";
			APP_SCRET = "8b9dc030-786b-490f-a769-85532f2ab1f5";
			SESSION_KEY = "df56dd7b-2bb1-400b-b794-b46e9cca52eb";
			HOST = "http://test2.edbkelai.com/router";
			METHOD = "kl.gc.data";
			GC_SOURCE = "gc8001";
			break;
		case EnvConfig.V_ENV_SPA_PRE:
			// pre
			APP_KEY = "0b15826f-0562-46d2-9fca-daf4ca242029";
			APP_SCRET = "8b9dc030-786b-490f-a769-85532f2ab1f5";
			SESSION_KEY = "df56dd7b-2bb1-400b-b794-b46e9cca52eb";
			HOST = "http://test2.edbkelai.com/router";
			METHOD = "kl.gc.data";
			GC_SOURCE = "gc8001";
			break;
		case EnvConfig.V_ENV_SPA_ONLINE:
			// online
			APP_KEY = "0b15826f-0562-46d2-9fca-daf4ca242029";
			APP_SCRET = "8b9dc030-786b-490f-a769-85532f2ab1f5";
			SESSION_KEY = "df56dd7b-2bb1-400b-b794-b46e9cca52eb";
			HOST = "http://open.edbkelai.com/router";
			METHOD = "kl.gc.data";
			GC_SOURCE = "gc8001";
			break;
		default:
			// test
			APP_KEY = "0b15826f-0562-46d2-9fca-daf4ca242029";
			APP_SCRET = "8b9dc030-786b-490f-a769-85532f2ab1f5";
			SESSION_KEY = "df56dd7b-2bb1-400b-b794-b46e9cca52eb";
			HOST = "http://test2.edbkelai.com/router";
			METHOD = "kl.gc.data";
			GC_SOURCE = "gc8001";
			break;
		}
	}

}
