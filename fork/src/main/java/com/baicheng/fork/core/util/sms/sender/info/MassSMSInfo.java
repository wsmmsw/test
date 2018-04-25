package com.baicheng.fork.core.util.sms.sender.info;

/**
 * 群发短信参数类
 */
public class MassSMSInfo extends BaseSMSInfo {
	// 手机号码
	private String[] mobiles;
	private String content;

	public String[] getMobiles() {
		return mobiles;
	}

	public void setMobiles(String[] mobiles) {
		this.mobiles = mobiles;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
