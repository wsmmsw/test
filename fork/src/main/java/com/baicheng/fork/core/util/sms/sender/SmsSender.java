package com.baicheng.fork.core.util.sms.sender;

import com.baicheng.fork.core.util.sms.sender.info.MassSMSInfo;
import com.baicheng.fork.core.util.sms.sender.info.SingleSMSInfo;

/**
 * 调用.net接口发送短信类
 */
public interface SmsSender {

	/**
	 * 单发短信方法
	 * 
	 * @return
	 */
	int smsSingleSend(SingleSMSInfo vo);

	/**
	 * 群发短信方法(给多个手机号码发送同一条信息)
	 */
	int smsMassSend(MassSMSInfo vo);

}
