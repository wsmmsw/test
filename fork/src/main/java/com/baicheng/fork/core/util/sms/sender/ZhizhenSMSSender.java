package com.baicheng.fork.core.util.sms.sender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.baicheng.fork.core.util.sms.sender.info.MassSMSInfo;
import com.baicheng.fork.core.util.sms.sender.info.SingleSMSInfo;
import com.nicetrip.freetrip.http.NTRequest;
import com.nicetrip.freetrip.http.NTRequestPost;
import com.nicetrip.freetrip.http.NTResponse;

public class ZhizhenSMSSender implements SmsSender {

	/**
	 * 短信URL
	 */
	public static final String SMS_DOMAIN = "http://115.28.112.245:8082";
	/**
	 * 备用地址
	 */
	public static final String SMS_DOMAIN_SPARE = "http://139.129.199.15:8082";

	/**
	 * 短信群发方法
	 */
	public static final String SMS_MASS_METHOD = "/SendMT/SendMessage";
	public static final String KEY_CONTENT = "Content";
	// 最大支持2000个手机号
	public static final String KEY_MOBILES = "Mobile";
	public static final String KEY_USER_NAME = "UserName";
	public static final String KEY_USER_PASS = "UserPass";
	// 通道号码末尾添加的子扩展号码,不可超过9位
	public static final String KEY_SYS = "Subid";

	/**
	 * 用户名密码固定值
	 */
	public static final String VALUE_USER_NAME = "bclxwyx";
	public static final String VALUE_USER_PASS = "5tgb3EDC_2015!";

	private static final Logger LOGGER = Logger.getLogger(ZhizhenSMSSender.class.getName());

	/**
	 * 单发短信方法
	 * 
	 * @return
	 */
	@Override
	public int smsSingleSend(SingleSMSInfo vo) {
		return SMSConstants.SMS_METHOD_NOT_IMPLIMENT;
	}

	/**
	 * 群发短信方法(给多个手机号码发送同一条信息)
	 */
	@Override
	public int smsMassSend(MassSMSInfo vo) {
		/* 调用至臻短信群发接口 */
		String[] mobiles = vo.getMobiles();
		if (mobiles == null || mobiles.length <= 0) {
			return SMSConstants.SMS_NULL_MOBILE;
		}

		String content = vo.getContent();
		if (content == null || content.equals("")) {
			return SMSConstants.SMS_NULL_CONTENT;
		}

		// Join mobile numbers as a string
		int startIndex = findFirstMobile(mobiles);
		if (startIndex < 0) {
			return SMSConstants.SMS_NULL_MOBILE;
		}

		StringBuffer mobilesSb = new StringBuffer(mobiles[startIndex]);
		for (int i = startIndex + 1; i < mobiles.length; i++) {
			String mobile = mobiles[i];
			if (mobile != null && !mobile.equals("")) {
				mobilesSb.append(",").append(mobile);
			}
		}
		String mobilesStr = mobilesSb.toString();

		// TODO: print info
		String params = KEY_MOBILES + "=" + mobilesStr + "&" + KEY_CONTENT + "=" + content;
		LOGGER.info("smsMassSend()调用接口发送短信: " + params);

		NTRequest request = getRequest(SMS_DOMAIN + SMS_MASS_METHOD, mobilesStr, content);
		String result;
		try {
			NTResponse response = request.execute();
			if (response == null) {
				return SMSConstants.SMS_NULL_RESULT;
			}
			// 状态码，响应ID
			result = response.getEntity();
			if (result == null) {
				return SMSConstants.SMS_NULL_RESULT;
			}
			LOGGER.info("smsMassSend()短信接口返回信息: " + result);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return SMSConstants.SMS_API_CONNECT_ERROR;
		}
		String[] resultArray = result.split(",");
		if (resultArray != null && resultArray.length >= 1) {
			return getCode(resultArray[0]);
		}
		return SMSConstants.SMS_PARSE_RESULT_ERROR;
	}

	private NTRequest getRequest(String url, String mobiles, String content) {
		NTRequest request = new NTRequestPost(url);
		request.addBodyParam(KEY_USER_NAME, VALUE_USER_NAME);
		request.addBodyParam(KEY_USER_PASS, VALUE_USER_PASS);
		request.addBodyParam(KEY_MOBILES, mobiles);
		request.addBodyParam(KEY_CONTENT, content);
		return request;
	}

	private int findFirstMobile(String[] mobiles) {
		for (int i = 0; i < mobiles.length; i++) {
			String mobile = mobiles[i];
			if (mobile != null && !mobile.equals("")) {
				return i;
			}
		}
		return -1;
	}

	private int getCode(String result) {
		if (result == null) {
			LOGGER.error("########## 返回结果是空......");
			return SMSConstants.SMS_SEND_FAILED;
		}
		// 去除空格或换行符
		Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
		Matcher matcher = pattern.matcher(result);
		result = matcher.replaceAll("");
		int i = Integer.parseInt(result);
		switch (i) {
		case 0:
			return SMSConstants.SMS_SEND_OK;
		case 3:
			return SMSConstants.SMS_SEND_OK;
		case 4:
			LOGGER.error("########## 用户名错误......");
			LOGGER.error("########## 用户密码错误......");
			LOGGER.error("########## 用户密码错误......");
			return SMSConstants.SMS_USER_NAME_ERROR;
		case 5:
			LOGGER.error("########## 用户密码错误......");
			LOGGER.error("########## 用户密码错误......");
			LOGGER.error("########## 用户密码错误......");
			return SMSConstants.SMS_PASS_ERROR;
		case 11:
			LOGGER.error("########## 余额不足......");
			LOGGER.error("########## 余额不足......");
			LOGGER.error("########## 余额不足......");
			return SMSConstants.SMS_NO_LIMIT;
		default:
			return SMSConstants.SMS_SEND_FAILED;
		}
	}

	public static void main(String args[]) {
		NTRequest request = new NTRequestPost(SMS_DOMAIN + SMS_MASS_METHOD);
		request.addBodyParam(KEY_USER_NAME, VALUE_USER_NAME);
		request.addBodyParam(KEY_USER_PASS, VALUE_USER_PASS);
		request.addBodyParam(KEY_MOBILES, "18813056517");
		// request.addBodyParam(KEY_SYS, SmsStatusConstants.SMS_SYSTEM);
		request.addBodyParam(KEY_CONTENT, "2017快来用最热烈的方式来挑战青春的极限吧。猛戳 t.cn/R9ArBF9 回TD退订");
		NTResponse response = request.execute();
		String result = response.getEntity();
		String[] resultArray = result.split(",");
		if (resultArray != null && resultArray.length >= 1) {
		}
	}

}
