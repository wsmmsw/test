package com.baicheng.fork.core.util.sms.sender;

import org.apache.log4j.Logger;

import com.baicheng.fork.core.util.XmlUtil;
import com.baicheng.fork.core.util.sms.sender.info.MassSMSInfo;
import com.baicheng.fork.core.util.sms.sender.info.SingleSMSInfo;
import com.nicetrip.freetrip.http.NTRequest;
import com.nicetrip.freetrip.http.NTRequestPost;
import com.nicetrip.freetrip.http.NTResponse;

public class ChangzhuoSMSSender implements SmsSender {

	public static final String SMS_DOMAIN = "http://service.baicheng.com";
	// public static final String SMS_DOMAIN = "http://service.test.baicheng.com";
	/**
	 * 短信群发方法
	 */
	public static final String SMS_MASS_METHOD = "/ShortMsgService.asmx/SendMessageByChanzorMobiles";
	public static final String KEY_MOBILES = "mobiles";
	public static final String KEY_CONTENT = "ShortMsg";
	public static final String KEY_SYS = "sysType";

	private static final Logger LOGGER = Logger
			.getLogger(ChangzhuoSMSSender.class.getName());

	@Override
	public int smsSingleSend(SingleSMSInfo vo) {
		// TODO
		return SMSConstants.SMS_METHOD_NOT_IMPLIMENT;
	}

	@Override
	public int smsMassSend(MassSMSInfo vo) {
		/* 调用.net WebService接口群发短信 */
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

		String strMobiles = mobiles[startIndex];
		for (int i = startIndex + 1; i < mobiles.length; i++) {
			String mobile = mobiles[i];
			if (mobile != null && !mobile.equals("")) {
				strMobiles += "," + mobiles[i];
			}
		}

		// TODO: print info
		String params = KEY_MOBILES + "=" + strMobiles + "&" + KEY_CONTENT + "=" + content
				+ "&" + KEY_SYS + "=" + SMSConstants.SMS_CHANNEL_CHANGZHUO;
		LOGGER.info("smsMassSend()调用接口发送短信: " + params);

		// Send POST request
		String result;
		try {
			NTRequest request = new NTRequestPost(SMS_DOMAIN + SMS_MASS_METHOD);
			request.addBodyParam(KEY_MOBILES, strMobiles);
			request.addBodyParam(KEY_CONTENT, content);
			request.addBodyParam(KEY_SYS, SMSConstants.SMS_CHANNEL_CHANGZHUO);
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

		String strCode = XmlUtil.xml2Bean(result, String.class);
		if (strCode != null) {
			Integer code = Integer.parseInt(strCode);
			if (code != null) {
				return getCode(code);
			}
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

	private int getCode(int code) {
		switch (code) {
		case 0:
			return SMSConstants.SMS_SEND_OK;
		case -1:
			return SMSConstants.SMS_SEND_FAILED;
		default:
			return SMSConstants.SMS_SEND_FAILED;
		}
	}

	public static void main(String[] args) {
		NTRequest request = new NTRequestPost(SMS_DOMAIN + SMS_MASS_METHOD);
		request.addBodyParam(KEY_MOBILES, "18813056517");
		request.addBodyParam(KEY_CONTENT, "测试数据");
		request.addBodyParam(KEY_SYS, SMSConstants.SMS_CHANNEL_CHANGZHUO);
		NTResponse response = request.execute();
		System.out.println(response.getEntity());
	}
}
