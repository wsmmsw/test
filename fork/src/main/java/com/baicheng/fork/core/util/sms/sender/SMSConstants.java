package com.baicheng.fork.core.util.sms.sender;

public class SMSConstants {

	/**
	 * 短信平台编号
	 *
	 */
	// 畅卓短信平台
	public static final long SMS_CHANNEL_CHANGZHUO = 10;
	// 至臻短信平台编码
	public static final long SMS_CHANNEL_ZHIZHEN = 11;
	// 朗宇短信平台编码
	public static final long SMS_CHANNEL_LANGYU = 12;

	/**
	 * 短信发送返回状态码
	 */
	public static final int SMS_SEND_OK = 1;// 发送成功
	public static final int SMS_SEND_FAILED = 0;// 发送失败

	public static final int SMS_NULL_MOBILE = 1000;// 手机号码为空
	public static final int SMS_NULL_CONTENT = 1001;// 内容为空
	public static final int SMS_NULL_KEY_INFO = 1002;
	public static final int SMS_NULL_RESULT = 1003;// 接口返回空

	public static final int SMS_USER_NAME_ERROR = 2000;// 用户名错误
	public static final int SMS_PASS_ERROR = 2001;// 用户密码错误
	public static final int SMS_PARAM_ERROR = 2002;// 参数错误
	public static final int SMS_ENCODE_ERROR = 2003;// 编码错误
	public static final int SMS_IP_ERROR = 2004;// IP地址认证错

	public static final int SMS_CONTENT_TOO_LONG = 3000;// 消息太长
	public static final int SMS_CONTENT_SENSITIVE = 3001;// 敏感消息
	public static final int SMS_CONTENT_NOT_AUDITED = 3002;// 内容没有审核
	public static final int SMS_NO_LIMIT = 3003;// 没有额度

	public static final int SMS_METHOD_NOT_IMPLIMENT = 4000;// 方法没有实现
	public static final int SMS_PARSE_RESULT_ERROR = 4001;// 发送短信接口异常
	public static final int SMS_API_CONNECT_ERROR = 4002; // 连接异常

}
