package com.baicheng.fork.core.util.sms.sender;

import org.apache.log4j.Logger;

import com.baicheng.fork.core.util.sms.sender.info.MassSMSInfo;
import com.baicheng.fork.core.util.sms.sender.info.SingleSMSInfo;
import com.nicetrip.freetrip.http.NTRequest;
import com.nicetrip.freetrip.http.NTRequestPost;
import com.nicetrip.freetrip.http.NTResponse;

public class LangyuSMSSender implements SmsSender {

	/**
	 * 短信URL
	 */
	public static final String SMS_DOMAIN = "http://120.26.66.24";

	/**
	 * 短信群发方法
	 */
	public static final String SMS_MASS_METHOD = "/msg/HttpBatchSendSM";
	public static final String KEY_CONTENT = "msg";
	public static final String KEY_MOBILES = "mobile";

	public static final String KEY_USER_NAME = "account";
	public static final String KEY_USER_PASS = "pswd";

	// 可选参数，用户自定义扩展码
	public static final String KEY_SYS = "extno";
	// 是否需要状态报告 true 需要状态报告，false不需要状态报告
	public static final String KEY_STATUS_NEED = "needstatus";
	// 用户订购的产品id，订购多个产品id是必填，否则会发生计费错误
	public static final String KEY_PRODUCT = "product";

	/**
	 * 用户名密码固定值
	 */
	public static final String VALUE_USER_NAME = "bcly_yx";
	public static final String VALUE_USER_PASS = "hbr5#gkGzh";

	public static final String VALUE_STATUS_NEED = "false";
	public static final String VALUE_PRODUCT = "393422693";

	private static final Logger LOGGER = Logger.getLogger(LangyuSMSSender.class.getName());

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

		NTRequest request = new NTRequestPost(SMS_DOMAIN + SMS_MASS_METHOD);
		request.addBodyParam(KEY_USER_NAME, VALUE_USER_NAME);
		request.addBodyParam(KEY_USER_PASS, VALUE_USER_PASS);
		request.addBodyParam(KEY_MOBILES, mobilesStr);
		request.addBodyParam(KEY_CONTENT, content);
		request.addBodyParam(KEY_STATUS_NEED, VALUE_STATUS_NEED);
		// request.addBodyParam(KEY_PRODUCT, VALUE_PRODUCT);

		String result;
		try {
			NTResponse response = request.execute();
			if (response == null) {
				return SMSConstants.SMS_NULL_RESULT;
			}

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

		if (resultArray != null && resultArray.length >= 2) {
			return getCode(resultArray[1]);
		}
		return SMSConstants.SMS_PARSE_RESULT_ERROR;
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
		int i = Integer.parseInt(result);
		switch (i) {
		case 0:
			return SMSConstants.SMS_SEND_OK;
		case 101:
			return SMSConstants.SMS_USER_NAME_ERROR;
		case 102:
			return SMSConstants.SMS_PARAM_ERROR;
		case 109:
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
		request.addBodyParam(KEY_STATUS_NEED, "false");
		// request.addBodyParam(KEY_PRODUCT,"393422693");
		request.addBodyParam(KEY_CONTENT, "2017快来用最热烈的方式来挑战青春的极限吧。猛戳 t.cn/R9ArBF9 回TD退订");
		NTResponse response = request.execute();
		String result = response.getEntity();
		System.out.println(result);
		int i = Integer.parseInt("00");
		System.out.println(i);
	}

}
