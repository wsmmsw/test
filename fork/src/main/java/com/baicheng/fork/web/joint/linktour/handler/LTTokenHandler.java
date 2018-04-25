package com.baicheng.fork.web.joint.linktour.handler;

import org.apache.log4j.Logger;

import com.baicheng.fork.core.util.BeanUtils;
import com.baicheng.fork.domain.joint.linktour.LTUser;
import com.baicheng.fork.web.joint.linktour.LinkTourConstants;
import com.baicheng.fork.web.joint.linktour.SignatureUtil;
import com.baicheng.utils.JsonUtil;
import com.nicetrip.freetrip.http.NTRequest;
import com.nicetrip.freetrip.http.NTRequestGet;
import com.nicetrip.freetrip.http.NTResponse;
import com.nicetrip.redis.client.CacheClientShard;

/**
 * 获取授权信息 token( 1:先由客户端向服务器发出一个验证请求 2:服务器接到此请求后生成一个随机数并通过网络传输给客户端
 * 3:客户端将收到的随机数提供给对称加密函数，由加密函数使用该随机数对客户的密码进行HMAC-SHA1运算并得到一个结果作为认证证据传给服务器
 * 4:同时，服务器也使用该随机数与存储在服务器数据库中的该用户的相关信息进行HMAC-SHA1运算，
 * 如果服务器的运算结果与客户端传回的响应结果相同，则认为客户端是一个合法用户
 * 5:服务器判断用户为合法用户后，返回一个Token字符串，在随后的请求中，客户端在请求的HTTP Header中附加该值即可
 * 
 * @author wsm 2018年4月18日下午6:22:06
 */
public class LTTokenHandler {

	private static final Logger LOGGER = Logger.getLogger(LTTokenHandler.class.getName());

	private static final String KEY_USERNAME = "username";
	private static final String KEY_SIGNATURE = "signature";

	// hashPBKDF2 加密
	private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
	
	/**领拓token redis key*/
	public static final String LINK_TOUR_TOKEN_KEY = "_LINK_TOUR_TOKEN_KEY_";

	/**领拓token redis 缓存时间*/
	public static final int LINK_TOUR_TOKEN_TIME = 3000;

	private CacheClientShard cacheClientShard;

	public LTTokenHandler() {
		this.cacheClientShard = BeanUtils.getInstance().getService(CacheClientShard.class);
	}

	public LTUser getLTUser() {
		LTUser ltUser = null;
		try {

			// 步骤一：从缓存中获取
			String ltUserCache = cacheClientShard.get(LINK_TOUR_TOKEN_KEY);
			if (ltUserCache != null) {
				return JsonUtil.json2bean(ltUserCache, LTUser.class);
			}

			// 步骤二：缓存中没有，调用接口获取，三次重试
			int retry = 0;
			while (retry < 3) {
				ltUser = getLTUserFromAPI();
				if (ltUser == null) {
					LOGGER.error("########### 第 " + retry + " 次调用领拓接口获取token ，返回结果是空");
					retry++;
					continue;
				}
				break;
			}
			
			if(ltUser == null){
				LOGGER.error("########### 3次重试之后也没有获取到token");	
				return null;
			}
			// 步骤三：接口获取的数据缓存，缓存的周期是50分钟
			String jsonLtUser = JsonUtil.bean2json(ltUser);
			cacheClientShard.set(LINK_TOUR_TOKEN_KEY, jsonLtUser, LINK_TOUR_TOKEN_TIME);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
		return ltUser;

	}

	/**
	 * 调用Api接口获取token信息
	 * 
	 * @return
	 */
	private static LTUser getLTUserFromAPI() {
		LTUser ltUser = null;
		try {
			// 1：准备数据，加密的参数
			String data = SignatureUtil.hashPBKDF2(ALGORITHM, LinkTourConstants.PASSWORD, LinkTourConstants.PASSWORD,
					1024, 32);
			
			// 2:调用质疑地址获取key
			String challengeUrl = LTUrlHandler.getBaseUrl(LinkTourConstants.CHALLENGE_REQUEST_TYPE, -1);
			NTRequest request = new NTRequestGet(challengeUrl);

			request.addUrlParam(KEY_USERNAME, LinkTourConstants.USER_NAME);
			// 接口返回数据
			NTResponse response = request.execute();
			if (response == null) {
				LOGGER.error("########## 调用领拓质疑接口，response 为空");
				return null;
			}

			if (response.getHttpCode() != LinkTourConstants.SUCCESS_HTTP_CODE) {
				LOGGER.error("########## 调用领拓质疑接口失败，response code " + response.getHttpCode() + " 错误信息: "
						+ response.getEntity());
				return null;
			}
			String key = response.getEntity();
			if (key == null) {
				LOGGER.error("########## 调用领拓质疑接口，response entity 为空");
				return null;
			}

			// 3:质疑地址调用成功，加密获取signature（签名），之后调用登录地址
			// 加密
			String signature = SignatureUtil.hash_hmac(data, key);

			// 调用登录接口获取token
			String loginUrl = LTUrlHandler.getBaseUrl(LinkTourConstants.LOGIN_REQUEST_TYPE, -1);
			request = new NTRequestGet(loginUrl);
			request.addUrlParam(KEY_USERNAME, LinkTourConstants.USER_NAME);
			request.addUrlParam(KEY_SIGNATURE, signature);
			response = request.execute();

			if (response == null) {
				LOGGER.error("########## 调用领拓登录接口，response 为空");
				return null;
			}

			if (response.getHttpCode() != LinkTourConstants.SUCCESS_HTTP_CODE) {
				LOGGER.error("########## 调用领拓登录接口失败，response code " + response.getHttpCode() + " 错误信息: "
						+ response.getEntity());
				return null;
			}

			// 3:处理返回结果
			String loginResult = response.getEntity();
			if (loginResult == null) {
				LOGGER.error("########## 调用领拓登录接口，response entity 为空");
				return null;
			}
			// 领拓用户信息
			ltUser = JsonUtil.json2bean(loginResult, LTUser.class);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
		return ltUser;
	}

}
