package com.baicheng.fork.core.util.email.jdk;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.baicheng.utils.email.jdk.SMTPConfig;
import com.baicheng.utils.email.jdk.SMTPConst;
import com.baicheng.utils.email.sendcloud.config.SendcloudConfig;

/**
 * @author SongPengpeng
 * @date 2017/9/14.
 */
public class SMTPConfigUtil {

	/**
	 * 读取SMTP配置文件
	 */
	static {
		try {
			InputStream is = SendcloudConfig.class.getClassLoader()
					.getResourceAsStream("email.properties");
			Properties pros = new Properties();
			pros.load(new InputStreamReader(is, "UTF-8"));
			SMTPConfig.serverHost = pros.getProperty(SMTPConst.EMAIL_SERVER_HOST);
			SMTPConfig.userName = pros.getProperty(SMTPConst.EMAIL_USER_NAME);
			SMTPConfig.userPwd = pros.getProperty(SMTPConst.EMAIL_PASSWORD);
			// String senderAddress = loader.getProperty(EmailConsts.EMAIL_ADDRESS);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void config() {
	}

}
