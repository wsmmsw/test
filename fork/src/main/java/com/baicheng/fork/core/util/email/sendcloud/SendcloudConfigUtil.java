package com.baicheng.fork.core.util.email.sendcloud;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.baicheng.utils.email.sendcloud.config.SendcloudConfig;

/**
 * @author SongPengpeng
 * @date 2017/9/14.
 */
public class SendcloudConfigUtil {

	/**
	 * 读取Sendcloud配置文件
	 */
	static {
		try {
			InputStream is = SendcloudConfig.class.getClassLoader()
					.getResourceAsStream("sendcloud.properties");
			Properties pros = new Properties();
			pros.load(new InputStreamReader(is, "UTF-8"));
			SendcloudConfig.send_api = pros.getProperty("send_api");
			SendcloudConfig.send_template_api = pros.getProperty("send_template_api");
			SendcloudConfig.send_sms_api = pros.getProperty("send_sms_api");
			SendcloudConfig.send_voice_api = pros.getProperty("send_voice_api");
			SendcloudConfig.api_user = pros.getProperty("api_user");
			SendcloudConfig.api_key = pros.getProperty("api_key");
			SendcloudConfig.sms_user = pros.getProperty("sms_user");
			SendcloudConfig.sms_key = pros.getProperty("sms_key");
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void config() {
	}

}
