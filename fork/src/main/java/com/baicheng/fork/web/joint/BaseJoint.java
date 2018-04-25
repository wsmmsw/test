package com.baicheng.fork.web.joint;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * @author mabaoyu
 * @date 2017年7月21日 上午11:00:00
 * @description 推送数据基类
 */

public abstract class BaseJoint {

	private final int TIME_OUT = 30000;
	private final String CHARSET = "UTF-8";
	private final String CONTENT_TYPE = "application/json";

	protected PostMethod createHttpMethod(String content, String url) throws UnsupportedEncodingException {
		PostMethod method = null;
		method = new PostMethod(url);
		RequestEntity se = new StringRequestEntity(content, this.CONTENT_TYPE, this.CHARSET);
		method.setRequestEntity(se);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		method.getParams().setSoTimeout(this.TIME_OUT);
		return method;
	}

}
