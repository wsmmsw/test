package com.baicheng.fork.domain;

import com.baicheng.domain.EnvConfig;

public class BackConstants {

	/**
	 * 短信平台编号
	 */
	public static final String SMS_SYSTEM = "10";
	/**
	 * SMS result code
	 */
	public static final int SMS_SEND_OK = 1;
	public static final int SMS_SEND_FAILED = 0;
	public static final int SMS_NULL_QUERY_CONDITION = -1;
	public static final int SMS_NULL_RECORD_INFO = -2;
	public static final int SMS_NULL_MOBILE = -3;
	public static final int SMS_QUERY_CONDITION_FORMAT_ERROR = -4;
	public static final int SMS_RECORD_INFO_FORMAT_ERROR = -5;
	public static final int SMS_QUERY_CONDITION_ERROR = -6;
	public static final int SMS_NULL_KEY_INFO = -7;
	public static final int SMS_NULL_CONFIG_LIST = -8;
	public static final int SMS_NODE_NOT_EXIST = -9;
	public static final int SMS_NODE_TOO_MUCH_ERROR_DATA = -10;
	public static final int SMS_NULL_NODE_CONTENT = -11;
	public static final int SMS_PARAM_COUNT_ERROR = -12;
	public static final int SMS_NULL_CONTENT = -100;
	public static final int SMS_PARSE_RESULT_ERROR = -101;
	public static final int SMS_API_CONNECT_ERROR = -102;
	public static final int SMS_METHOD_NOT_IMPLIMENT = -103;
	public static final int SMS_SENDER_ERROR = -104;

	/**
	 * Encode Type.
	 */
	public static final String ENCODE_TYPE_UTF_8 = "UTF-8";
	public static final String ENCODE_TYPE_GB_2312 = "GB2312";
	public static final String ENCODE_TYPE_GBK = "GBK";

	/**
	 * BROWSER INFO.
	 */
	public static final String BROWSER_MOZILLA_5_0 = "Mozilla/5.0";
	/**
	 * HTTP METHOD.
	 */
	public static final String HTTP_METHOD_GET = "GET";
	public static final String HTTP_METHOD_POST = "POST";
	public static final String HTTP_METHOD_PUT = "PUT";
	/**
	 *
	 */
	public static final String HTTP_HEAD_USER_AGENT = "User-Agent";
	public static final String HTTP_HEAD_CHAR_SET = "Charset";
	public static final String HTTP_HEAD_CONTENT_TYPE = "Content-Type";
	public static final String HTTP_HEAD_ACCEPT = "Accept";

	// 测试环境下的测试手机号码
	public static final String[] TEST_MOBILES = { "18813056517" };
	public static final String[] TEST_EMAILS = { "1074883081@qq.com" };

	public static final long TEST_UID = 1601711440;

	/**
	 * 根据环境进行切换.
	 */
	public static boolean IS_DEBUG = true;// 是否发送短信或者邮件
	public static String UPLOAD_DOMAIN_URL;
	public static String BAICHENG_DOMAIN_URL;

	static {
		/* 根据环境变量，自动配置项目环境 */
		String env = System.getProperty("spring.profiles.active");
		if (env != null && !env.isEmpty()) {
			EnvConfig.ENV_SPRING_PROFILES_ACTIVE = env;
		}
		switch (EnvConfig.ENV_SPRING_PROFILES_ACTIVE) {
		case EnvConfig.V_ENV_SPA_DEV:
			// dev
			IS_DEBUG = true;
			UPLOAD_DOMAIN_URL = "http://brs.test.baicheng.com";
			BAICHENG_DOMAIN_URL = "http://www.test.baicheng.com";
			break;
		case EnvConfig.V_ENV_SPA_TEST:
			// test
			IS_DEBUG = true;
			UPLOAD_DOMAIN_URL = "http://brs.test.baicheng.com";
			BAICHENG_DOMAIN_URL = "http://www.test.baicheng.com";
			break;
		case EnvConfig.V_ENV_SPA_PRE:
			// pre
			IS_DEBUG = true;
			UPLOAD_DOMAIN_URL = "http://brs.test.baicheng.com";
			BAICHENG_DOMAIN_URL = "http://www.test.baicheng.com";
			break;
		case EnvConfig.V_ENV_SPA_ONLINE:
			// online
			IS_DEBUG = false;
			UPLOAD_DOMAIN_URL = "http://brs.baicheng.com";
			BAICHENG_DOMAIN_URL = "http://www.baicheng.com";
			break;
		default:
			// test
			IS_DEBUG = true;
			UPLOAD_DOMAIN_URL = "http://brs.test.baicheng.com";
			BAICHENG_DOMAIN_URL = "http://www.test.baicheng.com";
			break;
		}
	}

}
