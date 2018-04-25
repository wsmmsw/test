package com.baicheng.fork.web.joint.linktour.handler;

import com.baicheng.fork.web.joint.linktour.LinkTourConstants;

/**
 * 根据请求的接口类型，动态拼接访问的url
 * 
 * @author wsm 2018年4月19日上午10:51:01
 */
public class LTUrlHandler {

	public static String getBaseUrl(int type, long productId) {
		if (type < 0) {
			return null;
		}
		StringBuffer resultUrl = new StringBuffer(LinkTourConstants.HOST);
		switch (type) {
		case LinkTourConstants.CHALLENGE_REQUEST_TYPE:
			resultUrl.append("/challenge");
			break;
		case LinkTourConstants.LOGIN_REQUEST_TYPE:
			resultUrl.append("/login");
			break;
		case LinkTourConstants.PRODUCT_LIST_REQUEST_TYPE:
			resultUrl.append("/markets");
			break;
		case LinkTourConstants.PRODUCT_REQUEST_TYPE:
			resultUrl.append("/markets/").append(productId);
			break;
		case LinkTourConstants.PRODUCT_SKU_REQUEST_TYPE:
			resultUrl.append("/markets/").append(productId).append("/sku");
			break;
		case LinkTourConstants.PRODUCT_STOCK_PRICE_REQUEST_TYPE:
			resultUrl.append("/markets/").append(productId).append("/stocks");
			break;
		case LinkTourConstants.CREATE_ORDER_QUEST_TYPE:
			resultUrl.append("/orders");
			break;
		default:
			break;
		}
		return resultUrl.toString();
	}

	public static void main(String[] args) {
		System.out.println(getBaseUrl(1001, 1));
	}
}
