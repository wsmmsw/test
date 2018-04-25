package com.baicheng.fork.core.util;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.baicheng.fork.core.util.endec.MDUtil;

public class SMSUtils {

	private static String userName = "bjjxjsyxgs";
	private static String passWord = "5LY8jRmn";
	private static String apiUrl = "http://api.app2e.com/smsBigSend.api.php";

	/**
	 * 发送短信
	 * 
	 * @param phoneNum 号码
	 * @param content 内容
	 */
	public static void sendMsg(String phoneNum, String content) {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(apiUrl);
		client.getParams().setContentCharset("GBK");
		method.setRequestHeader("ContentType",
				"application/x-www-form-urlencoded;charset=UTF-8");
		NameValuePair[] data = { // 提交短信
				new NameValuePair("username", userName),
				new NameValuePair("pwd", MDUtil.GetMD5Lower(passWord, 32, "GBK")),
				new NameValuePair("p", phoneNum),
				new NameValuePair("msg", content),
		};
		method.setRequestBody(data);
		try {
			client.executeMethod(method);
			System.out.println(method.getResponseBodyAsString());

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("短信提交成功");
	}

	/**
	 * 验证短信码
	 * 
	 * @param code
	 * @param request
	 * @return
	 */
	public static int codeValidate(String code, HttpServletRequest request) {
		String oldCode = (String) request.getSession().getAttribute("code");
		Date date = (Date) request.getSession().getAttribute("date");
		long seconds = (new Date().getTime() - date.getTime()) / 1000;
		if (code.equals(oldCode) && seconds < 60) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 随机生成4位随机验证码 方法说明
	 * 
	 * @return String
	 */
	public static String createRandomVcode() {
		// 验证码
		String vcode = "";
		for (int i = 0; i < 4; i++) {
			vcode = vcode + (int) (Math.random() * 9);
		}
		return vcode;
	}

	/**
	 * 发送验证码
	 * 
	 * @param phoneNum
	 * @param request
	 */
	public static void sendVerifyCode(String phoneNum, HttpServletRequest request) {
		String code = createRandomVcode();
		String content = new String("【签证服务】您的验证码" + code);
		sendMsg(phoneNum, content);
		request.getSession().setAttribute("date", new Date());
		request.getSession().setAttribute("code", code);
	}

}
